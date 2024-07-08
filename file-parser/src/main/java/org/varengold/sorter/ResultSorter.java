package org.sirma.sorter;

import org.sirma.items.FieldItem;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ResultSorter {

    public static void sortFieldItemsValues(List<FieldItem> inputList) {
        Comparator<FieldItem> comparator = Comparator.comparing(FieldItem::getDuration);
        inputList.sort(comparator);
        Collections.reverse(inputList);
    }

    public static List<FieldItem> pickValues(List<FieldItem> list, int limit) {
       return list.stream().limit(limit).collect(Collectors.toList());
    }

}
