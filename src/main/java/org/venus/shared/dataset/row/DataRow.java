package org.venus.shared.dataset.row;

import org.venus.shared.dataset.value.DataValue;
import org.venus.shared.dataset.value.DataValues;

/**
 * データ行を表現するためのインタフェースです。
 * <p>
 * このインタフェースは、1行分のデータを管理する基本的なAPIを提供します。
 * 主にデータセット内の個々の行を表現し、それぞれの列データを操作するために利用されます。
 * 実装クラスは、具体的なデータ行の構造や振る舞いを定義する必要があります。
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
}
