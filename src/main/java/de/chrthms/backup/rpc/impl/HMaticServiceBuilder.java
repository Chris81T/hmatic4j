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
package de.chrthms.backup.rpc.impl;

import de.chrthms.backup.rpc.HMaticBuilder;
import de.chrthms.backup.rpc.enums.BidCosMode;
import de.chrthms.backup.rpc.HMaticService;
import de.chrthms.backup.rpc.exceptions.HMaticServiceException;

/**
 *  Fluent API to build a service using to communicate via xml-rpc
 * 
 * @author christian
 */
public interface HMaticServiceBuilder extends HMaticBuilder {

    static HMaticServiceBuilder getInstance(BidCosMode mode) {
        return new HMaticServiceBuilderImpl(mode);
    }
    
    HMaticServiceBuilder port(String port);
    
    HMaticServiceBuilder serverAddress(String address);
    
    HMaticService build() throws HMaticServiceException;
    
}