package com.example.demo.mappers.menu;

import com.example.demo.entities.DataRow;
import com.example.demo.mappers.IEntityMapper;

//This mapper will be responsible for the mapping between the source and destination databases
public class MenuEntityMapper implements IEntityMapper {
    public DataRow transformToEntity(DataRow sourceRow) {
        DataRow destinationDataRow = new DataRow();
        destinationDataRow.addValueInData("order_", sourceRow.getData().get("Order"));
        destinationDataRow.addValueInData("parent_id", sourceRow.getData().get("ParentID"));
        destinationDataRow.addValueInData("module_routing", sourceRow.getData().get("ModuleRouting"));
        destinationDataRow.addValueInData("page_routing", sourceRow.getData().get("PageRouting"));
        destinationDataRow.addValueInData("permission_code", sourceRow.getData().get("PermissionCode"));
        destinationDataRow.addValueInData("title_ar", sourceRow.getData().get("TitleAR"));
        destinationDataRow.addValueInData("title_en", sourceRow.getData().get("TitleEN"));
        destinationDataRow.addValueInData("id", sourceRow.getData().get("MenuID"));

        return destinationDataRow;
    }
}