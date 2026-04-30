package org.venus.shared.dataset.impl;

import org.jspecify.annotations.NonNull;
import org.venus.shared.dataset.DataColumn;
import org.venus.shared.dataset.DataRow;
import org.venus.shared.dataset.DataValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * {@code DataValues} クラスは、複数の {@link DataValue} オブジェクトを
 * 格納し、イテレーション可能な形式で提供するためのクラスです。
 * <p>
 * このクラスは {@link Iterable} インタフェースを実装しており、
 * {@link DataValue} オブジェクトを直接イテレーションすることが可能です。
 * <p>
 * 内部で空の {@link DataValue} リストを保持しており、現在の実装では
 * 空のイテレーションを提供します。
 * <p>
 * 主に {@link DataRow} インタフェースを通じて
 * 使用されます。
 */
public class DataValues implements Iterable<DataValue> {
    private static final List<DataValue> EMPTY = Collections.unmodifiableList(new ArrayList<>());
    private static final List<DataValue> list = new ArrayList<>();

    /**
     * このメソッドは {@link DataValue} オブジェクトのためのイテレータを提供します。
     * <p>
     * このクラスでは、内部的に空の {@link DataValue} リストを使用しており、返される
     * イテレータは空のイテレータとなります。
     *
     * @return {@link DataValue} の空の {@link Iterator} オブジェクトを返します。
     */
    @Override
    public @NonNull Iterator<DataValue> iterator() {
        return EMPTY.iterator();
    }

    public void addAllColumn(DataColumns columns) {
        for (int i = 0; i < columns.size(); i++) {
            addColumn(columns.get(i));
        }

    }

    public void addColumn(DataColumn column) {
        list.add(new DataValueImpl(column));
    }

    public DataValue get(int columnIndex) throws IndexOutOfBoundsException {
        return list.get(columnIndex);

    }

    public Object getValue(String columnName) {
        for (DataValue value : list) {
            if (columnName.equals(value.column().getColumnName()))
                return value.getValue();
        }
        return null;
    }

    public void setValue(@NonNull String columnName, Object newValue) {
        for (DataValue value : list) {
            if (columnName.equals(value.column().getColumnName())) {
                value.setValue(newValue);
                return;
            }
        }
    }


}
