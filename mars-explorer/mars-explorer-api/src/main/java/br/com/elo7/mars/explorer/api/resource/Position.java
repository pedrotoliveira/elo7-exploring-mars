package br.com.elo7.mars.explorer.api.resource;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

/**
 * The Explorer Position
 *
 */
@ApiModel(description = "The Explorer Position")
public class Position {
		
	@JsonProperty("xAxis")
	@ApiModelProperty(required = true, value = "X Axis position in the current surface")
	@NotNull
	private Integer xAxis;
	
	@JsonProperty("yAxis")
	@ApiModelProperty(required = true, value = "Y Axis position in the current Surface")
	@NotNull
	private Integer yAxis;

	public Position() {
	}

	public Position(Integer xAxis, Integer yAxis) {
		this.xAxis = xAxis;
		this.yAxis = yAxis;
	}

	public Position xAxis(Integer xAxis) {
		this.xAxis = xAxis;
		return this;
	}

	public Position yAxis(Integer yAxis) {
		this.yAxis = yAxis;
		return this;
	}

	public Integer getxAxis() {
		return xAxis;
	}

	public void setxAxis(Integer xAxis) {
		this.xAxis = xAxis;
	}

	public Integer getyAxis() {
		return yAxis;
	}

	public void setyAxis(Integer yAxis) {
		this.yAxis = yAxis;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 29 * hash + Objects.hashCode(this.xAxis);
		hash = 29 * hash + Objects.hashCode(this.yAxis);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Position other = (Position) obj;
		if (!Objects.equals(this.xAxis, other.xAxis)) {
			return false;
		}
		if (!Objects.equals(this.yAxis, other.yAxis)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Position[" + "xAxis=" + xAxis + ", yAxis=" + yAxis + ']';
	}
}
