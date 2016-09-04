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
package de.chrthms.hmatic4j.core.impl;

import de.chrthms.hmatic4j.core.HMConnectionBuilder;
import de.chrthms.hmatic4j.core.HMService;
import de.chrthms.hmatic4j.core.exceptions.HMPluginException;
import de.chrthms.hmatic4j.core.exceptions.HMServiceException;
import de.chrthms.hmatic4j.event.client.HMEventBuilder;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author christian
 */
class HMServiceImpl implements HMService {

    private static final String PLUGIN_EVENT_CLASS = "de.chrthms.hmatic4j.event.core.impl.HMEventBuilderImpl";
    
    private static final Logger LOG = LoggerFactory.getLogger(HMServiceImpl.class);
    
    private final Optional<String> rpcServerAddress;
    
    public HMServiceImpl(Optional<String> rpcServerAddress) {
        this.rpcServerAddress = rpcServerAddress;
    }

    @Override
    public HMConnectionBuilder connection() {
        return new HMConnectionBuilderImpl(this);
    }

    /**
     * To establish the connection, this address is relevant.
     * @return the xml-rpc server address
     */
    public String getRpcServerAddress() throws HMServiceException {
        return rpcServerAddress.orElseThrow(() -> new HMServiceException("No RPC server address given! Check builder procedure. "
                + "May passed the wrong path during building"));
    }

    @Override
    public HMEventBuilder event() throws HMPluginException {

        ClassLoader classLoader = getClass().getClassLoader();

        try {
            LOG.debug("trying to load class {} using classLoader", PLUGIN_EVENT_CLASS);
            Class pluginClass = classLoader.loadClass(PLUGIN_EVENT_CLASS);
            
            HMEventBuilder pluginImpl = (HMEventBuilder) pluginClass.newInstance();
            LOG.debug("successfully instantiated plugin implementation {}", pluginImpl);
            return pluginImpl;
            
        } catch (ClassNotFoundException e) {
            throw new HMPluginException(PLUGIN_EVENT_CLASS + " was not found! Check, that the pluggable hmatic4j-event.jar is available for the classloader!", e);
        } catch (InstantiationException e) {
            throw new HMPluginException("Could not instantiate plugin implementation for interface " + HMEventBuilder.class.getSimpleName(), e);
        } catch (IllegalAccessException e) {
            throw new HMPluginException("Illegal Access detected. Could not instantiate plugin implementation for interface " + 
                    HMEventBuilder.class.getSimpleName(), e);
        }
        
    }
    
}
