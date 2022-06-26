package entities;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class NewProject {

    @JsonProperty("Content")
    private String content;
    @JsonProperty("Icon")
    private Integer icon;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public NewProject(String content, Integer icon) {
        this.content = content;
        this.icon = icon;
    }

    @JsonProperty("Content")
    public String getContent() {
        return content;
    }

    @JsonProperty("Content")
    public void setContent(String content) {
        this.content = content;
    }

    @JsonProperty("Icon")
    public Integer getIcon() {
        return icon;
    }

    @JsonProperty("Icon")
    public void setIcon(Integer icon) {
        this.icon = icon;
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
