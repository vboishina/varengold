package org.sirma.app;

import org.sirma.items.EmployeItem;
import org.sirma.items.FieldItem;
import org.sirma.parser.CsvParser;
import org.sirma.sorter.ResultSorter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CVSFileParserImpl implements CVSFileParser {

    String absoluteFilePath;
    byte[] fileBytes;

    private Map<Integer, List<EmployeItem>> fieldItemsMap;


    private List<FieldItem> fieldItemList;

    public CVSFileParserImpl(String absoluteFilePath) {
        this.absoluteFilePath = absoluteFilePath;
    }

    public CVSFileParserImpl(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    @Override
    public List<FieldItem> parseCVSToFieldItemList() {
        fieldItemList = CsvParser.processInputFile(this.absoluteFilePath);
        return fieldItemList;
    }

    @Override
    public List<FieldItem> parseBytesToFieldItemList() {
        fieldItemList = CsvParser.processBytesInput(this.fileBytes);
        return fieldItemList;
    }


    @Override
    public void sortFieldItemList(List<FieldItem> fieldItemList) {
        ResultSorter.sortFieldItemsValues(fieldItemList);
    }

    @Override
    public List<FieldItem> pickFieldItemList(List<FieldItem> fieldItemList, int limit) {
        return ResultSorter.pickValues(fieldItemList, limit);
    }

    @Override
    public Map<Integer, List<EmployeItem>> getFieldItemsMap() {
        if (fieldItemList == null) {
            if (this.absoluteFilePath != null) {
                parseCVSToFieldItemList();
            } else if (this.fileBytes != null) {
                parseBytesToFieldItemList();
            }
            else return null;
        }
        List<EmployeItem> employeItemList = null;
        if (fieldItemsMap == null) {
            fieldItemsMap = new HashMap<>();
            for (FieldItem fieldItem : fieldItemList) {

                employeItemList = fieldItemsMap.get(fieldItem.getProjectId());

                if (employeItemList == null) {
                    employeItemList = new ArrayList<>();
                }

                EmployeItem employeItem = new EmployeItem();
                employeItem.setFieldItem(fieldItem);
                employeItem.setEmpId(fieldItem.getEmpId());
                employeItemList.add(employeItem);

                fieldItemsMap.put(fieldItem.getProjectId(), employeItemList);
            }

        }
        return fieldItemsMap;
    }


}
