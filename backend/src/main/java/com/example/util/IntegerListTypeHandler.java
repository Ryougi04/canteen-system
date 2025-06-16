package com.example.util;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntegerListTypeHandler extends BaseTypeHandler<List<Integer>> {

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, List<Integer> parameter, JdbcType jdbcType) throws SQLException {
    // 将 List<Integer> 转换为 PostgreSQL integer[]
    Integer[] array = parameter.toArray(new Integer[0]);
    ps.setArray(i, ps.getConnection().createArrayOf("integer", array));
  }

  @Override
  public List<Integer> getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return arrayToList(rs.getArray(columnName));
  }

  @Override
  public List<Integer> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return arrayToList(rs.getArray(columnIndex));
  }

  @Override
  public List<Integer> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return arrayToList(cs.getArray(columnIndex));
  }

  private List<Integer> arrayToList(Array pgArray) throws SQLException {
    if (pgArray == null) {
      return new ArrayList<>();
    }
    // PostgreSQL JDBC 返回的是 Integer[] 数组
    Integer[] array = (Integer[]) pgArray.getArray();
    return new ArrayList<>(Arrays.asList(array));
  }
}