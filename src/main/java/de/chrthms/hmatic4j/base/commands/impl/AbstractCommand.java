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

package de.chrthms.hmatic4j.base.commands.impl;

import de.chrthms.hmatic4j.base.commands.HMCommand;
import de.chrthms.hmatic4j.base.exceptions.HMExecutionException;
import de.chrthms.hmatic4j.base.exceptions.HMUnsupportedException;
import de.chrthms.hmatic4j.base.impl.AbstractConnectionImpl;

/**
 *
 * @author christian
 */
public abstract class AbstractCommand implements HMCommand {

    protected String deviceAddress = null;
    protected String deviceChannel = null;
    
    public abstract void execute(AbstractConnectionImpl connection) throws HMUnsupportedException, HMExecutionException;

    public abstract Object singleResult(AbstractConnectionImpl connection) throws HMUnsupportedException, HMExecutionException;

    protected abstract String getMethodName();
    
    protected String concatAddressWithChannel() {
        return new StringBuilder()
                .append(deviceAddress)
                .append(":")
                .append(deviceChannel)
                .toString();
    }
    
    @Override
    public HMCommand deviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
        return this;
    }

    @Override
    public HMCommand deviceChannel(String deviceChannel) {
        this.deviceChannel = deviceChannel;
        return this;
    }
    
}
