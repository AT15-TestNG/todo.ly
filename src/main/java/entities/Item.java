package entities;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Item {
    @JsonProperty("Id")
    private Integer id;
    @JsonProperty("Content")
    private String content;
    @JsonProperty("ItemType")
    private Integer itemType;
    @JsonProperty("Checked")
    private Boolean checked;
    @JsonProperty("ProjectId")
    private Integer projectId;
    @JsonProperty("ParentId")
    private Object parentId;
    @JsonProperty("Path")
    private String path;
    @JsonProperty("Collapsed")
    private Boolean collapsed;
    @JsonProperty("DateString")
    private String dateString;
    @JsonProperty("DateStringPriority")
    private Integer dateStringPriority;
    @JsonProperty("DueDate")
    private String dueDate;
    @JsonProperty("Recurrence")
    private Object recurrence;
    @JsonProperty("ItemOrder")
    private Integer itemOrder;
    @JsonProperty("Priority")
    private Integer priority;
    @JsonProperty("LastSyncedDateTime")
    private String lastSyncedDateTime;
    @JsonProperty("Children")
    private List<Object> children = null;
    @JsonProperty("DueDateTime")
    private String dueDateTime;
    @JsonProperty("CreatedDate")
    private String createdDate;
    @JsonProperty("LastCheckedDate")
    private Object lastCheckedDate;
    @JsonProperty("LastUpdatedDate")
    private String lastUpdatedDate;
    @JsonProperty("Deleted")
    private Boolean deleted;
    @JsonProperty("Notes")
    private String notes;
    @JsonProperty("InHistory")
    private Boolean inHistory;
    @JsonProperty("SyncClientCreationId")
    private Object syncClientCreationId;
    @JsonProperty("DueTimeSpecified")
    private Boolean dueTimeSpecified;
    @JsonProperty("OwnerId")
    private Integer ownerId;
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

    @JsonProperty("Content")
    public String getContent() {
        return content;
    }

    @JsonProperty("Content")
    public void setContent(String content) {
        this.content = content;
    }

    @JsonProperty("ItemType")
    public Integer getItemType() {
        return itemType;
    }

    @JsonProperty("ItemType")
    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    @JsonProperty("Checked")
    public Boolean getChecked() {
        return checked;
    }

    @JsonProperty("Checked")
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @JsonProperty("ProjectId")
    public Integer getProjectId() {
        return projectId;
    }

    @JsonProperty("ProjectId")
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @JsonProperty("ParentId")
    public Object getParentId() {
        return parentId;
    }

    @JsonProperty("ParentId")
    public void setParentId(Object parentId) {
        this.parentId = parentId;
    }

    @JsonProperty("Path")
    public String getPath() {
        return path;
    }

    @JsonProperty("Path")
    public void setPath(String path) {
        this.path = path;
    }

    @JsonProperty("Collapsed")
    public Boolean getCollapsed() {
        return collapsed;
    }

    @JsonProperty("Collapsed")
    public void setCollapsed(Boolean collapsed) {
        this.collapsed = collapsed;
    }

    @JsonProperty("DateString")
    public String getDateString() {
        return dateString;
    }

    @JsonProperty("DateString")
    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    @JsonProperty("DateStringPriority")
    public Integer getDateStringPriority() {
        return dateStringPriority;
    }

    @JsonProperty("DateStringPriority")
    public void setDateStringPriority(Integer dateStringPriority) {
        this.dateStringPriority = dateStringPriority;
    }

    @JsonProperty("DueDate")
    public String getDueDate() {
        return dueDate;
    }

    @JsonProperty("DueDate")
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @JsonProperty("Recurrence")
    public Object getRecurrence() {
        return recurrence;
    }

    @JsonProperty("Recurrence")
    public void setRecurrence(Object recurrence) {
        this.recurrence = recurrence;
    }

    @JsonProperty("ItemOrder")
    public Integer getItemOrder() {
        return itemOrder;
    }

    @JsonProperty("ItemOrder")
    public void setItemOrder(Integer itemOrder) {
        this.itemOrder = itemOrder;
    }

    @JsonProperty("Priority")
    public Integer getPriority() {
        return priority;
    }

    @JsonProperty("Priority")
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @JsonProperty("LastSyncedDateTime")
    public String getLastSyncedDateTime() {
        return lastSyncedDateTime;
    }

    @JsonProperty("LastSyncedDateTime")
    public void setLastSyncedDateTime(String lastSyncedDateTime) {
        this.lastSyncedDateTime = lastSyncedDateTime;
    }

    @JsonProperty("Children")
    public List<Object> getChildren() {
        return children;
    }

    @JsonProperty("Children")
    public void setChildren(List<Object> children) {
        this.children = children;
    }

    @JsonProperty("DueDateTime")
    public String getDueDateTime() {
        return dueDateTime;
    }

    @JsonProperty("DueDateTime")
    public void setDueDateTime(String dueDateTime) {
        this.dueDateTime = dueDateTime;
    }

    @JsonProperty("CreatedDate")
    public String getCreatedDate() {
        return createdDate;
    }

    @JsonProperty("CreatedDate")
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @JsonProperty("LastCheckedDate")
    public Object getLastCheckedDate() {
        return lastCheckedDate;
    }

    @JsonProperty("LastCheckedDate")
    public void setLastCheckedDate(Object lastCheckedDate) {
        this.lastCheckedDate = lastCheckedDate;
    }

    @JsonProperty("LastUpdatedDate")
    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    @JsonProperty("LastUpdatedDate")
    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @JsonProperty("Deleted")
    public Boolean getDeleted() {
        return deleted;
    }

    @JsonProperty("Deleted")
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @JsonProperty("Notes")
    public String getNotes() {
        return notes;
    }

    @JsonProperty("Notes")
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @JsonProperty("InHistory")
    public Boolean getInHistory() {
        return inHistory;
    }

    @JsonProperty("InHistory")
    public void setInHistory(Boolean inHistory) {
        this.inHistory = inHistory;
    }

    @JsonProperty("SyncClientCreationId")
    public Object getSyncClientCreationId() {
        return syncClientCreationId;
    }

    @JsonProperty("SyncClientCreationId")
    public void setSyncClientCreationId(Object syncClientCreationId) {
        this.syncClientCreationId = syncClientCreationId;
    }

    @JsonProperty("DueTimeSpecified")
    public Boolean getDueTimeSpecified() {
        return dueTimeSpecified;
    }

    @JsonProperty("DueTimeSpecified")
    public void setDueTimeSpecified(Boolean dueTimeSpecified) {
        this.dueTimeSpecified = dueTimeSpecified;
    }

    @JsonProperty("OwnerId")
    public Integer getOwnerId() {
        return ownerId;
    }

    @JsonProperty("OwnerId")
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
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
