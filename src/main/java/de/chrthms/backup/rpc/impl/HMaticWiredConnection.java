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

import de.chrthms.backup.rpc.HMaticConnection;
import de.chrthms.backup.rpc.HMaticService;
import de.chrthms.backup.rpc.exceptions.HMaticExecutionException;
import java.util.List;
import de.chrthms.backup.specs.HomematicBidCosWired;

/**
 *
 * @author christian
 */
public class HMaticWiredConnection extends AbstractConnection implements HMaticConnection, HomematicBidCosWired {

    public HMaticWiredConnection(HMaticService service) {
        super(service);
    }

    @Override
    public Object getParamset(String address, String paramsetKey) throws HMaticExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putParamset(String address, String paramsetKey, Object paramset) throws HMaticExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValue(String address, String valueKey) throws HMaticExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setValue(String address, String valueKey, Object value) throws HMaticExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}