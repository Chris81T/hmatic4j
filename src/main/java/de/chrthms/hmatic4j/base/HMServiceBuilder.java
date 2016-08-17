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
package de.chrthms.hmatic4j.base;

/**
 *
 * @author christian
 */
public interface HMServiceBuilder {
 
    /**
     * Is relevant to build the needed url to connect to the rpc server.
     * 
     * Simply set as serverAddress for instance "raspberrypi" or "192.168.47.11"
     * (adapt the address to your concrete address). The building procedure will
     * automatically prepend the "http://". If you have configured a secured
     * connection, so set explicitly the "https://" in your address.
     * 
     * @param address 
     * @return fluent style
     */    
    HMServiceBuilder rpcServerAddress(String address);
    
    HMConfig config();
    
    HMService service();
    
}
