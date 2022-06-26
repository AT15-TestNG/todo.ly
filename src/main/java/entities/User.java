package entities;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class User {
    @JsonProperty("Id")
    private Integer id;
    @JsonProperty("Email")
    private String email;
    @JsonProperty("Password")
    private Object password;
    @JsonProperty("FullName")
    private String fullName;
    @JsonProperty("TimeZone")
    private Integer timeZone;
    @JsonProperty("IsProUser")
    private Boolean isProUser;
    @JsonProperty("DefaultProjectId")
    private Integer defaultProjectId;
    @JsonProperty("AddItemMoreExpanded")
    private Boolean addItemMoreExpanded;
    @JsonProperty("EditDueDateMoreExpanded")
    private Boolean editDueDateMoreExpanded;
    @JsonProperty("ListSortType")
    private Integer listSortType;
    @JsonProperty("FirstDayOfWeek")
    private Integer firstDayOfWeek;
    @JsonProperty("NewTaskDueDate")
    private Integer newTaskDueDate;
    @JsonProperty("TimeZoneId")
    private String timeZoneId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("Id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("Email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("Email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("Password")
    public Object getPassword() {
        return password;
    }

    @JsonProperty("Password")
    public void setPassword(Object password) {
        this.password = password;
    }

    @JsonProperty("FullName")
    public String getFullName() {
        return fullName;
    }

    @JsonProperty("FullName")
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @JsonProperty("TimeZone")
    public Integer getTimeZone() {
        return timeZone;
    }

    @JsonProperty("TimeZone")
    public void setTimeZone(Integer timeZone) {
        this.timeZone = timeZone;
    }

    @JsonProperty("IsProUser")
    public Boolean getIsProUser() {
        return isProUser;
    }

    @JsonProperty("IsProUser")
    public void setIsProUser(Boolean isProUser) {
        this.isProUser = isProUser;
    }

    @JsonProperty("DefaultProjectId")
    public Integer getDefaultProjectId() {
        return defaultProjectId;
    }

    @JsonProperty("DefaultProjectId")
    public void setDefaultProjectId(Integer defaultProjectId) {
        this.defaultProjectId = defaultProjectId;
    }

    @JsonProperty("AddItemMoreExpanded")
    public Boolean getAddItemMoreExpanded() {
        return addItemMoreExpanded;
    }

    @JsonProperty("AddItemMoreExpanded")
    public void setAddItemMoreExpanded(Boolean addItemMoreExpanded) {
        this.addItemMoreExpanded = addItemMoreExpanded;
    }

    @JsonProperty("EditDueDateMoreExpanded")
    public Boolean getEditDueDateMoreExpanded() {
        return editDueDateMoreExpanded;
    }

    @JsonProperty("EditDueDateMoreExpanded")
    public void setEditDueDateMoreExpanded(Boolean editDueDateMoreExpanded) {
        this.editDueDateMoreExpanded = editDueDateMoreExpanded;
    }

    @JsonProperty("ListSortType")
    public Integer getListSortType() {
        return listSortType;
    }

    @JsonProperty("ListSortType")
    public void setListSortType(Integer listSortType) {
        this.listSortType = listSortType;
    }

    @JsonProperty("FirstDayOfWeek")
    public Integer getFirstDayOfWeek() {
        return firstDayOfWeek;
    }

    @JsonProperty("FirstDayOfWeek")
    public void setFirstDayOfWeek(Integer firstDayOfWeek) {
        this.firstDayOfWeek = firstDayOfWeek;
    }

    @JsonProperty("NewTaskDueDate")
    public Integer getNewTaskDueDate() {
        return newTaskDueDate;
    }

    @JsonProperty("NewTaskDueDate")
    public void setNewTaskDueDate(Integer newTaskDueDate) {
        this.newTaskDueDate = newTaskDueDate;
    }

    @JsonProperty("TimeZoneId")
    public String getTimeZoneId() {
        return timeZoneId;
    }

    @JsonProperty("TimeZoneId")
    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
