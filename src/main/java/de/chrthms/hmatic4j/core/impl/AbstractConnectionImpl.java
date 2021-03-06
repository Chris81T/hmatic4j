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
package de.chrthms.hmatic4j.core.impl;

import de.chrthms.hmatic4j.core.HMConnection;
import de.chrthms.hmatic4j.core.commands.HMCommand;
import de.chrthms.hmatic4j.core.commands.impl.AbstractCommand;
import de.chrthms.hmatic4j.core.commands.impl.AbstractResultCommand;
import de.chrthms.hmatic4j.core.exceptions.HMExecutionException;
import de.chrthms.hmatic4j.core.exceptions.HMConnectionException;
import de.chrthms.hmatic4j.core.exceptions.HMUnsupportedException;
import de.chrthms.hmatic4j.core.helpers.ConcatHelper;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author christian 
 */
public abstract class AbstractConnectionImpl implements HMConnection {
 
    private final Logger LOG;
    
    private final HMServiceImpl service;

    private String port;
    private HMCommand command = null;
    
    private XmlRpcClientConfigImpl xmlRpcConfig = null;
    private XmlRpcClient xmlRpcClient = null;    
    
    AbstractConnectionImpl(HMServiceImpl service, String port, Class<? extends AbstractConnectionImpl> loggerClass) {
        this.service = service;
        this.port = port;
        this.LOG = LoggerFactory.getLogger(loggerClass);
    }
    
    private String getUrl() {
        final String rpcServerAddress = service.getRpcServerAddress();
        LOG.trace("check given rpcServerAddress {} and build desired URL", rpcServerAddress);

        StringBuilder builder = new StringBuilder();
        
        if (!rpcServerAddress.startsWith("http")) builder.append("http://");        
        builder.append(ConcatHelper.concatAddressPort(rpcServerAddress, port));
        
        return builder.toString();
    }    
    
    /**
     * Lazy Loading. When the first time the executeRpcCall or singleResult method will
     * be invoked, the xmlRpcClient will be instantiated.
     * 
     * @return the xmlRpcClient instance 
     */
    private XmlRpcClient getXmlRpcClient() throws HMConnectionException {
        LOG.trace("about to return XmlRpcClient = ", xmlRpcClient);
        if (xmlRpcClient == null) {
                        
            final String url = getUrl();
            LOG.debug("lazy loading: create xmlRpcClient using the url {}", url);
            
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
        LOG.trace("cast command interface to AbstractCommand");
        if (!(command instanceof AbstractCommand)) {
            throw new HMConnectionException("Command does not extend AbstractCommand! Class name = " + command.getClass().getSimpleName());
        }
        return (AbstractCommand) command;
    }
    
    private AbstractResultCommand castResultCommand() throws HMConnectionException {
        LOG.trace("cast command interface to AbstractResultCommand");
        if (!(command instanceof AbstractResultCommand)) {
            throw new HMConnectionException("Command does not extend AbstractResultCommand! Class name = " + command.getClass().getSimpleName());
        }
        return (AbstractResultCommand) command;
    }
    
    public Object executeRpcCall(final String methodName, Object... params) throws HMExecutionException {
        try {
            LOG.info("about to execute xml-rpc call with methodName = {} and params = {}", methodName, params);
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
        LOG.trace("If allowed, cast given instance {} to HMWiredConnectionImpl", this);
        if (isWireless()) throw new HMConnectionException("This instance is not a wired connection implementation!");
        return (HMWiredConnectionImpl) this;
    }
    
    public HMWirelessConnectionImpl castToWirelessImpl() throws HMConnectionException {
        LOG.trace("If allowed, cast given instance {} to HMWirelessConnectionImpl", this);
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
        LOG.debug("about to invoke the command's execute method. Command = {}", command);
        castCommand().execute(this);
    }

    @Override
    public Object singleResult() throws HMConnectionException, HMUnsupportedException, HMExecutionException {
        LOG.debug("about to invoke the command's singleResult method. Command = {}", command);
        return castCommand().singleResult(this);
    }   
    
    @Override
    public <T> T singleResult(Class<T> resultClass) throws HMConnectionException, HMUnsupportedException, HMExecutionException {
        LOG.debug("about to prepare the typed singleResult invocation of the command = {}", command);
        
        /**
         * Before invoking the rpc call, first compare commands expected class with
         * the given one as a parameter.
         */
        final Class<?> expectedClass = castResultCommand().getExpectedClass();
        if (!resultClass.equals(expectedClass)) {
            throw new HMExecutionException("Type checkup failed! ExpectedClass = " + 
                    expectedClass.getSimpleName() +
                    " is not equal to resultClass given as parameter = " +
                    resultClass.getSimpleName());            
        }
        
        Object result = singleResult();
        
        if (!resultClass.isInstance(result)) {
            throw new HMExecutionException("Type checkup failed! Result is not an instance of resultClass given as parameter = " +
                    resultClass.getSimpleName() +
                    ", class of given result = " + result.getClass().getSimpleName());
        }
        
        return (T) result;
    }   
    
}
