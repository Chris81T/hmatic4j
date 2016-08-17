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
package de.chrthms.backup.rpc.commands.setvalue;

import de.chrthms.backup.rpc.commands.SetValueCmd;

/**
 *
 * @author christian
 */
public class SetValueLevel implements SetValueCmd<Double> {
        
    private static final String PARAM = "LEVEL";
    
    private Double value = null;

    public SetValueLevel() {
    }
    
    public SetValueLevel(Double value) {
        this.value = value;
    }

    @Override
    public String getParam() {
        return PARAM;
    }

    @Override
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SetValueLevel{" + "value=" + value + '}';
    }
    
}
