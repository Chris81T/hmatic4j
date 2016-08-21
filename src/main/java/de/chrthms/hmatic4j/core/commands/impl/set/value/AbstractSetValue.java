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

package de.chrthms.hmatic4j.core.commands.impl.set.value;

import de.chrthms.hmatic4j.core.commands.impl.AbstractCommand;
import de.chrthms.hmatic4j.core.exceptions.HMExecutionException;
import de.chrthms.hmatic4j.core.exceptions.HMUnsupportedException;
import de.chrthms.hmatic4j.core.impl.AbstractConnectionImpl;
import de.chrthms.hmatic4j.core.impl.HMWirelessConnectionImpl;

/**
 *
 * @author christian
 * @param <T> is relevant for the concrete SetValue class.
 */
public abstract class AbstractSetValue<T> extends AbstractCommand {
   
    abstract protected String getValueKey();

    abstract protected T getValue();
    
    @Override
    protected String getMethodName() {
        return "setValue";
    }
    
    @Override
    public void execute(AbstractConnectionImpl connection) throws HMUnsupportedException, HMExecutionException {

        if (connection.isWireless()) {
            HMWirelessConnectionImpl wirelessConnection = connection.castToWirelessImpl();
            connection.executeRpcCall(getMethodName(), concatAddressWithChannel(), getValueKey(), getValue(), wirelessConnection.getRxMode().toString());
        } else {
            connection.executeRpcCall(getMethodName(), concatAddressWithChannel(), getValueKey(), getValue());            
        }

    }

    @Override
    public Object singleResult(AbstractConnectionImpl connection) throws HMUnsupportedException, HMExecutionException {
        throw new HMUnsupportedException();
    }
    
}
