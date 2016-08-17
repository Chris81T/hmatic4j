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
package de.chrthms.hmatic4j.base.impl;

import de.chrthms.hmatic4j.base.HMConfig;
import de.chrthms.hmatic4j.base.HMService;
import de.chrthms.hmatic4j.base.HMServiceBuilder;
import de.chrthms.hmatic4j.base.HMWiredConnection;
import de.chrthms.hmatic4j.base.HMWirelessConnection;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author christian
 */
public class HMServiceBuilderImpl implements HMServiceBuilder {

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
                        
            // TODO load config and set address to instantiate the Service Impl
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
