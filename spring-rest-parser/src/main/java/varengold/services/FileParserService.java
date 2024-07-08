package sirma.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sirma.dto.Employee;

import java.util.List;
import java.util.Map;

@Service
public interface FileParserService {

    Map<String, List<Employee>> parseToMap(MultipartFile file);

    List<Employee> parseToList(MultipartFile file);
}
