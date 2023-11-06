package com.etl.tool.mappers.user;

import com.etl.tool.entities.DataRow;
import com.etl.tool.mappers.EntityMapper;

//This mapper will be responsible for the mapping between the source and destination databases
public class UserEntityMapper extends EntityMapper {
    public DataRow transformToEntity(DataRow sourceRow) {
        DataRow destinationDataRow = new DataRow();
        destinationDataRow.addValueInData("is_active", !(boolean) sourceRow.getData().get("LockoutEnabled"));
        destinationDataRow.addValueInData("date_of_birth", sourceRow.getData().get("DateOfBirth"));
        destinationDataRow.addValueInData("gate_id", transferToLong(sourceRow.getData().get("GateID")));
        destinationDataRow.addValueInData("gate_monitoring_id", transferToLong(sourceRow.getData().get("GateMonitoringType")));
        destinationDataRow.addValueInData("email", sourceRow.getData().get("Email"));
        destinationDataRow.addValueInData("name", sourceRow.getData().get("FullName"));
        destinationDataRow.addValueInData("password", sourceRow.getData().get("PasswordHash"));
        destinationDataRow.addValueInData("phone_number", sourceRow.getData().get("PhoneNumber"));
        destinationDataRow.addValueInData("username", sourceRow.getData().get("UserName"));
        destinationDataRow.addValueInData("id", sourceRow.getData().get("Id"));

        return destinationDataRow;
    }
}