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

package de.chrthms.hmatic4j.base.commands.impl.set.value;

import de.chrthms.hmatic4j.base.commands.HMCommand;
import de.chrthms.hmatic4j.base.exceptions.HMExecutionException;
import de.chrthms.hmatic4j.base.exceptions.HMUnsupportedException;
import de.chrthms.hmatic4j.base.impl.AbstractConnectionImpl;
import de.chrthms.hmatic4j.base.impl.HMWirelessConnectionImpl;

/**
 *
 * @author christian
 */
public class SetValueStop extends AbstractSetValue implements HMCommand {
    
    private static final String VALUE_KEY = "STOP";
    
    @Override
    public void execute(AbstractConnectionImpl connection) throws HMUnsupportedException, HMExecutionException {

        if (connection.isWireless()) {
            HMWirelessConnectionImpl wirelessConnection = connection.castToWirelessImpl();
            connection.execute(METHOD_NAME, VALUE_KEY, concatAddressWithChannel(), true, wirelessConnection.getRxMode());
        } else {
            connection.execute(METHOD_NAME, VALUE_KEY, concatAddressWithChannel(), true);            
        }

    }

    @Override
    public Object singleResult(AbstractConnectionImpl connection) throws HMUnsupportedException, HMExecutionException {
        throw new HMUnsupportedException();
    }

    @Override
    public <T> T singleResult(AbstractConnectionImpl connection, Class<T> resultClass) throws HMUnsupportedException, HMExecutionException {
        throw new HMUnsupportedException();
    }

    
}
