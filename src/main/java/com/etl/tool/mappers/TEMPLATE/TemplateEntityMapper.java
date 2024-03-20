package com.etl.tool.mappers.TEMPLATE;

import com.etl.tool.entities.DataRow;
import com.etl.tool.mappers.EntityMapper;

import java.util.List;
import java.util.Map;

//This mapper will be responsible for the mapping between the application and destination databases
public class TemplateEntityMapper extends EntityMapper {

    @Override
    public DataRow transformToEntity(DataRow sourceRow, Map<String, List<DataRow>> extractedData) {
        DataRow destinationDataRow = new DataRow();
        destinationDataRow.addValue("id", sourceRow.getData().get("Id"));
        destinationDataRow.addValue("name", sourceRow.getData().get("Name"));

        /*// in case data is needed from external tables
        extractedData.getOrDefault("source2", List.of()).stream()
                .filter(row -> row.getData().get("UserId").equals(sourceRow.getData().get("Id")))
                .findFirst()
                .ifPresentOrElse(
                        dataRow -> destinationDataRow.addValue("group_id", dataRow.getData().get("RoleId")),
                        () -> destinationDataRow.addValue("group_id", null)
                );*/

        return destinationDataRow;
    }
}