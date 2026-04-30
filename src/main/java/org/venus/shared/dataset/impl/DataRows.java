package org.venus.shared.dataset.impl;

import org.jspecify.annotations.NonNull;
import org.venus.shared.dataset.DataRow;
import org.venus.shared.dataset.DataTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * {@code DataRows} クラスは、複数のデータ行（{@link DataRow}）を管理するためのコンテナクラスです。
 * <p>
 * このクラスは、データ行の追加、取得、行数の確認など、各種のデータ操作を提供します。
 * 内部的には {@link ArrayList} を利用してデータ行を格納しています。
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

    /**
     * 保持しているデータ行の数を返します。
     * このメソッドは内部リストのサイズを基に行数を計算します。
     *
     * @return データ行の数
     */
    public int size() {
        return list.size();
    }

    /**
     * 指定されたインデックスに基づいてデータ行を返します。
     * <p>
     * インデックスは 0 から始まる形式で指定します。このメソッドは内部リストから
     * 対応する位置の {@link DataRow} を取得して返します。
     *
     * @param rowIndex 取得するデータ行のインデックス（0 から始まるインデックス）
     * @return 指定されたインデックスに対応する {@link DataRow}
     * @throws IndexOutOfBoundsException 指定されたインデックスが範囲外の場合
     */
    public DataRow get(int rowIndex) throws IndexOutOfBoundsException {
        return list.get(rowIndex);
    }

    /**
     * 指定されたデータテーブルとカラムを基に新しい {@code DataRow} を作成し、リストに追加します。
     *
     * @param dataTable データ行が属する {@link DataTable} オブジェクト
     * @param columns   データ行で使用する {@link DataColumns} オブジェクト
     * @return 追加された新しい {@link DataRow} インスタンス
     */
    public DataRow add(@NonNull DataTable dataTable, @NonNull DataColumns columns) {
        DataRow result = new DataRowImpl(dataTable, columns);
        list.add(result);
        return result;
    }
}
