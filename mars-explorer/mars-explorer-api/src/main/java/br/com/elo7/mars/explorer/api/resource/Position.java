package br.com.elo7.mars.explorer.api.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 * The Explorer Position
 */
@ApiModel(description = "The Explorer Position")
public class Position implements Serializable {

	private static final long serialVersionUID = 6476153333816770400L;		

    @JsonProperty("xAxis")
    @ApiModelProperty(required = true, value = "X Axis position in the current surface")
    @NotNull
    private Integer xAxis;

    @JsonProperty("yAxis")
    @ApiModelProperty(required = true, value = "Y Axis position in the current Surface")
    @NotNull
    private Integer yAxis;

    @JsonProperty("direction")
    @ApiModelProperty(required = true, value = "Explorer Direction")
    @NotNull
    private String direction;

    public Position() {
    }

    public Position(Integer xAxis, Integer yAxis, String direction) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.direction = direction;
    }

    public Position xAxis(Integer xAxis) {
        this.xAxis = xAxis;
        return this;
    }

    public Position yAxis(Integer yAxis) {
        this.yAxis = yAxis;
        return this;
    }

    public Position direction(String direction) {
        this.direction = direction;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
	
	@JsonIgnore
	public String formattedInput() {
		return String.format("%d %d %s", getxAxis(), getyAxis(), getDirection());
	}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.xAxis);
        hash = 79 * hash + Objects.hashCode(this.yAxis);
        hash = 79 * hash + Objects.hashCode(this.direction);
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
        if (!Objects.equals(this.direction, other.direction)) {
            return false;
        }
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
        return "Position[" + "xAxis=" + xAxis + ", yAxis=" + yAxis + ", direction=" + direction + ']';
    }
	
}
