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
package de.chrthms.hmatic4j.rpc.builder;

import de.chrthms.hmatic4j.rpc.impl.HMaticServiceBuilder;
import de.chrthms.hmatic4j.rpc.impl.HMaticServiceBuilderImpl;
import de.chrthms.hmatic4j.rpc.enums.BidCosMode;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import de.chrthms.hmatic4j.rpc.HMaticService;

/**
 *
 * @author christian
 */
public class HMaticServiceBuilderTest {
    
    public HMaticServiceBuilderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void getInstance() {     
        HMaticServiceBuilder builder = HMaticServiceBuilder.getInstance();
         assertTrue(builder instanceof HMaticServiceBuilderImpl);         
     }
     
     @Test
     public void setMode() {
         
         final BidCosMode mode = BidCosMode.WIRELESS;
         
        HMaticService service = HMaticServiceBuilder.getInstance()
                .mode(mode)
                .build();
        
         assertTrue(mode.equals(service.getMode()));
         
     }

     @Test
     public void setServerAddress() {
         
         final String address = "192.168.77.7";
         
        HMaticService service = HMaticServiceBuilder.getInstance()
                .mode(BidCosMode.WIRED)
                .serverAddress(address)
                .build();
        
        assertTrue(address.equals(service.getServerAddress()));
         
     }
}
