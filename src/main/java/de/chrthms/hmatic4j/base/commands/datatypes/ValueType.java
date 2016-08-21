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
package de.chrthms.hmatic4j.base.commands.datatypes;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author christian
 */
public class ValueType implements Serializable {
    
    private Boolean undefined = null;
    private Double value = null;
    
    public Boolean getUndefined() {
        return undefined;
    }

    public void setUndefined(Boolean undefined) {
        this.undefined = undefined;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.undefined);
        hash = 97 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ValueType other = (ValueType) obj;
        if (!Objects.equals(this.undefined, other.undefined)) {
            return false;
        }
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ValueType{" + "undefined=" + undefined + ", value=" + value + '}';
    }
    
}
