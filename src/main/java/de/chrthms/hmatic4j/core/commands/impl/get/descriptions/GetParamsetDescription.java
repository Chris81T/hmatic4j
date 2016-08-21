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

package de.chrthms.hmatic4j.core.commands.impl.get.descriptions;

import de.chrthms.hmatic4j.core.commands.HMCommand;
import de.chrthms.hmatic4j.core.commands.enums.ParamsetType;
import de.chrthms.hmatic4j.core.commands.impl.AbstractResultCommand;
import de.chrthms.hmatic4j.core.exceptions.HMExecutionException;
import de.chrthms.hmatic4j.core.exceptions.HMUnsupportedException;
import de.chrthms.hmatic4j.core.impl.AbstractConnectionImpl;

/**
 * Command will return some information about the target device.
 * 
 * It is possible to simply set the address without a channel (typically parent device)
 * or to set the channel to get information about the child device.
 * 
 * @author christian
 */
public class GetParamsetDescription extends AbstractResultCommand implements HMCommand {

    private ParamsetType paramsetType = null;
    
    public GetParamsetDescription paramsetType(ParamsetType paramsetType) {
        this.paramsetType = paramsetType;
        return this;
    }
    
    @Override
    public Class<?> getExpectedClass() {
        throw new UnsupportedOperationException("Not supported yet. A concrete datatype Class must be implemented!"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void execute(AbstractConnectionImpl connection) throws HMUnsupportedException, HMExecutionException {
        throw new HMUnsupportedException();
    }

    @Override
    public Object singleResult(AbstractConnectionImpl connection) throws HMUnsupportedException, HMExecutionException {
        
        if (paramsetType == null) throw new HMExecutionException("a ParamsetType must be set!");
        
        return connection.executeRpcCall(getMethodName(), concatAndValidateAddressWithChannelIfSet(), paramsetType.toString());
    }

    @Override
    protected String getMethodName() {
        return "getParamsetDescription";
    }
    
}
