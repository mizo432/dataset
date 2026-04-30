package org.venus.shared.dataset.impl;

import org.jspecify.annotations.NonNull;
import org.venus.shared.dataset.*;

import java.sql.DatabaseMetaData;
import java.util.Iterator;

public class DataSetImpl implements DataTable, Iterable<DataRow> {

    private final DataColumns columns = DataColumns.empty();
    private boolean hasMetaData;
    private String tableName;
    private DataRows rows = new DataRows();

    @Override
    public @NonNull Iterator<DataRow> iterator() {
        return DataRows.EMPTY.iterator();
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public int getRowSize() {
        return rows.size();

    }

    @Override
    public DataRow getRow(int index) throws IndexOutOfBoundsException {

        return rows.get(index);
    }

    @Override
    public DataRow addRow() {
        return rows.add(this, columns);
    }

    @Override
    public int getRemovedRowSize() {
        return 0;
    }

    @Override
    public DataRow getRemovedRow(int index) {
        return null;
    }

    @Override
    public DataRow[] removeRows() {
        return new DataRow[0];
    }

    @Override
    public int getColumnSize() {
        return columns.size();
    }

    @Override
    public DataColumn getColumn(int index) throws IndexOutOfBoundsException {
        return columns.get(index);
    }

    @Override
    public DataColumn getColumn(String columnName) throws ColumnNotFoundRuntimeException {
        return columns.get(columnName);
    }

    @Override
    public boolean hasColumn(String columnName) {
        return columns.hasColumn(columnName);
    }

    @Override
    public String getColumnName(int index) throws IndexOutOfBoundsException {
        return columns.get(index).getColumnName();
    }

    @Override
    public ColumnType getColumnType(int index) throws IndexOutOfBoundsException {
        return columns.get(index).getColumnType();
    }

    @Override
    public ColumnType getColumnType(String columnName) throws ColumnNotFoundRuntimeException {
        return columns.get(columnName).getColumnType();
    }

    @Override
    public @NonNull DataColumn addColumn(@NonNull String columnName) {
        return columns.addColumn(columnName);
    }

    @Override
    public @NonNull DataColumn addColumn(@NonNull String columnName, @NonNull ColumnType columnType) {
        return columns.addColumn(columnName, columnType);
    }

    @Override
    public boolean hasMetaData() {
        return hasMetaData;
    }

    @Override
    public void setupMetaData(@NonNull DatabaseMetaData dbMetaData) {
        hasMetaData = true;

    }

    @Override
    public void setupColumns(Class<?> beanClass) {

    }

    @Override
    public void copyFrom(Object source) {

    }
}
