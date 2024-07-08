package org.sirma.items;

import org.sirma.utils.DateUtils;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class FieldItem {

    private Integer empId;
    private Integer projectId;
    private Date dateFrom;
    private Date dateTo;

    private long duration;


    public void setDuration(Date dateFrom, Date dateTo) {
        this.duration = ChronoUnit.DAYS.between(DateUtils.dateToLocalDate(dateFrom), DateUtils.dateToLocalDate(dateTo));
    }

    public long getDuration() {
        return this.duration;
    }


    //Period period = Period.between(startDate, endDate);
    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }


    @Override
    public String toString() {
        return "FieldItem{" +
                "empId=" + empId +
                ", projectId=" + projectId +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", duration=" + duration +
                '}';
    }
}
