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
package de.chrthms.hmatic4j.core;

import de.chrthms.hmatic4j.core.commands.HMCommand;
import de.chrthms.hmatic4j.core.exceptions.HMExecutionException;
import de.chrthms.hmatic4j.core.exceptions.HMConnectionException;
import de.chrthms.hmatic4j.core.exceptions.HMUnsupportedException;

/**
 *
 * @author christian
 */
public interface HMConnection {

    /**
     * If it is relevant to customize the port setting, this method will adjust
     * the port.
     * 
     * @param port will override default set port
     * @return 
     */
    HMConnection port(String port);
    
    /**
     * Is relevant to perform the @see execute() or @see singleResult() methods.
     * 
     * @param command
     * @return 
     */
    HMConnection command(HMCommand command);
        
    /**
     * Is typically useful for "setter" commands or commands, that does not give
     * a result back.
     * @throws HMConnectionException when connection can not be established or an
     *         invalid command is given.
     * @throws HMUnsupportedException when command does not provide invoked
     *         execution method. For instance the command does not return a value
     *         and the client has invoked one of the singleResult methods.
     * @throws HMExecutionException when the command execution will fail.
     */
    void execute() throws HMConnectionException, HMUnsupportedException, HMExecutionException;
    
    /**
     * If the the command should give a result back the the calling client, this
     * method is the right one.
     * 
     * @return the single result an untyped result
     * @throws HMConnectionException when connection can not be established or an
     *         invalid command is given.
     * @throws HMUnsupportedException when command does not provide invoked
     *         execution method. For instance the command does not return a value
     *         and the client has invoked one of the singleResult methods.
     * @throws HMExecutionException when the command execution will fail.
     */
    Object singleResult() throws HMConnectionException, HMUnsupportedException, HMExecutionException;
    
    /**
     * If the the command should give a result back the the calling client, this
     * method is the right one.
     * 
     * @param <T>
     * @param resultClass is relevant for a typed result to make the cast obsolete
     * @return the single result a typed result
     * @throws HMConnectionException when connection can not be established or an
     *         invalid command is given.
     * @throws HMUnsupportedException when command does not provide invoked
     *         execution method. For instance the command does not return a value
     *         and the client has invoked one of the singleResult methods.
     * @throws HMExecutionException when the command execution will fail.
     */
    <T> T singleResult(Class<T> resultClass) throws HMConnectionException, HMUnsupportedException, HMExecutionException;
      
}
