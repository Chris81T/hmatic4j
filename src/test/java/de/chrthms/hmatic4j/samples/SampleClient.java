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
package de.chrthms.hmatic4j.samples;

import de.chrthms.hmatic4j.base.commands.HMCommand;
import de.chrthms.hmatic4j.HMaticAPI;

/**
 *
 * @author christian
 */
public class SampleClient {
    
    public static void main(String... args) {
        
        Object result = HMaticAPI.getInstance()
                .rpcServerAddress("127.0.0.1")
                .service()
                .connection()
                .wireless()
                .command(new HMCommand() {})
                .singleResult();
        
        HMaticAPI.getInstance()
                .config()
                .configPath("some/path/to/properties/file")
                .service()
                .connection()
                .wired()
                .command(null)
                .execute();
        
        HMaticAPI.getInstance()
                .rpcServerAddress("127.0.0.1")
                .wireless()
                .command(new HMCommand() {})
                .execute();
        
        HMaticAPI.getInstance()
                .config()
                .wireless()
                .command(new HMCommand() {})
                .execute();
        
        Double singleResult = HMaticAPI.getInstance()
                .wireless()
                .command(new HMCommand() {})
                .singleResult(Double.class);
        
    }
    
}
