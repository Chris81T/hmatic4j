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
package de.chrthms.backup.rpc.impl;

import de.chrthms.backup.rpc.enums.BidCosMode;
import de.chrthms.backup.rpc.HMaticConnection;
import de.chrthms.backup.rpc.HMaticService;
import de.chrthms.backup.rpc.exceptions.HMaticServiceException;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author christian
 * 
 * @param <T> it is relevant to define the concrete connection class. This is relevant 
 *            for the service builder.
 */
public abstract class AbstractService<T extends HMaticConnection> implements HMaticService<T> {

    private static final String BID_COS_WIRED_DEFAULT_PORT = "2000";
    private static final String BID_COS_RF_DEFAULT_PORT = "2001";
    
    private final BidCosMode mode;
    private final Class<T> connectionClass;
    
    private String serverAddress = null;
    private String port = null;

    AbstractService(BidCosMode mode, Class<T> connectionClass) {
        this.mode = mode;
        this.connectionClass = connectionClass;
        
        switch (mode) {
            case WIRED:
                port = BID_COS_WIRED_DEFAULT_PORT;
                break;
            case WIRELESS:
                port = BID_COS_RF_DEFAULT_PORT;            
        }
    }

    @Override
    public String getServerAddress() {
        return serverAddress;
    }

    @Override
    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    @Override
    public BidCosMode getMode() {
        return mode;
    }

    @Override
    public String getPort() {
        return port;
    }

    /**
     * Normally the BidCos mode will define the port. But it is possible to force
     * override the default port.
     * 
     * @param port 
     */
    @Override
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * Will build the relevant url to establish a server connection.
     * 
     * Simply set as serverAddress for instance "raspberrypi" or "192.168.47.11"
     * (adapt the address to your concrete address). The building procedure will
     * automatically prepend the "http://". If you have configured a secured
     * connection, so set explicitly the "https://" in your @see serverAddress.
     * 
     * @return concatenated URL address 
     */
    @Override
    public String getUrl() {
        StringBuilder builder = new StringBuilder();
        
        if (!serverAddress.startsWith("http")) builder.append("http://");        
        builder.append(serverAddress);
        builder.append(":");
        builder.append(port);
        
        return builder.toString();
    }

    @Override
    public T getConnection() throws HMaticServiceException {
        try {
            return connectionClass.getDeclaredConstructor(HMaticService.class).newInstance(this);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                IllegalArgumentException| NoSuchMethodException | SecurityException e) {
            throw new HMaticServiceException("New instantiation of connectionClass failed!", e);
        }
    }
    
}
