package org.venus.shared.dataset.impl;

import org.venus.shared.dataset.DataColumn;
import org.venus.shared.dataset.DataValue;

public class DataValueImpl implements DataValue {
    private final DataColumn column;
    private Object value;

    public DataValueImpl(DataColumn column) {
        this.column = column;
    }

    @Override
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
