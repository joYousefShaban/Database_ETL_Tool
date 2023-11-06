package com.etl.tool.mappers.rank;

import com.etl.tool.entities.DataRow;
import com.etl.tool.mappers.EntityMapper;

//This mapper will be responsible for the mapping between the source and destination databases
public class RankEntityMapper extends EntityMapper {
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