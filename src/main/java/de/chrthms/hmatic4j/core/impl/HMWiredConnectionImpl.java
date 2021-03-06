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

import de.chrthms.hmatic4j.core.HMWiredConnection;

/**
 *
 * @author christian
 */
public class HMWiredConnectionImpl extends AbstractConnectionImpl implements HMWiredConnection {

    private static final String PORT = "2000";
    
    public HMWiredConnectionImpl(HMServiceImpl service) {
        super(service, PORT, HMWiredConnectionImpl.class);
    }
    
}
