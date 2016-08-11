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

import de.chrthms.hmatic4j.rpc.enums.BidCosMode;
import de.chrthms.hmatic4j.rpc.HMaticConnection;
import de.chrthms.hmatic4j.rpc.HMaticService;

/**
 *
 * @author christian
 * 
 * @param <T> it is relevant to define the concrete connection class. This is relevant 
 *            for the service builder.
 */
public class HMaticServiceImpl<T extends HMaticConnection> implements HMaticService<T> {

    private static final String BID_COS_WIRED_DEFAULT_PORT = "2000";
    private static final String BID_COS_RF_DEFAULT_PORT = "2001";
    
    private final BidCosMode mode;

    private String serverAddress = null;
    private String port = null;

    HMaticServiceImpl(BidCosMode mode) {
        this.mode = mode;
        
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

    @Override
    public String getUrl() {
        StringBuilder builder = new StringBuilder(serverAddress);
        builder.append(":");
        builder.append(port);
        return builder.toString();
    }

    @Override
    public T getConnection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
