package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonPropertyOrder({
        "Id",
        "Content",
        "ItemsCount",
        "Icon",
        "ItemType",
        "Children"
})
@Generated("jsonschema2pojo")
public class Filter {

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
    @JsonProperty("Children")
    private List<Object> children = null;
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
}
