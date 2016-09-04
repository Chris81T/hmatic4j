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

import de.chrthms.hmatic4j.core.HMConfig;
import de.chrthms.hmatic4j.core.HMService;
import de.chrthms.hmatic4j.core.HMServiceBuilder;
import de.chrthms.hmatic4j.core.HMWiredConnection;
import de.chrthms.hmatic4j.core.HMWirelessConnection;
import de.chrthms.hmatic4j.event.client.HMObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author christian
 */
public class HMServiceBuilderImpl implements HMServiceBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(HMServiceBuilderImpl.class);
    
    private String rpcServerAddress = null;
    
    @Override
    public HMServiceBuilder rpcServerAddress(String rpcServerAddress) {
        this.rpcServerAddress = rpcServerAddress;
        return this;
    }

    @Override
    public HMConfig config() {
        return new HMConfigImpl();
    }

    @Override
    public HMService service() {
        LOG.debug("the service is requested. Check, if rpcServerAddress = {} is set. If not, use the default configuration "
                + "per convention.", rpcServerAddress);
        
        if (rpcServerAddress == null) {
            return config().service();
        }
        
        return new HMServiceImpl(rpcServerAddress);
    }
    
    @Override
    public HMWiredConnection wired() {
        return service().connection().wired();
    }

    @Override
    public HMWirelessConnection wireless() {
        return service().connection().wireless();
    }

    @Override
    public HMObserver observe() {
        return service().event().observe();
    }

    @Override
    public void unobserve(String registryId) {
        service().event().unobserve(registryId);
    }

    private static class HMConfigImpl implements HMConfig {

        private String path = null;
        
        HMConfigImpl() {
        }

        @Override
        public HMConfig configPath(String path) {
            this.path = path;
            return this;
        }

        @Override
        public HMService service() {
            LOG.trace("check, if configPath = {} is set. Else use configuration per convention");

            // TODO load config and set address to instantiate the Service Impl
            LOG.warn("!! actually the config part not yet implemented !!");
            final String address = "";
            
            return new HMServiceImpl(address);
        }               

        @Override
        public HMWiredConnection wired() {
            return service().connection().wired();
        }

        @Override
        public HMWirelessConnection wireless() {
            return service().connection().wireless();
        }
        
    }
    
}
