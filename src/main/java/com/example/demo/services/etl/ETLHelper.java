package com.example.demo.services.etl;

import com.example.demo.entities.DataRow;
import com.example.demo.services.logging.ANSI;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Service
@Log4j2
public class ETLHelper implements IETLHelper {

    protected ETLHelper() {

    }

    //The importance of sorting the data lies on the possibility of being Foreign keys mapped in the table itself
    protected static void sortData(List<DataRow> destinationTableData, String columnToCompare) {
        //lambda expression to override compare method
        destinationTableData.sort((row1, row2) -> {
            Object value1 = row1.getData().get(columnToCompare);
            Object value2 = row2.getData().get(columnToCompare);

            if (value1 == null && value2 == null) {
                return 0;
            } else if (value1 == null) {
                return -1;
            } else if (value2 == null) {
                return 1;
            }

            return value1.toString().compareTo(value2.toString());
        });
    }

    protected static String generateUpdateSQL(String destinationTableName, DataRow sampleData) {
        try {
            StringJoiner setClause = new StringJoiner(", ");

            sampleData.getData().forEach((key, value) -> {
                if (!Objects.equals(key, "id")) {
                    setClause.add(key + " = ?");
                }
            });
            return String.format("UPDATE %s SET %s WHERE id = ?", destinationTableName, setClause);
        } catch (Exception e) {
            log.fatal(ANSI.colour("SERVICE OF SQL GENERATION: The update sql statement generation for: \"" + sampleData.getData() + "\" to \"" + destinationTableName + "\" table failed, and generated the following exception: \r\n" + e.getMessage(), ANSI.RED_BOLD));
        }
        return null;
    }

    protected static String generateInsertSQL(String destinationTableName, DataRow sampleData) {
        try {
            StringJoiner columnNames = new StringJoiner(", ");
            StringJoiner placeholders = new StringJoiner(", ");

            sampleData.getData().keySet().forEach(key -> {
                columnNames.add(key);
                placeholders.add("?");
            });
            return String.format("INSERT INTO %s (%s) VALUES (%s)", destinationTableName, columnNames, placeholders);
        } catch (Exception e) {
            log.fatal(ANSI.colour("SERVICE OF SQL GENERATION: The insert sql statement generation for: \"" + sampleData.getData() + "\" to \"" + destinationTableName + "\" table failed, and generated the following exception: \r\n" + e.getMessage(), ANSI.RED_BOLD));
        }
        return null;
    }
}