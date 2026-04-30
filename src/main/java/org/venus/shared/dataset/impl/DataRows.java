package org.venus.shared.dataset.impl;

import org.venus.shared.dataset.DataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * {@code DataRows} クラスは、データ行に関連する定義を提供するヘルパークラスです。
 * <p>
 * このクラスは {@link DataRow} オブジェクトのための共通定数や、
 * 共有リソースを効率的に管理するための基盤を提供します。
 */
public class DataRows {
    /**
     * 空の {@link DataRow} オブジェクトのリストを表す定数。
     * このリストは変更不可能です。
     * <p>
     * 主に、データ行が存在しない状態を明示的に示すために使用されます。
     */
    public static final List<DataRow> EMPTY = Collections.unmodifiableList(new ArrayList<>());
    private final List<DataRow> list = new ArrayList<>();

    public int size() {
        return list.size();
    }

    public DataRow get(int index) throws IndexOutOfBoundsException {
        return list.get(index);
    }

    public DataRow add(DataColumns columns) {
        DataRow result = new DataRowImpl(columns);
        list.add(result);
        return result;
    }
}
