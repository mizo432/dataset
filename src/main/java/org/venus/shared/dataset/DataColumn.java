package org.venus.shared.dataset;

/**
 * データセット内の単一の列を表すためのインターフェースです。
 * <p>
 * DataColumnインターフェースは、列の基本情報（名前、位置、型、主キー、更新可能性など）や、
 * 列データの変換やフォーマット情報を管理するためのメソッドを提供します。このインターフェースを
 * 実装することで、列のプロパティを操作および管理する統一的な方法を提供します。
 * <p>
 * 主な利用例として、データセット内の列情報を抽象化し、各インスタンスが異なる列型や構造を
 * 持つ場合でも統一的に操作できるようにすることが挙げられます。
 */
public interface DataColumn {
    /**
     * カラム名を返します。
     *
     * @return カラム名
     */
    String getColumnName();

    /**
     * カラムの位置を返します。
     *
     * @return カラムの位置
     */
    int getColumnIndex();

    /**
     * カラムの型を返します。
     *
     * @return カラムの型
     */
    ColumnType getColumnType();

    /**
     * カラムの型を設定します。
     *
     * @param columnType カラムの型
     */
    void setColumnType(ColumnType columnType);

    /**
     * プライマリキーかどうかを返します。
     *
     * @return プライマリキーかどうか
     */
    boolean isPrimaryKey();

    /**
     * プライマリーキーかどうかを設定します。
     *
     * @param primaryKey プライマリキーかどうか
     */
    void setPrimaryKey(boolean primaryKey);

    /**
     * 更新可能かどうかを返します。
     *
     * @return 更新可能かどうか
     */
    boolean isWritable();

    /**
     * 更新可能かどうかを設定します。
     *
     * @param writable 更新可能かどうか
     */
    void setWritable(boolean writable);

    /**
     * フォーマットパターンを返します。
     *
     * @return フォーマットパターン
     */
    String getFormatPattern();

    /**
     * フォーマットパターンを設定します。
     *
     * @param formatPattern フォーマットパターン
     */
    void setFormatPattern(String formatPattern);

    /**
     * 値を適切な型に変換します。
     *
     * @param value 値
     * @return 変換後の値
     */
    Object convert(Object value);
}
