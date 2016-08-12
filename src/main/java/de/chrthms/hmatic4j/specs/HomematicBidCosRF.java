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
package de.chrthms.hmatic4j.specs;

import de.chrthms.hmatic4j.rpc.exceptions.HMaticExecutionException;

/**
 *
 * @author christian
 */
public interface HomematicBidCosRF extends Homematic {
    
    Object getParamset(String address, String paramsetKey, Integer mode) throws HMaticExecutionException;
    
    void putParamset(String address, String paramsetKey, Object paramset, String rxMode) throws HMaticExecutionException;

    Object getValue(String address, String valueKey, Integer mode) throws HMaticExecutionException;
    
    void setValue(String address, String valueKey, Object value, String rxMode) throws HMaticExecutionException;
        
}
