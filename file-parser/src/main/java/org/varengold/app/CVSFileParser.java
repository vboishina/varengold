package org.sirma.app;

import org.sirma.items.EmployeItem;
import org.sirma.items.FieldItem;

import java.util.List;
import java.util.Map;

public interface CVSFileParser {

    List<FieldItem> parseCVSToFieldItemList();

    List<FieldItem> parseBytesToFieldItemList();

    void sortFieldItemList(List<FieldItem> fieldItemList);

    List<FieldItem> pickFieldItemList(List<FieldItem> fieldItemList, int limit);

    Map<Integer, List<EmployeItem>> getFieldItemsMap();


}
