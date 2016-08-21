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

package de.chrthms.hmatic4j.core.commands.impl.init;

import de.chrthms.hmatic4j.core.commands.HMCommand;
import de.chrthms.hmatic4j.core.exceptions.HMExecutionException;

/**
 *
 * @author christian
 */
public class LogicLayerInit extends AbstractInit implements HMCommand {

    private String interfaceId = null;
    
    /**
     * The logic layer has to set a specific interface Id.
     * 
     * @param interfaceId
     * @return 
     */
    public LogicLayerInit interfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
        return this;
    }
    
    @Override
    protected String getIterfaceId() throws HMExecutionException {
        if (interfaceId == null) throw new HMExecutionException("An interfaceId must be set!");
        return interfaceId;
    }


}
