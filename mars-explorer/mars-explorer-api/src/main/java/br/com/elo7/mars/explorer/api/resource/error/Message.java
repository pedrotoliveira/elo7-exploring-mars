package br.com.elo7.mars.explorer.api.resource.error;

import br.com.elo7.mars.explorer.api.resource.json.DateTimeJsonSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response.Status;
import org.joda.time.DateTime;

/**
 * A Class that Encapsulate Errors Messages to the Client.
 *
 * @author pedrotoliveira
 *
 */
@JsonPropertyOrder({"id",
	"code",
	"type",
	"description",
	"httpReason",
	"date",
	"notifications"})
@ApiModel(description = "Error Message")
public class Message implements Serializable {

	private static final long serialVersionUID = 4922469828024757220L;
	private static final int CLIENT_ERRO = 4;
	private static final int BASE = 100;

	public static final Message UNEXPECTED_ERROR = new Message(MessageType.Unexpected_Error, Status.INTERNAL_SERVER_ERROR);

	@JsonProperty("id")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	@ApiModelProperty(name = "id", value = "Error Id", example = "1", readOnly = true)
	private Integer id;
	@JsonProperty("code")
	@ApiModelProperty(name = "code", value = "Error Code", example = "500", readOnly = true)
	private Integer code;
	@JsonProperty("type")
	@ApiModelProperty(name = "type", value = "Error Type", readOnly = true)
	private MessageType type;
	@JsonProperty("description")
	@ApiModelProperty(name = "description", value = "Description", example = "Null Pointer Exception", readOnly = true)
	private String description;
	@JsonProperty("httpReason")
	@ApiModelProperty(name = "httpReason", value = "HTTP Reason", example = "Internal Server Error", readOnly = true)
	private String httpReason;
	@JsonProperty("date")
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	@ApiModelProperty(value = "Date", readOnly = true)
	private DateTime date = new DateTime();
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
	@ApiModelProperty(value = "Error Details and Links to Documentation", readOnly = true)
	private List<String> notifications = new ArrayList<>();

	public Message() {
	}

	/**
	 * Construct a Error Message
	 *
	 * @param code the http code
	 * @param type the error type
	 */
	public Message(int code, MessageType type) {
		super();
		this.code = validateCode(code);
		this.type = type;
	}

	/**
	 * Construct a Error Message
	 *
	 * @param type the error type
	 * @param httpStatus the http status
	 */
	public Message(MessageType type, Status httpStatus) {
		super();
		this.type = type;
		this.description = type.getDescription();
		this.code = httpStatus.getStatusCode();
		this.httpReason = httpStatus.getReasonPhrase();
	}

	/**
	 * Construct a Error Message
	 *
	 * @param type the error type
	 * @param httpStatus the http status
	 * @param description a description of error
	 */
	public Message(MessageType type, Status httpStatus, String description) {
		super();
		this.type = type;
		this.code = httpStatus.getStatusCode();
		this.httpReason = httpStatus.getReasonPhrase();
		this.description = description;
	}

	/**
	 * Construct a Error Message
	 *
	 * @param type the error type
	 * @param httpCode the http code
	 * @param httpReason the http reason
	 * @param description a description of error
	 */
	public Message(MessageType type, int httpCode, String httpReason, String description) {
		super();
		this.type = type;
		this.code = validateCode(httpCode);
		this.httpReason = httpReason;
		this.description = description;
	}

	private Integer validateCode(int code) {
		if ((code / BASE) < CLIENT_ERRO) {
			throw new IllegalArgumentException("Invalid Http ErroCode [" + code + "], select a code after 400");
		}
		return Integer.valueOf(code);
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * @return the errorType
	 */
	public MessageType getType() {
		return type;
	}

	/**
	 * @param type the errorType to set
	 */
	public void setType(MessageType type) {
		this.type = type;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the httpReason
	 */
	public String getHttpReason() {
		return httpReason;
	}

	/**
	 * @param httpReason the httpReason to set
	 */
	public void setHttpReason(String httpReason) {
		this.httpReason = httpReason;
	}

	/**
	 * @return the date
	 */
	public DateTime getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(DateTime date) {
		this.date = date;
	}

	/**
	 * @return the notifications
	 */
	public List<String> getNotifications() {
		return notifications;
	}

	/**
	 * @param notifications the notifications to set
	 */
	public void setNotifications(List<String> notifications) {
		this.notifications = notifications;
	}

	/**
	 * Add a Notification to this error message.
	 *
	 * @param notification
	 * @return this Message
	 */
	public Message addNotification(String notification) {
		getNotifications().add(notification);
		return this;
	}

	@Override
	public String toString() {
		return "Message["
				+ "id=" + id
				+ ", code=" + code
				+ ", type=" + type
				+ ", description=" + description
				+ ", httpReason=" + httpReason
				+ ", date=" + date
				+ ", notifications=" + notifications
				+ ']';
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((httpReason == null) ? 0 : httpReason.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((notifications == null) ? 0 : notifications.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Message)) {
			return false;
		}
		Message other = (Message) obj;
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		if (httpReason == null) {
			if (other.httpReason != null) {
				return false;
			}
		} else if (!httpReason.equals(other.httpReason)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (notifications == null) {
			if (other.notifications != null) {
				return false;
			}
		} else if (!notifications.equals(other.notifications)) {
			return false;
		}
		return true;
	}
}
