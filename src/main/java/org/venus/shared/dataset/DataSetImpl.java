package org.venus.shared.dataset;

import org.jspecify.annotations.NonNull;
import org.venus.shared.dataset.row.DataRow;
import org.venus.shared.dataset.row.DataRows;

import java.util.Iterator;

public class DataSetImpl implements DataSet, Iterable<DataRow> {

    @Override
    public @NonNull Iterator<DataRow> iterator() {
        return DataRows.EMPTY.iterator();
    }
}
