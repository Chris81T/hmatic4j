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
package de.chrthms.hmatic4j.rpc.impl;

import de.chrthms.hmatic4j.rpc.HMaticService;
import de.chrthms.hmatic4j.rpc.exceptions.HMaticExecutionException;
import de.chrthms.hmatic4j.specs.Homematic;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 *
 * @author christian
 */
public class AbstractConnection implements Homematic {
    
    private final HMaticService service;

    private XmlRpcClientConfigImpl clientConfig = null;
    private XmlRpcClient client = null;
    
    protected AbstractConnection(HMaticService service) {
        this.service = service;
    }   
    
    private XmlRpcClient getClient() throws HMaticExecutionException {
        
        if (client == null) {
            
            try {
                clientConfig = new XmlRpcClientConfigImpl();
                clientConfig.setServerURL(new URL(service.getUrl()));
            } catch (MalformedURLException e) {
                throw new HMaticExecutionException("Invalid/malformed given url = " + service.getUrl(), e);
            }
            
            client = new XmlRpcClient();
            client.setConfig(clientConfig);
            
        }
        
        return client;
    }
        
    protected Object execute(final String methodName, final Object... params) throws HMaticExecutionException {
        try {
            return getClient().execute(methodName, params);
        } catch (XmlRpcException e) {
            StringBuilder msg = new StringBuilder();
            msg.append("Execution of method with name = ");
            msg.append(methodName);
            msg.append(" failed! Given params = ");
            msg.append(params);
            throw new HMaticExecutionException(msg.toString(), e);
        }
    }
    
    @Override
    public Object getDeviceDescription(String address) throws HMaticExecutionException {                
        return execute("getDeviceDescription", address);
    }

    @Override
    public Object getParamsetDescription(String address, String paramsetType) throws HMaticExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getParamsetId(String address, String type) throws HMaticExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
    
}
