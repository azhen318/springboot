package com.az.druid.handler;

import com.az.druid.eum.Sex;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.context.annotation.ComponentScan;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes({Sex.class})
public class SexTypeHandler extends BaseTypeHandler<Sex> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Sex sex, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,sex.getCode());
    }

    @Override
    public Sex getNullableResult(ResultSet resultSet, String s) throws SQLException {
       Integer code=resultSet.getInt(s);
        return Sex.getSexByCode(code);
    }

    @Override
    public Sex getNullableResult(ResultSet resultSet, int i) throws SQLException {
        Integer code=resultSet.getInt(i);
        return Sex.getSexByCode(code);
    }

    @Override
    public Sex getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        Integer code=callableStatement.getInt(i);
        return Sex.getSexByCode(code);
    }
}
