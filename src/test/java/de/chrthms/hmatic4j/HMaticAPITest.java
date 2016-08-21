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
package de.chrthms.hmatic4j;

import de.chrthms.hmatic4j.core.HMConnection;
import de.chrthms.hmatic4j.core.HMServiceBuilder;
import de.chrthms.hmatic4j.core.commands.impl.set.value.SetValueStop;
import de.chrthms.hmatic4j.core.impl.HMServiceBuilderImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author christian
 */
public class HMaticAPITest {
    
    public HMaticAPITest() {
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
     public void testHMaticAPIInstantiation() {
         
        HMServiceBuilder instance = HMaticAPI.getInstance();
        assertTrue(instance instanceof HMServiceBuilderImpl);
        
     }
     
     @Test
     public void testCommandExecution() {
         
        SetValueStop setValueStop = new SetValueStop();
         
        HMConnection connection = HMaticAPI.getInstance()
                .wireless()
                .command(setValueStop);
        
        // TODO mock XmlRpcClient for checking functionality
        
     }
     
}
