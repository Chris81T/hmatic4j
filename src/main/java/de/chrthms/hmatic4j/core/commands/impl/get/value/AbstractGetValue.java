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

package de.chrthms.hmatic4j.core.commands.impl.get.value;

import de.chrthms.hmatic4j.core.commands.datatypes.ValueType;
import de.chrthms.hmatic4j.core.commands.enums.ValueTypeMode;
import de.chrthms.hmatic4j.core.commands.impl.AbstractResultCommand;
import de.chrthms.hmatic4j.core.exceptions.HMExecutionException;
import de.chrthms.hmatic4j.core.exceptions.HMUnsupportedException;
import de.chrthms.hmatic4j.core.impl.AbstractConnectionImpl;
import java.util.Map;

/**
 *
 * @author christian
 */
public abstract class AbstractGetValue extends AbstractResultCommand {

    abstract protected String getValueKey();
    
    protected ValueTypeMode valueTypeMode = ValueTypeMode.SIMPLE_VALUE;
    
    @Override
    protected String getMethodName() {
        return "getValue";
    }
    
    @Override
    public void execute(AbstractConnectionImpl connection) throws HMUnsupportedException, HMExecutionException {
        throw new HMUnsupportedException();
    }

    @Override
    public Object singleResult(AbstractConnectionImpl connection) throws HMUnsupportedException, HMExecutionException {

        if (connection.isWireless()) {
            Object resultCall = connection.executeRpcCall(getMethodName(), concatAddressWithChannel(), getValueKey(), valueTypeMode.getAsInt());
            
            switch (valueTypeMode) {
                case SIMPLE_VALUE:
                    return resultCall;                            
                case STRUCT_VALUE:
                    Map<String, Object> resultMap = (Map<String, Object>) resultCall;
                    
                    ValueType valueType = new ValueType();                    
                    valueType.setUndefined((Boolean) resultMap.get("UNDEFINED"));
                    valueType.setValue((Double) resultMap.get("VALUE"));                    
                    
                    return valueType;
                default:
                    throw new HMExecutionException("Given valueTypeMode is not supported!");
            }
        } else {
            return connection.executeRpcCall(getMethodName(), concatAddressWithChannel(), getValueKey());            
        }

    }

    /**
     * Will be only recognized, if the connection is in wireless mode.
     * 
     * @param valueTypeMode check enumeration for available modes
     * @return 
     */
    public AbstractGetValue valueTypeMode(ValueTypeMode valueTypeMode) {
        this.valueTypeMode = valueTypeMode;
        return this;
    }
   
    
}
