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

import de.chrthms.backup.rpc.exceptions.HMaticExecutionException;
import de.chrthms.hmatic4j.base.HMConnection;
import de.chrthms.hmatic4j.base.HMResult;
import de.chrthms.hmatic4j.base.commands.HMCommand;
import java.net.MalformedURLException;
import java.net.URL;
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
    private XmlRpcClient getXmlRpcClient() {
        if (xmlRpcClient == null) {
            
            final String url = getUrl();
            
            try {
                xmlRpcConfig = new XmlRpcClientConfigImpl();
                xmlRpcConfig.setServerURL(new URL(url));
            } catch (MalformedURLException e) {
                throw new HMaticExecutionException("Invalid/malformed given url = " + url, e);
            }
            
            xmlRpcClient = new XmlRpcClient();
            xmlRpcClient.setConfig(xmlRpcConfig);            
        }
        
        return xmlRpcClient;        
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
    public void execute() {
        XmlRpcClient xmlRpcClient = getXmlRpcClient();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HMResult singleResult() {
        XmlRpcClient xmlRpcClient = getXmlRpcClient();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }   
    
}
