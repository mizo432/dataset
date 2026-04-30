package org.venus.shared.dataset;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@code ColumnNotFoundRuntimeExceptionTest} クラスは、カラムが存在しないケースにおける
 * {@link ColumnNotFoundRuntimeException} の動作を検証するためのテストクラスです。
 * <p>
 * このクラスは、カラムが見つからない場合に例外が正しくスローされることを確認する
 * 単体テストを目的としています。テストケースでは、例外のインスタンス生成と
 * 期待されるメッセージの確認を行います。
 * <p>
 * 主に以下の検証を実施します:
 * <ul>
 * <li>例外インスタンスが正しく生成されること。</li>
 * <li>例外メッセージが期待される形式で含まれること。</li>
 * </ul>
 */
class ColumnNotFoundRuntimeExceptionTest {


    @Nested
    class ConstructorTest {
        /**
         * {@code test} メソッドは、{@link ColumnNotFoundRuntimeException} の動作を検証する単体テストです。
         * <p>
         * 主な検証内容は以下の通りです:
         * <ul>
         * <li>例外が生成される際に {@code null} ではないことを確認します。</li>
         * <li>例外メッセージが「Column not found: colName」と正しく設定されていることを確認します。</li>
         * </ul>
         * <p>
         * 本メソッドでは、以下の条件を基にテストしています:
         * <ol>
         * <li>"colName" というカラム名を指定して {@link ColumnNotFoundRuntimeException}
         * インスタンスを生成します。</li>
         * <li>生成された例外オブジェクトが {@code null} ではないことを検証します。</li>
         * <li>例外メッセージが期待通りであることを検証します。</li>
         * </ol>
         * <p>
         * このテストは、小規模な単体テストとして分類されます。
         */
        @Test
        @Tag("small")
        void shouldSetMessageWithColumnName() {
            // given & when
            ColumnNotFoundRuntimeException target = new ColumnNotFoundRuntimeException("colName");

            // then
            assertThat(target).isNotNull();
            assertThat(target.getMessage()).isEqualTo("Column not found: colName");

        }
    }
}
