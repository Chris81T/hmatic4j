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

import de.chrthms.hmatic4j.core.commands.impl.AbstractCommand;
import de.chrthms.hmatic4j.core.exceptions.HMExecutionException;
import de.chrthms.hmatic4j.core.exceptions.HMUnsupportedException;
import de.chrthms.hmatic4j.core.helpers.ConcatHelper;
import de.chrthms.hmatic4j.core.impl.AbstractConnectionImpl;

/**
 *
 * @author christian
 */
public abstract class AbstractInit extends AbstractCommand {

    private String logicRpcServerAddress = null;
    private String logicRpcServerPort = null;
    private String logicRpcServerPath = "/";
    
    protected abstract String getIterfaceId() throws HMExecutionException;
    
    private String getLogicRpcServerUrl() throws HMExecutionException {
        if (logicRpcServerAddress == null) throw new HMExecutionException("An URL for the rpc server (logic side) must be set!");
        
        StringBuilder serverUrl = new StringBuilder();
        
        if (logicRpcServerPort != null) {
            serverUrl.append(ConcatHelper.concatAddressPort(logicRpcServerAddress, logicRpcServerPort));
        } else {
            serverUrl.append(logicRpcServerAddress);
        }
        
        if (!logicRpcServerPath.startsWith("/")) {
            serverUrl.append("/");
        }
        
        serverUrl.append(logicRpcServerPath);
        return serverUrl.toString();
    }
    
    public AbstractInit logicRpcServerAddress(String logicRpcServerAddress) {
        this.logicRpcServerAddress = logicRpcServerAddress;
        return this;
    }
    
    public AbstractInit logicRpcServerPort(String logicRpcServerPort) {
        this.logicRpcServerPort = logicRpcServerPort;
        return this;
    }
    
    public AbstractInit logicRpcServerPath(String logicRpcServerPath) {
        this.logicRpcServerPath = logicRpcServerPath;
        return this;
    }
    
    @Override
    public void execute(AbstractConnectionImpl connection) throws HMUnsupportedException, HMExecutionException {
        final String interfaceId = getIterfaceId();
        
        if (interfaceId != null) {
            connection.executeRpcCall(getMethodName(), getLogicRpcServerUrl(), interfaceId);            
        } else {
            connection.executeRpcCall(getMethodName(), getLogicRpcServerUrl());            
        }        
    }

    @Override
    public Object singleResult(AbstractConnectionImpl connection) throws HMUnsupportedException, HMExecutionException {
        throw new HMUnsupportedException();
    }

    @Override
    protected String getMethodName() {
        return "init";
    }

}
