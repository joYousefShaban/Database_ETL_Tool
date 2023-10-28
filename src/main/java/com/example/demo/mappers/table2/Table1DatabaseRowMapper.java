package com.example.demo.mappers.table2;

import com.example.demo.entities.DataRow;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//This mapper will be responsible for the migration from database to the program
public class Table1DatabaseRowMapper implements RowMapper<DataRow> {
    @Override
    public DataRow mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        DataRow sourceDataRow = new DataRow();
        sourceDataRow.addValueInData("RankID", resultSet.getInt("RankID"));
        sourceDataRow.addValueInData("CreatedByUserID", resultSet.getString("CreatedByUserID"));
        sourceDataRow.addValueInData("CreationDate", resultSet.getString("CreationDate"));
        sourceDataRow.addValueInData("RecordStatus", resultSet.getInt("RecordStatus"));
        sourceDataRow.addValueInData("ModifiedByUserID", resultSet.getString("ModifiedByUserID"));
        sourceDataRow.addValueInData("ModificationDate", resultSet.getString("ModificationDate"));
        sourceDataRow.addValueInData("TitleAR", resultSet.getString("TitleAR"));
        sourceDataRow.addValueInData("TitleEN", resultSet.getString("TitleEN"));

        return sourceDataRow;
    }
}