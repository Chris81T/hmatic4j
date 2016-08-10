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
 */
public class HMaticServiceImpl implements HMaticService {

    private String serverAddress = null;
    private BidCosMode mode = null;
    
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
    public void setMode(BidCosMode mode) {
        this.mode = mode;
    }

    @Override
    public HMaticConnection getConnection() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
