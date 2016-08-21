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
import de.chrthms.hmatic4j.core.HMWiredConnection;
import de.chrthms.hmatic4j.core.HMWirelessConnection;

/**
 *
 * @author christian
 */
class HMConnectionBuilderImpl implements HMConnectionBuilder {

    private final HMServiceImpl service;
    
    public HMConnectionBuilderImpl(HMServiceImpl service) {
        this.service = service;
    }

    @Override
    public HMWiredConnection wired() {
        return new HMWiredConnectionImpl(service);
    }

    @Override
    public HMWirelessConnection wireless() {
        return new HMWirelessConnectionImpl(service);
    }
    
}
