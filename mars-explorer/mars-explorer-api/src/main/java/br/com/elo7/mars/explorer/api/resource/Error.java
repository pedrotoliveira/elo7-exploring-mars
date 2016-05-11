package br.com.elo7.mars.explorer.api.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApiModel(description = "Error")
public class Error   {
  
  private Integer code = null;
  private String description = null;
  private String id = null;
  private String message = null;
  private List<String> notifications = new ArrayList<String>();

  
  /**
   * Error code
   **/
  public Error code(Integer code) {
    this.code = code;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "Error code")
  @JsonProperty("code")
  public Integer getCode() {
    return code;
  }
  public void setCode(Integer code) {
    this.code = code;
  }

  
  /**
   * The error description
   **/
  public Error description(String description) {
    this.description = description;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "The error description")
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  
  /**
   * The id
   **/
  public Error id(String id) {
    this.id = id;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "The id")
  @JsonProperty("id")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  
  /**
   * The message
   **/
  public Error message(String message) {
    this.message = message;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "The message")
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
  }

  
  /**
   * Notifications Collection
   **/
  public Error notifications(List<String> notifications) {
    this.notifications = notifications;
    return this;
  }

  
  @ApiModelProperty(value = "Notifications Collection")
  @JsonProperty("notifications")
  public List<String> getNotifications() {
    return notifications;
  }
  public void setNotifications(List<String> notifications) {
    this.notifications = notifications;
  }

}

