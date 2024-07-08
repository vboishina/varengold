package sirma.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sirma.dto.Employee;
import sirma.services.FileParserService;

import java.util.List;
import java.util.Map;


@RestController
public class FileParserController {

    @Autowired
    private FileParserService fileParserService;

    // Save

    @RequestMapping(value = "/employeeMap", headers = ("content-type=multipart/*"), method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Map<String, List<Employee>> getEmployeeMap(@RequestParam(value = "file") MultipartFile file) {
        return fileParserService.parseToMap(file);
    }

    @RequestMapping(value = "/employeeList", headers = ("content-type=multipart/*"), method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    List<Employee> getEmployeeList(@RequestParam(value = "file") MultipartFile file) {
        return fileParserService.parseToList(file);
    }

}
