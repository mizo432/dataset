# プロジェクト概要

このプロジェクトは、`org.venus.shared.dataset` パッケージで利用するデータセット操作の共通モデルを提供する Java ライブラリです。
`DataTable` / `DataRow` / `DataColumn` / `DataValue` などのインターフェースと、それらの実装クラスを通じて、表形式データを扱うための基盤を提供します。

## 主な構成

- 公開 API（`src/main/java/org/venus/shared/dataset`）
    - `DataTable`, `DataRow`, `DataColumn`, `DataValue` などのインターフェース
    - `ColumnType`, `RowState` などの列挙
    - `ColumnNotFoundRuntimeException`, `PrimaryKeyNotFoundRuntimeException` などの例外
- 実装（`src/main/java/org/venus/shared/dataset/impl`）
    - `DataSetImpl`, `DataRowImpl`, `DataValueImpl` ほか、内部コレクションクラス群

## ビルドとテスト

- ビルドシステム: Gradle（`build.gradle.kts`）
- テスト: JUnit（`src/test/java/...`）

## 目的

データセットに関する共通仕様と実装を一箇所に集約し、他モジュールから一貫した方法でデータ行・列・値を扱えるようにすることを目的としています。