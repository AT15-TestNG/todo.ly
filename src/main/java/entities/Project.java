package entities;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Project {
    @JsonProperty("Id")
    private Integer id;
    @JsonProperty("Content")
    private String content;
    @JsonProperty("ItemsCount")
    private Integer itemsCount;
    @JsonProperty("Icon")
    private Integer icon;
    @JsonProperty("ItemType")
    private Integer itemType;
    @JsonProperty("ParentId")
    private Object parentId;
    @JsonProperty("Collapsed")
    private Boolean collapsed;
    @JsonProperty("ItemOrder")
    private Integer itemOrder;
    @JsonProperty("Children")
    private List<Object> children = null;
    @JsonProperty("IsProjectShared")
    private Boolean isProjectShared;
    @JsonProperty("ProjectShareOwnerName")
    private Object projectShareOwnerName;
    @JsonProperty("ProjectShareOwnerEmail")
    private Object projectShareOwnerEmail;
    @JsonProperty("IsShareApproved")
    private Boolean isShareApproved;
    @JsonProperty("IsOwnProject")
    private Boolean isOwnProject;
    @JsonProperty("LastSyncedDateTime")
    private String lastSyncedDateTime;
    @JsonProperty("LastUpdatedDate")
    private String lastUpdatedDate;
    @JsonProperty("Deleted")
    private Boolean deleted;
    @JsonProperty("SyncClientCreationId")
    private Object syncClientCreationId;
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

    @JsonProperty("ItemsCount")
    public Integer getItemsCount() {
        return itemsCount;
    }

    @JsonProperty("ItemsCount")
    public void setItemsCount(Integer itemsCount) {
        this.itemsCount = itemsCount;
    }

    @JsonProperty("Icon")
    public Integer getIcon() {
        return icon;
    }

    @JsonProperty("Icon")
    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    @JsonProperty("ItemType")
    public Integer getItemType() {
        return itemType;
    }

    @JsonProperty("ItemType")
    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    @JsonProperty("ParentId")
    public Object getParentId() {
        return parentId;
    }

    @JsonProperty("ParentId")
    public void setParentId(Object parentId) {
        this.parentId = parentId;
    }

    @JsonProperty("Collapsed")
    public Boolean getCollapsed() {
        return collapsed;
    }

    @JsonProperty("Collapsed")
    public void setCollapsed(Boolean collapsed) {
        this.collapsed = collapsed;
    }

    @JsonProperty("ItemOrder")
    public Integer getItemOrder() {
        return itemOrder;
    }

    @JsonProperty("ItemOrder")
    public void setItemOrder(Integer itemOrder) {
        this.itemOrder = itemOrder;
    }

    @JsonProperty("Children")
    public List<Object> getChildren() {
        return children;
    }

    @JsonProperty("Children")
    public void setChildren(List<Object> children) {
        this.children = children;
    }

    @JsonProperty("IsProjectShared")
    public Boolean getIsProjectShared() {
        return isProjectShared;
    }

    @JsonProperty("IsProjectShared")
    public void setIsProjectShared(Boolean isProjectShared) {
        this.isProjectShared = isProjectShared;
    }

    @JsonProperty("ProjectShareOwnerName")
    public Object getProjectShareOwnerName() {
        return projectShareOwnerName;
    }

    @JsonProperty("ProjectShareOwnerName")
    public void setProjectShareOwnerName(Object projectShareOwnerName) {
        this.projectShareOwnerName = projectShareOwnerName;
    }

    @JsonProperty("ProjectShareOwnerEmail")
    public Object getProjectShareOwnerEmail() {
        return projectShareOwnerEmail;
    }

    @JsonProperty("ProjectShareOwnerEmail")
    public void setProjectShareOwnerEmail(Object projectShareOwnerEmail) {
        this.projectShareOwnerEmail = projectShareOwnerEmail;
    }

    @JsonProperty("IsShareApproved")
    public Boolean getIsShareApproved() {
        return isShareApproved;
    }

    @JsonProperty("IsShareApproved")
    public void setIsShareApproved(Boolean isShareApproved) {
        this.isShareApproved = isShareApproved;
    }

    @JsonProperty("IsOwnProject")
    public Boolean getIsOwnProject() {
        return isOwnProject;
    }

    @JsonProperty("IsOwnProject")
    public void setIsOwnProject(Boolean isOwnProject) {
        this.isOwnProject = isOwnProject;
    }

    @JsonProperty("LastSyncedDateTime")
    public String getLastSyncedDateTime() {
        return lastSyncedDateTime;
    }

    @JsonProperty("LastSyncedDateTime")
    public void setLastSyncedDateTime(String lastSyncedDateTime) {
        this.lastSyncedDateTime = lastSyncedDateTime;
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

    @JsonProperty("SyncClientCreationId")
    public Object getSyncClientCreationId() {
        return syncClientCreationId;
    }

    @JsonProperty("SyncClientCreationId")
    public void setSyncClientCreationId(Object syncClientCreationId) {
        this.syncClientCreationId = syncClientCreationId;
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
