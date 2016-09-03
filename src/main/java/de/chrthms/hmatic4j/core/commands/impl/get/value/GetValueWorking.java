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

package de.chrthms.hmatic4j.core.commands.impl.get.value;

import de.chrthms.hmatic4j.core.commands.HMCommand;
import de.chrthms.hmatic4j.event.client.enums.ValueKey;

/**
 *
 * @author christian
 */
public class GetValueWorking extends AbstractGetValue implements HMCommand {

    @Override
    public Class<?> getExpectedClass() {
        return Boolean.class;
    }

    @Override
    protected String getValueKey() {
        return ValueKey.WORKING.toString();
    }
    
}
