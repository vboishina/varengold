package sirma.convertor;

import org.sirma.items.EmployeItem;
import sirma.dto.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeConvertor {


    public static List<Employee> convertToEmployee(List<EmployeItem> employeeItems) {
        List<Employee> employeeList = new ArrayList<>();
        if (employeeItems != null && !employeeItems.isEmpty()) {
            for (EmployeItem employeItem : employeeItems) {
                Employee emp = new Employee();
                emp.setEmployeeId(employeItem.getFieldItem().getEmpId());
                emp.setProjectId(employeItem.getFieldItem().getProjectId());
                emp.setDuration(employeItem.getFieldItem().getDuration());
                employeeList.add(emp);
            }
        }

        return employeeList;
    }
}
