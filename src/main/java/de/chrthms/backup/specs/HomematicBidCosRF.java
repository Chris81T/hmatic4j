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
package de.chrthms.backup.specs;

import de.chrthms.backup.rpc.commands.SetValueCmd;
import de.chrthms.backup.rpc.commands.GetValueCmd;
import de.chrthms.backup.specs.datatypes.ValueType;
import de.chrthms.backup.rpc.exceptions.HMaticExecutionException;
import de.chrthms.backup.specs.enums.RxMode;
import de.chrthms.backup.specs.enums.ValueTypeMode;

/**
 *
 * @author christian
 */
public interface HomematicBidCosRF extends Homematic {
    
    Object getParamset(String address, String paramsetKey, ValueTypeMode mode) throws HMaticExecutionException;
    
    void putParamset(String address, String paramsetKey, Object paramset, RxMode rxMode) throws HMaticExecutionException;

    ValueType getValue(String address, String channel, GetValueCmd cmd) throws HMaticExecutionException;
    ValueType getValue(String address, String channel, GetValueCmd cmd, ValueTypeMode mode) throws HMaticExecutionException;
    
    void setValue(String address, String channel, SetValueCmd cmd) throws HMaticExecutionException;
    void setValue(String address, String channel, SetValueCmd cmd, RxMode rxMode) throws HMaticExecutionException;
        
}
