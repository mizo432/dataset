package org.venus.shared.dataset;

import javax.sql.DataSource;

/**
 * {@code RowState} インターフェースは、データ行の状態を管理、制御するためのメソッドを定義します。
 * <p>
 * このインターフェースにより、行の状態に基づいた処理を実現することが可能です。
 * 主に、行データをデータベースに更新する操作を提供します。
 */
public enum RowState {
    UNKNOWN {
        @Override
        void update(DataSource dataSource, DataRow row) {
            throw new UnsupportedOperationException("RowState is unknown");
        }
    };

    /**
     * 行の内容をデータベースに更新します。
     *
     * @param dataSource データソース
     * @param row        行
     */
    abstract void update(DataSource dataSource, DataRow row);
}
