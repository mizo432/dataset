package org.venus.shared.dataset.impl;

import org.jspecify.annotations.NonNull;
import org.venus.shared.dataset.ColumnNotFoundRuntimeException;
import org.venus.shared.dataset.ColumnType;
import org.venus.shared.dataset.DataColumn;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code DataColumns} クラスは、複数の {@link DataColumn} オブジェクトを管理し、
 * カラム情報の操作や検索を提供するためのクラスです。
 * <p>
 * 主にデータセットに関連するカラム情報をまとめて操作する際に使用されます。
 * このクラスは不変オブジェクトとして設計されており、基本的な操作はカラムの
 * 追加、取得、存在確認を含みます。
 */
public class DataColumns {
    /**
     * {@code list} は、{@link DataColumn} オブジェクトを格納するためのリストです。
     * <p>
     * このリストはスレッドセーフではないため、アクセス時に注意が必要です。
     * 主にカラム情報を保持するために内部的に使用されます。
     */
    private final List<DataColumn> list = new ArrayList<>();

    /**
     * DataColumnsクラスのコンストラクタです。
     * <p>
     * このクラスはインスタンス化を目的としていないため、コンストラクタはプライベート
     * アクセス修飾子で定義されています。
     * <p>
     * DataColumnsは静的メソッドまたは定数を提供するユーティリティクラスとして設計されています。
     */
    private DataColumns() {
    }

    /**
     * 空の {@link DataColumns} インスタンスを返します。
     * <p>
     * このメソッドは、新しい空の {@link DataColumns} オブジェクトを生成します。
     * {@link DataColumns} はカラム情報を管理するクラスであり、このメソッドを
     * 使用することで、初期状態の空のコレクションを用意できます。
     *
     * @return 空の {@link DataColumns} インスタンス
     */
    public static DataColumns empty() {
        return new DataColumns();
    }

    /**
     * 保持しているカラムの数を返します。
     * このメソッドは内部リストのサイズを基にカラム数を計算します。
     *
     * @return カラムの数
     */
    public int size() {
        return list.size();
    }

    /**
     * 指定されたインデックスに位置する {@link DataColumn} を返します。
     * <p>
     * インデックスは0から始まる値で指定します。指定されたインデックスが範囲外の場合、
     * {@link IndexOutOfBoundsException} がスローされます。
     *
     * @param index 取得するカラムのインデックス
     * @return 指定されたインデックスに対応する {@link DataColumn}
     * @throws IndexOutOfBoundsException インデックスが範囲外の場合にスローされます
     */
    public DataColumn get(int index) throws IndexOutOfBoundsException {
        return list.get(index);
    }

    /**
     * 指定されたカラム名に対応する {@link DataColumn} を返します。
     * <p>
     * 内部リストから指定されたカラム名に合致するカラムを検索し、最初に一致した
     * カラムを返します。もし該当するカラムが存在しない場合は、{@link ColumnNotFoundRuntimeException}
     * がスローされます。
     *
     * @param columnName 取得したいカラムの名前
     * @return 指定されたカラム名に一致する {@link DataColumn}
     * @throws ColumnNotFoundRuntimeException 指定されたカラムが存在しない場合にスローされます
     */
    public DataColumn get(String columnName) throws ColumnNotFoundRuntimeException {
        return list.stream().filter(column -> column.getColumnName().equals(columnName)).findFirst().orElseThrow(() -> new ColumnNotFoundRuntimeException(columnName));

    }

    /**
     * 指定されたカラム名を持つカラムが存在するかを判定します。
     * <p>
     * 内部に保持しているカラムのリストに対して指定されたカラム名を検索し、
     * 一致するカラムが存在する場合に {@code true} を返します。
     *
     * @param columnName 確認したいカラムの名前
     * @return 指定されたカラム名を持つカラムが存在する場合は {@code true}、存在しない場合は {@code false}
     */
    public boolean hasColumn(String columnName) {
        return list
                .stream()
                .anyMatch(column -> column.getColumnName()
                        .equals(columnName));
    }

    /**
     * 指定されたカラム名を持つ新しい {@link DataColumn} を追加します。
     * <p>
     * このメソッドは指定された名前で新しいカラムを作成し、デフォルトの型として {@link ColumnType#NULL}
     * を割り当てたカラムを返します。
     *
     * @param columnName 追加するカラムの名前（null ではない文字列）
     * @return 作成された {@link DataColumn} オブジェクト（null ではない）
     */
    public @NonNull DataColumn addColumn(@NonNull String columnName) {
        return new DataColumnImpl(columnName, ColumnType.NULL);
    }

    /**
     * 指定された名前および型を持つ新しい {@link DataColumn} を追加します。
     * <p>
     * このメソッドは、引数として提供されたカラム名とカラム型を使用して
     * 新しい {@link DataColumn} オブジェクトを作成し、返します。
     *
     * @param columnName 追加するカラムの名前（null ではない文字列）
     * @param columnType 追加するカラムの型（null ではない {@link ColumnType}）
     * @return 作成された {@link DataColumn} オブジェクト（null ではない）
     */
    public @NonNull DataColumn addColumn(@NonNull String columnName, @NonNull ColumnType columnType) {
        return new DataColumnImpl(columnName, columnType);

    }
}
