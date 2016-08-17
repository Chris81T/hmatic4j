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
public class SetValueStop implements SetValueCmd<Boolean> {

    private static final String PARAM = "STOP";
    private static final Boolean VALUE = true;
    
    @Override
    public String getParam() {
        return PARAM;
    }

    @Override
    public Boolean getValue() {
        return VALUE;
    }
    
}
