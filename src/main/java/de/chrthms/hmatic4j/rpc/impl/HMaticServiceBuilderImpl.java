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

import de.chrthms.hmatic4j.rpc.HMaticConnection;
import de.chrthms.hmatic4j.rpc.enums.BidCosMode;
import de.chrthms.hmatic4j.rpc.HMaticService;
import de.chrthms.hmatic4j.rpc.exceptions.HMaticServiceException;

/**
 *
 * @author christian
 */
public class HMaticServiceBuilderImpl implements HMaticServiceBuilder {

    private final HMaticService<? extends HMaticConnection> service;
    
    HMaticServiceBuilderImpl(BidCosMode mode) {
        
        switch (mode) {
            case WIRED:
                service = new HMaticWiredService(mode, HMaticWiredConnection.class);                
                break;
            case WIRELESS:
                service = new HMaticWirelessService(mode, HMaticWirelessConnection.class);                
                break;
            default:
                service = null;
                throw new HMaticServiceException("Cannot prepare HMaticService. Unsupported BidCosMode = " + mode);
        }
    
    }
    
    @Override
    public HMaticServiceBuilder port(String port) {
        service.setPort(port);
        return this;
    }

    @Override
    public HMaticServiceBuilder serverAddress(String address) {
        service.setServerAddress(address);
        return this;
    }

    /**
     * 
     * @return the baked service
     */
    @Override
    public HMaticService build() throws HMaticServiceException {
                
        return service;
    }
    
}
