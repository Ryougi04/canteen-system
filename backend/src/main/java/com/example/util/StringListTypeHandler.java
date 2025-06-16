package com.example.util;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class StringListTypeHandler extends BaseTypeHandler<List<String>> {
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
    // 将 List 转为 PostgreSQL 数组
    ps.setArray(i, ps.getConnection().createArrayOf("varchar", parameter.toArray()));
  }

  @Override
  public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
    // 从 PostgreSQL 数组转回 List
    Array array = rs.getArray(columnName);
    return array == null ? null : Arrays.asList((String[]) array.getArray());
  }

  @Override
  public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    Array array = rs.getArray(columnIndex);
    return array == null ? null : Arrays.asList((String[]) array.getArray());
  }

  @Override
  public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    Array array = cs.getArray(columnIndex);
    return array == null ? null : Arrays.asList((String[]) array.getArray());
  }
}