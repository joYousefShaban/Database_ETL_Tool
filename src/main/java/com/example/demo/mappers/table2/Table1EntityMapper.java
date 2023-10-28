package com.example.demo.mappers.table2;

import com.example.demo.entities.DataRow;
import com.example.demo.mappers.EntityMapper;

//This mapper will be responsible for the mapping between the source and destination databases
public class Table1EntityMapper extends EntityMapper {
    public DataRow transformToEntity(DataRow sourceRow) {
        DataRow destinationDataRow = new DataRow();
        destinationDataRow.addValueInData("active_status", sourceRow.getData().get("RecordStatus"));
        destinationDataRow.addValueInData("creation_date", sourceRow.getData().get("CreationDate"));
        destinationDataRow.addValueInData("modification_date", sourceRow.getData().get("ModificationDate"));
        destinationDataRow.addValueInData("create_by_id", sourceRow.getData().get("CreatedByUserID"));
        destinationDataRow.addValueInData("modified_by_id", sourceRow.getData().get("ModifiedByUserID"));
        destinationDataRow.addValueInData("title_ar", sourceRow.getData().get("TitleAR"));
        destinationDataRow.addValueInData("title_en", sourceRow.getData().get("TitleEN"));
        destinationDataRow.addValueInData("id", transferToInt(sourceRow.getData().get("RankID")));

        return destinationDataRow;
    }
}