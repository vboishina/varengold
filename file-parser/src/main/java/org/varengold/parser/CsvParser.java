package org.sirma.parser;

import org.sirma.items.FieldItem;
import org.sirma.sorter.ResultSorter;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CsvParser {

    public static List<FieldItem> processInputFile(String inputFilePath) {

        List<FieldItem> inputList = new ArrayList<>();

        try {

            File inputF = new File(inputFilePath);
            InputStream inputFS = Files.newInputStream(inputF.toPath());
            inputList = process(inputFS.readAllBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputList;
    }

    public static List<FieldItem> processBytesInput(byte[] fileBytes) {
        return process(fileBytes);
    }

    public static List<FieldItem> process(byte[] inputFileBytes) {
        InputStream inputFS = new ByteArrayInputStream(inputFileBytes);
        try {
            return parseStream(inputFS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<FieldItem> parseStream(InputStream inputFS) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
        List<FieldItem> inputList = null;
        // skip the header of the csv
        inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
        br.close();
        return inputList;
    }

    private static final Function<String, FieldItem> mapToItem = (line) -> {

        String[] p = line.split(",");// a CSV has comma separated lines

        FieldItem item = new FieldItem();

        item.setEmpId(Integer.valueOf(p[0] != null ? p[0].trim() : "-1"));

        item.setProjectId(Integer.valueOf(p[1] != null ? p[1].trim() : "-1"));

        String customDateString = p[2] != null ? p[2].trim() : null;
        if (customDateString != null && !customDateString.equals("NULL")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate customDate = LocalDate.parse(customDateString, formatter);

            item.setDateFrom(Date.from(customDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        } else item.setDateFrom(new Date());


        String customDateToString = p[3] != null ? p[3].trim() : null;
        if (customDateToString != null && !customDateToString.equals("NULL")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate customDate = LocalDate.parse(customDateToString, formatter);

            item.setDateTo(Date.from(customDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        } else item.setDateTo(new Date());

        item.setDuration(item.getDateFrom(), item.getDateTo());

        return item;
    };


}
