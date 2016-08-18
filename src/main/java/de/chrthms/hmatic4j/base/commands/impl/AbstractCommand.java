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
import de.chrthms.hmatic4j.base.exceptions.HMCommandException;
import de.chrthms.hmatic4j.base.exceptions.HMUnsupportedException;
import org.apache.xmlrpc.client.XmlRpcClient;

/**
 *
 * @author christian
 */
public abstract class AbstractCommand implements HMCommand {

    public abstract void execute(XmlRpcClient rpcClient) throws HMUnsupportedException, HMCommandException;

    public abstract Object singleResult(XmlRpcClient rpcClient) throws HMUnsupportedException, HMCommandException;

    public abstract <T> T singleResult(XmlRpcClient rpcClient, Class<T> resultClass) throws HMUnsupportedException, HMCommandException;
    
}
