package org.sirma;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.sirma.app.CVSFileParser;
import org.sirma.app.CVSFileParserImpl;
import org.sirma.items.EmployeItem;
import org.sirma.items.FieldItem;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.assertFalse;

public class ParserTest {


    private static Logger logger = LogManager.getLogger(ParserTest.class);

    String resourceName = "employes.csv";
    CVSFileParser cvsFileParserImpl;
    String absolutePath;
    List<FieldItem> parsedValues;

    @Before
    public void init() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(resourceName)).getFile());
        absolutePath = file.getAbsolutePath();
    }


    @Test
    public void testService() {

        File inputF = new File(absolutePath);
        InputStream inputFS = null;
        try {
            inputFS = Files.newInputStream(inputF.toPath());

            cvsFileParserImpl = new CVSFileParserImpl(inputFS.readAllBytes());
            parsedValues = cvsFileParserImpl.parseBytesToFieldItemList();

            cvsFileParserImpl.sortFieldItemList(parsedValues);
            parsedValues = cvsFileParserImpl.pickFieldItemList(parsedValues, 4);

            for (FieldItem fieldItem : parsedValues) {
                logger.info(fieldItem.getDuration() + " project " + fieldItem.getProjectId() + " employee " + fieldItem.getEmpId());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertFalse(parsedValues.isEmpty());
    }

    @Test
    public void testApp() {

        cvsFileParserImpl = new CVSFileParserImpl(absolutePath);
        parsedValues = cvsFileParserImpl.parseCVSToFieldItemList();
        cvsFileParserImpl.sortFieldItemList(parsedValues);
        parsedValues = cvsFileParserImpl.pickFieldItemList(parsedValues, 4);

        for (FieldItem fieldItem : parsedValues) {
            logger.info(fieldItem.getDuration() + " project " + fieldItem.getProjectId() + " employee " + fieldItem.getEmpId());
        }

        assertFalse(parsedValues.isEmpty());
    }


    @Test
    public void testMap() {
        cvsFileParserImpl = new CVSFileParserImpl(absolutePath);
     //   parsedValues = cvsFileParserImpl.parseCVSToFieldItemList();
        Map<Integer, List<EmployeItem>> fieldItemsMap = cvsFileParserImpl.getFieldItemsMap();
        logger.info("FieldItemsMap " + fieldItemsMap);

        assertFalse(fieldItemsMap.isEmpty());
    }
}

