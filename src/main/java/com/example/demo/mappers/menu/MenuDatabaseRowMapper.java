package com.example.demo.mappers.menu;

import com.example.demo.entities.DataRow;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//This mapper will be responsible for the migration from database to the program
public class MenuDatabaseRowMapper implements RowMapper<DataRow> {
    @Override
    public DataRow mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        DataRow sourceDataRow = new DataRow();
        sourceDataRow.addValueInData("MenuID", resultSet.getObject("MenuID", Integer.class));
        sourceDataRow.addValueInData("TitleAR", resultSet.getString("TitleAR"));
        sourceDataRow.addValueInData("TitleEN", resultSet.getString("TitleEN"));
        sourceDataRow.addValueInData("ModuleRouting", resultSet.getString("ModuleRouting"));
        sourceDataRow.addValueInData("PageRouting", resultSet.getString("PageRouting"));
        sourceDataRow.addValueInData("ParentID", resultSet.getObject("ParentID", Integer.class));
        sourceDataRow.addValueInData("Order", resultSet.getObject("Order", Integer.class));
        sourceDataRow.addValueInData("PermissionCode", resultSet.getString("PermissionCode"));

        return sourceDataRow;
    }
}