package br.com.elo7.mars.explorer.api.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

/**
 * A dimension of surface
 *
 * @author pedrotoliveira
 */
@ApiModel(description = "A dimension of surface")
public class Dimension {

	@JsonProperty("xAxis")
	@ApiModelProperty(name = "xAxis", value = "XAxis", required = true, example = "1")
	@NotNull
	private Integer xAxis;

	@JsonProperty("yAxis")
	@ApiModelProperty(name = "yAxis", value = "YAxis", required = true, example = "2")
	@NotNull
	private Integer yAxis;

	public Dimension() {
	}

	public Dimension(Integer xAxis, Integer yAxis) {
		this.xAxis = xAxis;
		this.yAxis = yAxis;
	}

	public Dimension xAxis(Integer xAxis) {
		this.xAxis = xAxis;
		return this;
	}

	public Dimension yAxis(Integer yAxis) {
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

	@JsonIgnore
	public String formattedInput() {
		return String.format("%d %d", getxAxis(), getyAxis());
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 79 * hash + Objects.hashCode(this.xAxis);
		hash = 79 * hash + Objects.hashCode(this.yAxis);
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
		final Dimension other = (Dimension) obj;
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
		return "Dimension[" + "xAxis=" + xAxis + ", yAxis=" + yAxis + ']';
	}
}
