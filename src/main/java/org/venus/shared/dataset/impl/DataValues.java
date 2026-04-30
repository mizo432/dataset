package org.venus.shared.dataset.impl;

import org.jspecify.annotations.NonNull;
import org.venus.shared.dataset.DataColumn;
import org.venus.shared.dataset.DataValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * {@code DataValues} クラスは、複数の {@link DataValue} オブジェクトを管理し、操作するためのクラスです。
 * <p>
 * このクラスはイテラブルであり、{@link DataValue} オブジェクトのコレクションに対する
 * イテレーションを提供します。主に、データセットのカラムデータを一括で管理および操作する
 * 目的で設計されています。
 * <p>
 * 主な機能として以下を提供します：
 * - カラムデータの追加
 * - カラムデータの取得および更新
 * - カラム名を基にしたデータ値の検索と操作
 */
public class DataValues implements Iterable<DataValue> {
    private static final List<DataValue> EMPTY = Collections.unmodifiableList(new ArrayList<>());
    /**
     * {@link DataValue} オブジェクトを格納するためのリストを表す定数。
     * <p>
     * このリストは初期状態では空であり、主に内部操作やデータ管理のために利用されます。
     * {@code DataValues} クラス内で使用される内部的なデータ構造の一部として機能します。
     * <p>
     * このリストは {@code static final} 修飾子が指定されているため、クラスロード時に
     * 初期化され、その後は変更されません。
     */
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

    /**
     * 指定された {@link DataColumns} 内のすべてのカラムを内部リストに追加します。
     * <p>
     * 引数として渡された {@link DataColumns} 内に含まれる各 {@link DataColumn} を
     * 順に取り出し、それぞれを内部リストに追加します。このメソッドを使用することで、
     * 複数のカラムを一括して追加することができます。
     *
     * @param columns 追加対象となる {@link DataColumns} オブジェクト。{@code null} であってはなりません。
     *                また、空の {@link DataColumns} が渡された場合は実行結果に変更はありません。
     * @throws NullPointerException 引数が {@code null} の場合にスローされます。
     */
    public void addAllColumn(DataColumns columns) {
        for (int i = 0; i < columns.size(); i++) {
            addColumn(columns.get(i));
        }

    }

    /**
     * 指定された {@link DataColumn} を基に新しいデータ値を作成し、内部リストに追加します。
     *
     * @param column 追加する対象となる {@link DataColumn} オブジェクト。{@code null} であってはなりません。
     */
    public void addColumn(DataColumn column) {
        list.add(new DataValueImpl(column));
    }

    /**
     * 指定されたインデックスに基づいて {@link DataValue} オブジェクトを取得します。
     * <p>
     * インデックスは 0 から始まる形式で指定します。このメソッドは内部リストから
     * 対応する位置の {@link DataValue} を取得して返します。
     *
     * @param columnIndex 取得するデータ値のインデックス（0 から始まるインデックス）。
     *                    指定する値は 0 以上、リストのサイズ未満である必要があります。
     * @return 指定されたインデックスに対応する {@link DataValue} オブジェクト
     * @throws IndexOutOfBoundsException 指定されたインデックスが範囲外の場合
     */
    public DataValue get(int columnIndex) throws IndexOutOfBoundsException {
        return list.get(columnIndex);

    }

    /**
     * 指定されたカラム名に関連付けられた値を取得します。
     * <p>
     * 内部リスト内のすべての {@link DataValue} オブジェクトを調べ、
     * 対応するカラム名が一致するものを検索します。一致した場合、その値を返します。
     * 一致するカラムが存在しない場合は {@code null} を返します。
     *
     * @param columnName 値を取得する対象のカラム名。{@code null} ではない必要があります。
     * @return 指定されたカラム名に関連付けられた値。対応するカラムが見つからない場合は {@code null} を返します。
     */
    public Object getValue(String columnName) {
        for (DataValue value : list) {
            if (columnName.equals(value.column().getColumnName()))
                return value.getValue();
        }
        return null;
    }

    /**
     * 指定されたカラム名に対応するデータ値を更新します。
     * <p>
     * 内部リスト内の各 {@link DataValue} オブジェクトについて、
     * 指定されたカラム名に一致するカラムを持つデータ値を検索します。
     * 一致する場合、そのデータ値を新しい値に更新します。
     *
     * @param columnName 更新対象のカラムの名前。{@code null} ではない必要があります。
     * @param newValue   指定されたカラム名に関連付けられた新しい値。
     */
    public void setValue(@NonNull String columnName, Object newValue) {
        for (DataValue value : list) {
            if (columnName.equals(value.column().getColumnName())) {
                value.setValue(newValue);
                return;
            }
        }
    }


}
