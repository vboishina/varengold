package org.sirma.items;

public class EmployeItem {

    private Integer empId;

    private FieldItem fieldItem;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public FieldItem getFieldItem() {
        return fieldItem;
    }

    public void setFieldItem(FieldItem fieldItem) {
        this.fieldItem = fieldItem;
    }

    @Override
    public String toString() {
        return "EmployeItem {" +
                "empId=" + empId +
                ", fieldItem=" + fieldItem +
                '}';
    }
}
