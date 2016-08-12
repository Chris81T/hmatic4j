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
import de.chrthms.hmatic4j.rpc.HMaticService;
import de.chrthms.hmatic4j.rpc.exceptions.HMaticExecutionException;
import java.util.List;
import de.chrthms.hmatic4j.specs.HomematicBidCosRF;

/**
 *
 * @author christian
 */
public class HMaticWirelessConnection extends AbstractConnection  implements HMaticConnection, HomematicBidCosRF {

    public HMaticWirelessConnection(HMaticService service) {
        super(service);
    }

    @Override
    public Object getParamset(String address, String paramsetKey, Integer mode) throws HMaticExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putParamset(String address, String paramsetKey, Object paramset, String rxMode) throws HMaticExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValue(String address, String valueKey, Integer mode) throws HMaticExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setValue(String address, String valueKey, Object value, String rxMode) throws HMaticExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init(String url, String interfaceId) throws HMaticExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> listDevices() throws HMaticExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getDeviceDescription(String address) throws HMaticExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
