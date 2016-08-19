/*
 * Copyright 2016 Christian Thomas.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.chrthms.hmatic4j.base.impl;

import de.chrthms.hmatic4j.base.HMConnection;
import de.chrthms.hmatic4j.base.commands.HMCommand;
import de.chrthms.hmatic4j.base.commands.impl.AbstractCommand;
import de.chrthms.hmatic4j.base.commands.impl.AbstractResultCommand;
import de.chrthms.hmatic4j.base.exceptions.HMExecutionException;
import de.chrthms.hmatic4j.base.exceptions.HMConnectionException;
import de.chrthms.hmatic4j.base.exceptions.HMUnsupportedException;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 *
 * @author christian 
 */
public abstract class AbstractConnectionImpl implements HMConnection {
 
    private final HMServiceImpl service;

    private String port;
    private HMCommand command = null;
    
    private XmlRpcClientConfigImpl xmlRpcConfig = null;
    private XmlRpcClient xmlRpcClient = null;    
    
    AbstractConnectionImpl(HMServiceImpl service, String port) {
        this.service = service;
        this.port = port;
    }
    
    private String getUrl() {
        StringBuilder builder = new StringBuilder();
        final String rpcServerAddress = service.getRpcServerAddress();
        
        if (!rpcServerAddress.startsWith("http")) builder.append("http://");        
        builder.append(rpcServerAddress);
        builder.append(":");
        builder.append(port);
        
        return builder.toString();
    }    
    
    /**
     * Lazy Loading. When the first time the execute or singleResult method will
     * be invoked, the xmlRpcClient will be instantiated.
     * 
     * @return the xmlRpcClient instance 
     */
    private XmlRpcClient getXmlRpcClient() throws HMConnectionException {
        if (xmlRpcClient == null) {
            
            final String url = getUrl();
            
            try {
                xmlRpcConfig = new XmlRpcClientConfigImpl();
                xmlRpcConfig.setServerURL(new URL(url));
            } catch (MalformedURLException e) {
                throw new HMConnectionException("Invalid/malformed given url = " + url, e);
            }
            
            xmlRpcClient = new XmlRpcClient();
            xmlRpcClient.setConfig(xmlRpcConfig);            
        }
        
        return xmlRpcClient;        
    }

    private AbstractCommand castCommand() throws HMConnectionException {
        if (!(command instanceof AbstractCommand)) {
            throw new HMConnectionException("Command does not extend AbstractCommand! Class name = " + command.getClass().getSimpleName());
        }
        return (AbstractCommand) command;
    }
    
    private AbstractResultCommand<?> castResultCommand() throws HMConnectionException {
        if (!(command instanceof AbstractResultCommand)) {
            throw new HMConnectionException("Command does not extend AbstractResultCommand! Class name = " + command.getClass().getSimpleName());
        }
        return (AbstractResultCommand) command;
    }
    
    public Object execute(final String methodName, Object... params) throws HMExecutionException {
        try {
            return getXmlRpcClient().execute(methodName, params);
        } catch (XmlRpcException e) {
            throw new HMExecutionException("Could not perform xml-rpc execution!", e);
        }
    }

    public boolean isWired() {
        return (this instanceof HMWiredConnectionImpl);
    }
    
    public boolean isWireless() {
        return (this instanceof HMWirelessConnectionImpl);
    }

    public HMWiredConnectionImpl castToWiredImpl() throws HMConnectionException {
        if (isWireless()) throw new HMConnectionException("This instance is not a wired connection implementation!");
        return (HMWiredConnectionImpl) this;
    }
    
    public HMWirelessConnectionImpl castToWirelessImpl() throws HMConnectionException {
        if (isWired()) throw new HMConnectionException("This instance is not a wireless connection implementation!");
        return (HMWirelessConnectionImpl) this;
    }
    
    @Override
    public HMConnection port(String port) {
        this.port = port;
        return this;
    }
    
    @Override
    public HMConnection command(HMCommand command) {
        this.command = command;
        return this;
    }
    
    @Override
    public void execute() throws HMConnectionException, HMUnsupportedException, HMExecutionException {
        castCommand().execute(this);
    }

    @Override
    public Object singleResult() throws HMConnectionException, HMUnsupportedException, HMExecutionException {
        return castCommand().singleResult(this);
    }   
    
    @Override
    public <T> T singleResult(Class<T> resultClass) throws HMConnectionException, HMUnsupportedException, HMExecutionException {
        Object result = singleResult();
        
        final Class<?> expectedClass = castResultCommand().getExpectedClass();
        
        if (!resultClass.equals(expectedClass) ||
            !resultClass.isInstance(result)) {
            throw new HMExecutionException("Type checkup failed! expectedClass = " + 
                    expectedClass.getSimpleName() +
                    ", resultClass given as parameter = " +
                    resultClass.getSimpleName() +
                    ", class of given result = " + result.getClass().getSimpleName());
        }
        
        return (T) result;
    }   
    
}
