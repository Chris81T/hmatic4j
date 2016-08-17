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
import de.chrthms.backup.rpc.commands.GetValueCmd;
import de.chrthms.backup.rpc.commands.SetValueCmd;
import de.chrthms.backup.rpc.exceptions.HMaticExecutionException;
import de.chrthms.backup.specs.HomematicBidCosRF;
import de.chrthms.backup.specs.datatypes.ValueType;
import de.chrthms.backup.specs.enums.RxMode;
import de.chrthms.backup.specs.enums.ValueTypeMode;
import java.util.Map;

/**
 *
 * @author christian
 */
public class HMaticWirelessConnection extends AbstractConnection implements HMaticConnection, HomematicBidCosRF {

    public HMaticWirelessConnection(HMaticService service) {
        super(service);
    }

    @Override
    public Object getParamset(String address, String paramsetKey, ValueTypeMode mode) throws HMaticExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putParamset(String address, String paramsetKey, Object paramset, RxMode rxMode) throws HMaticExecutionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ValueType getValue(String address, String channel, GetValueCmd cmd) throws HMaticExecutionException {
        return getValue(address, channel, cmd, ValueTypeMode.SIMPLE_VALUE);
    }
    
    @Override
    public ValueType getValue(String address, String channel, GetValueCmd cmd, ValueTypeMode mode) throws HMaticExecutionException {
        
        Object result = execute("getValue", concatAddressWithChannel(address, channel), cmd.getValueKey(), mode.getAsInt());
        
        ValueType valueType = new ValueType();
        
        switch (mode) {
            case SIMPLE_VALUE:
                valueType.setValue((Double) result);
                break;
            case STRUCT_VALUE:
                Map<String, Object> resultMap = (Map<String, Object>) result;
                valueType.setUndefined((Boolean) resultMap.get("UNDEFINED"));
                valueType.setValue((Double) resultMap.get("VALUE"));
                break;
            default:
                throw new HMaticExecutionException("Unhandled ValueTypeMode!");
        }
        
        return valueType;
    }

    @Override
    public void setValue(String address, String channel, SetValueCmd cmd) throws HMaticExecutionException {
        setValue(address, channel, cmd, RxMode.BURST);
    }

    @Override
    public void setValue(String address, String channel, SetValueCmd cmd, RxMode rxMode) throws HMaticExecutionException {
        execute("setValue", concatAddressWithChannel(address, channel), cmd.getParam(), cmd.getValue(), rxMode.toString());
    }
    
}
