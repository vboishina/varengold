package sirma.services;

import org.sirma.app.CVSFileParser;
import org.sirma.app.CVSFileParserImpl;
import org.sirma.items.EmployeItem;
import org.sirma.items.FieldItem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sirma.convertor.EmployeeConvertor;
import sirma.dto.Employee;

import java.io.IOException;
import java.util.*;

@Service
public class FileParserServiceImpl implements FileParserService {


    public Map<String, List<Employee>> parseToMap(MultipartFile file)  {
        try {
            CVSFileParser cvsFileParserImpl = new CVSFileParserImpl(file.getBytes());
            Map<Integer, List<EmployeItem>> fieldItemsMap = cvsFileParserImpl.getFieldItemsMap();

            return convertToEmployeeMap(fieldItemsMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> parseToList(MultipartFile file) {
        try {
            CVSFileParser cvsFileParserImpl = new CVSFileParserImpl(file.getBytes());
            Map<Integer, List<EmployeItem>> fieldItemsMap = cvsFileParserImpl.getFieldItemsMap();

            return convertToEmployeeList(fieldItemsMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, List<Employee>> convertToEmployeeMap(Map<Integer, List<EmployeItem>> fieldItemsMap) {
        Map<String, List<Employee>> employeeMap = new HashMap<>();
        Set<Integer> keySets = fieldItemsMap.keySet();
        for(Integer key :keySets) {
            employeeMap.put(String.valueOf(key), EmployeeConvertor.convertToEmployee(fieldItemsMap.get(key)));
        }

        return employeeMap;
    }

    private List<Employee> convertToEmployeeList(Map<Integer, List<EmployeItem>> fieldItemsMap) {
        List<Employee> employeeList = new ArrayList<>();
        Set<Integer> keySets = fieldItemsMap.keySet();
        for(Integer key :keySets) {
            employeeList.addAll(EmployeeConvertor.convertToEmployee(fieldItemsMap.get(key)));
        }

        Comparator<Employee> comparator = Comparator.comparing(Employee::getDuration);
        employeeList.sort(comparator);
        Collections.reverse(employeeList);
        return employeeList;
    }


}
