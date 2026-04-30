package org.venus.shared.dataset;

/**
 * 指定されたカラム名が存在しない場合にスローされる例外です。
 * <p>
 * 主にデータ操作の際に、指定されたカラム名に対応するカラムが見つからないケースで
 * 使用されます。この例外は実行時例外（RuntimeException）の一種であり、データモデルや
 * データセット操作におけるエラーハンドリングに役立ちます。
 * <p>
 * コンストラクタでは、例外メッセージに該当するカラム名を含む詳細情報を提供します。
 */
public class ColumnNotFoundRuntimeException extends RuntimeException {
    /**
     * 指定されたカラム名が見つからない場合にスローされるランタイム例外です。
     *
     * @param columnName 存在しないカラムの名前
     */
    public ColumnNotFoundRuntimeException(String columnName) {
        super("Column not found: " + columnName);
    }
}
