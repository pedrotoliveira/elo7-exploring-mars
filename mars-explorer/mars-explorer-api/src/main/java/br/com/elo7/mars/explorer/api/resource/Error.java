package br.com.elo7.mars.explorer.api.resource;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;



/**
 * Error
 **/

@ApiModel(description = "Error")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-10T01:31:07.119Z")
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

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error error = (Error) o;
    return Objects.equals(code, error.code) &&
        Objects.equals(description, error.description) &&
        Objects.equals(id, error.id) &&
        Objects.equals(message, error.message) &&
        Objects.equals(notifications, error.notifications);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, description, id, message, notifications);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    notifications: ").append(toIndentedString(notifications)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

