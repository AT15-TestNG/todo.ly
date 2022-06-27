package entities;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class NewItem {
    @JsonProperty("Content")
    private String content;
    @JsonProperty("ProjectId")
    private Integer projectId;
    @JsonProperty("Checked")
    private Boolean checked;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public NewItem(String content, Integer projectId, Boolean checked) {
        this.content = content;
        this.projectId = projectId;
        this.checked = checked;
    }

    @JsonProperty("Content")
    public String getContent() {
        return content;
    }

    @JsonProperty("Content")
    public void setContent(String content) {
        this.content = content;
    }

    @JsonProperty("ProjectId")
    public Integer getProjectId() {
        return projectId;
    }

    @JsonProperty("ProjectId")
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @JsonProperty("Checked")
    public Boolean getChecked() {
        return checked;
    }

    @JsonProperty("Checked")
    public void setChecked(Boolean checked) {
        this.checked = checked;
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
