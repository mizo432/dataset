package org.venus.shared.dataset;

import java.sql.DatabaseMetaData;

/**
 * データセットを表すインタフェースです。
 * <p>
 * このインタフェースは、データの集合を管理するための基本的なAPIを提供します。
 * 実装クラスは、データセットに関する具体的な操作と振る舞いを提供する必要があります。
 */
public interface DataSet {
    /**
     * テーブル名を返します。
     *
     * @return テーブル名
     */
    String getTableName();

    /**
     * テーブル名を設定します。
     *
     * @param tableName テーブル名
     */
    void setTableName(String tableName);

    /**
     * データセット内の行の総数を返します。
     *
     * @return データセット内の行の数
     */
    int getRowSize();

    /**
     * 指定されたインデックスに基づいてデータ行を取得します。
     * <p>
     * インデックスは 0 から始まる形式で指定する必要があります。
     * このメソッドを使用してデータセット内の特定の行を検索できます。
     *
     * @param index データセット内の対象行のインデックス（0 から始まるインデックス）
     * @return 指定されたインデックスに対応する {@code DataRow} オブジェクト
     * @throws IndexOutOfBoundsException 指定されたインデックスが範囲外の場合
     */
    DataRow getRow(int index);

    /**
     * 新しい行をデータセットに追加します。
     * <p>
     * このメソッドを呼び出すと、新しい {@code DataRow} インスタンスが作成され、
     * データセットに追加されます。追加された行は、データセットの行として取り扱えます。
     *
     * @return 追加された新しい {@code DataRow} インスタンス
     */
    DataRow addRow();

    /**
     * 削除された行の総数を返します。
     * <p>
     * このメソッドは、データセットから削除された行の数を追跡し、
     * 削除済みの行数を取得するために使用されます。
     *
     * @return データセットで削除された行の総数
     */
    int getRemovedRowSize();

    /**
     * 指定されたインデックスに基づいて削除された行を返します。
     * <p>
     * このメソッドは、以前にデータセットから削除された {@code DataRow} を
     * 取得するために使用されます。インデックスは 0 から始まり、削除された
     * 行の数を超えない範囲で指定する必要があります。
     *
     * @param index 削除された行のインデックス（0 から始まるインデックス）
     * @return 指定されたインデックスに対応する削除された {@code DataRow} インスタンス
     * @throws IndexOutOfBoundsException 指定されたインデックスが範囲外の場合
     */
    DataRow getRemovedRow(int index);

    /**
     * データセットからすべての行を削除し、削除された行を返します。
     * <p>
     * このメソッドを呼び出すと、データセット内のすべてのデータ行が削除され、
     * 削除された行は配列として返されます。
     *
     * @return 削除された {@code DataRow} の配列
     */
    DataRow[] removeRows();

    /**
     * データセットに含まれるカラムの数を返します。
     *
     * @return カラムの数
     */
    int getColumnSize();

    /**
     * 指定されたインデックスに基づいてカラムを取得します。
     *
     * @param index カラムのインデックス（0から始まるインデックス）
     * @return 指定されたインデックスの {@code DataColumn} オブジェクト
     * @throws IndexOutOfBoundsException インデックスが範囲外の場合
     */
    DataColumn getColumn(int index);

    /**
     * 指定されたカラム名に基づいてカラムを取得します。
     *
     * @param columnName カラム名
     * @return 指定されたカラム名に対応する {@code DataColumn} オブジェクト
     * @throws ColumnNotFoundRuntimeException 指定されたカラム名に対応するカラムが見つからない場合
     */
    DataColumn getColumn(String columnName)
            throws ColumnNotFoundRuntimeException;

    /**
     * カラムを持っているかどうかを返します。
     *
     * @param columnName カラム名
     * @return カラムを持っているかどうか
     */
    boolean hasColumn(String columnName);

    /**
     * 指定されたインデックスに基づいてカラム名を返します。
     *
     * @param index カラムのインデックス（0から始まるインデックス）
     * @return 指定されたインデックスのカラム名
     */
    String getColumnName(int index);

    /**
     * 指定された位置に基づいてカラムの型を返します。
     *
     * @param index 取得するカラムの位置（0から始まるインデックス）
     * @return 指定された位置のカラムの型
     */
    ColumnType getColumnType(int index);

    /**
     * 指定されたカラム名に対応するカラムの型を返します。
     *
     * @param columnName カラム名
     * @return 指定されたカラムの型
     */
    ColumnType getColumnType(String columnName);

    /**
     * 新しいカラムをデータセットに追加します。
     *
     * @param columnName 追加するカラムの名前
     * @return 追加されたカラム
     */
    DataColumn addColumn(String columnName);

    /**
     * 指定されたカラム名とカラム型を持つ新しいカラムをデータセットに追加します。
     *
     * @param columnName 新しいカラムの名前
     * @param columnType 新しいカラムの型
     * @return 追加されたカラム
     */
    DataColumn addColumn(String columnName, ColumnType columnType);

    /**
     * メタデータを持っているかどうかを返します。
     *
     * @return メタデータを持っているかどうか
     */
    boolean hasMetaData();

    /**
     * メタデータのセットアップを行ないます。
     *
     * @param dbMetaData データベースのメタデータ
     */
    void setupMetaData(DatabaseMetaData dbMetaData);

    /**
     * 指定されたクラス情報に基づいてデータセットのカラムをセットアップします。
     * <p>
     * このメソッドは、指定されたクラスのプロパティを分析し、それに基づいて対応する
     * カラムをデータセットに追加します。データセットのレイアウトは、このメソッドを
     * 実行することで指定されたクラス構造に基づいたものに初期化されます。
     *
     * @param beanClass プロパティ情報を元にカラムを生成するための対象クラス
     */
    void setupColumns(Class<?> beanClass);

    /**
     * 他のオブジェクトから値をコピーします。
     *
     * @param source 他のオブジェクト
     */
    void copyFrom(Object source);
}
