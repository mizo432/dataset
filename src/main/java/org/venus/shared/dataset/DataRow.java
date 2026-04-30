package org.venus.shared.dataset;

import org.venus.shared.dataset.impl.DataValues;

/**
 * {@code DataRow} インターフェースは、データセット内の単一の行を表現します。
 * <p>
 * このインターフェースは、データ行に関連する操作を包括的に定義しています。
 * これには、データの取得・設定、関連オブジェクトの取得、行の状態管理などが含まれます。
 * <p>
 * 主に以下の機能を提供します:
 * <ul>
 * <li>指定されたカラムやインデックスに基づいて値を取得・設定する</li>
 * <li>現在の行に関連付けられた {@link DataSet}, {@link DataValues}, または状態情報を取得する</li>
 * <li>他のオブジェクトからデータをコピーする</li>
 * <li>行自体を削除する操作</li>
 * </ul>
 * <p>
 * 使用中に例外がスローされる場合があります。適切なハンドリングが必要です。
 */
public interface DataRow {

    /**
     * このメソッドは現在のデータ行に関連付けられた {@link DataValues} オブジェクトを返します。
     * <p>
     * {@link DataValues} は複数の {@link DataValue} オブジェクトを格納し、
     * それらにアクセス可能な形式を提供するクラスです。
     *
     * @return データ行に紐付けられた {@link DataValues} オブジェクト
     */
    DataValues values();

    /**
     * 指定されたカラムインデックスに基づいて値を取得します。
     * <p>
     * カラムインデックスは 0 から始まる形式で指定します。このメソッドは、データセット内で
     * 対応するカラムの値を特定し、その値を返します。
     *
     * @param columnIndex 取得する値のカラムインデックス（0 から始まるインデックス）
     * @return 指定されたカラムインデックスに対応する値
     * @throws IndexOutOfBoundsException 指定されたカラムインデックスが範囲外の場合
     */
    Object getValue(int columnIndex) throws IndexOutOfBoundsException;

    /**
     * 値を返します。
     *
     * @param columnName カラム名
     * @return 値
     * @throws ColumnNotFoundRuntimeException カラムが見つからなかった場合
     */
    Object getValue(String columnName) throws ColumnNotFoundRuntimeException;

    /**
     * 値を設定します。
     *
     * @param index 位置
     * @param value 値
     */
    void setValue(int index, Object value) throws IndexOutOfBoundsException;

    /**
     * 値を設定します。
     *
     * @param columnName カラム名
     * @param value      値
     * @throws ColumnNotFoundRuntimeException カラムが見つからなかった場合
     */
    void setValue(String columnName, Object value)
            throws ColumnNotFoundRuntimeException;

    /**
     * 削除します。
     */
    void remove();

    /**
     * データセットを返します。
     *
     * @return データセット
     */
    DataSet getSet();

    /**
     * 行の状態を返します。
     *
     * @return 行の状態
     */
    RowState getState();

    /**
     * 行の状態を設定します。
     *
     * @param rowState 行の状態
     */
    void setState(RowState rowState);

    /**
     * 他から値をコピーします。
     *
     * @param source 他のデータ
     */
    void copyFrom(Object source);
}
