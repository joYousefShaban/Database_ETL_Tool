package com.etl.tool.mappers.user;

import com.etl.tool.entities.DataRow;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//This mapper will be responsible for the migration from database to the program
public class UserDatabaseRowMapper implements RowMapper<DataRow> {
    @Override
    public DataRow mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        DataRow sourceDataRow = new DataRow();
        sourceDataRow.addValueInData("Id", resultSet.getString("Id"));
        sourceDataRow.addValueInData("UserName", resultSet.getString("UserName"));
        sourceDataRow.addValueInData("NormalizedUserName", resultSet.getString("NormalizedUserName"));
        sourceDataRow.addValueInData("Email", resultSet.getString("Email"));
        sourceDataRow.addValueInData("NormalizedEmail", resultSet.getString("NormalizedEmail"));
        sourceDataRow.addValueInData("EmailConfirmed", resultSet.getBoolean("EmailConfirmed"));
        sourceDataRow.addValueInData("PasswordHash", resultSet.getString("PasswordHash"));
        sourceDataRow.addValueInData("SecurityStamp", resultSet.getString("SecurityStamp"));
        sourceDataRow.addValueInData("ConcurrencyStamp", resultSet.getString("ConcurrencyStamp"));
        sourceDataRow.addValueInData("PhoneNumber", resultSet.getString("PhoneNumber"));
        sourceDataRow.addValueInData("PhoneNumberConfirmed", resultSet.getBoolean("PhoneNumberConfirmed"));
        sourceDataRow.addValueInData("TwoFactorEnabled", resultSet.getBoolean("TwoFactorEnabled"));
        sourceDataRow.addValueInData("LockoutEnd", resultSet.getString("LockoutEnd"));
        sourceDataRow.addValueInData("LockoutEnabled", resultSet.getBoolean("LockoutEnabled"));
        sourceDataRow.addValueInData("AccessFailedCount", resultSet.getObject("AccessFailedCount", Integer.class));
        sourceDataRow.addValueInData("Status", resultSet.getObject("Status", Integer.class));
        sourceDataRow.addValueInData("DateOfBirth", resultSet.getString("DateOfBirth"));
        sourceDataRow.addValueInData("FullName", resultSet.getString("FullName"));
        sourceDataRow.addValueInData("Photo", resultSet.getString("Photo"));
        sourceDataRow.addValueInData("GateID", resultSet.getObject("GateID", Integer.class));
        sourceDataRow.addValueInData("GateMonitoringType", resultSet.getObject("GateMonitoringType", Integer.class));

        return sourceDataRow;
    }
}