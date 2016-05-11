package br.com.elo7.mars.explorer.api.resource;

import br.com.elo7.mars.explorer.api.endpoint.SurfaceEndpoint;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * SurfaceResource Resource
 */
@ApiModel(description = "surface")
public class SurfaceResource extends BaseResource {

    @Override
    public Class<?> getEndpointClass() {
        return SurfaceEndpoint.class;
    }

    @Override
    public String getRel() {
        return "surface";
    }

    @JsonProperty("id")
    @ApiModelProperty(value = "UUID Representing a Created Surface")
    private String id;

    @JsonProperty("dimension")
    @ApiModelProperty(required = true, value = "The Surface Dimension")
    @NotNull
    private Dimension dimension;

    @JsonProperty("createdDate")
    @ApiModelProperty(value = "The Creation Date")
    private Date createdDate;

    @JsonProperty("deployedExplorers")
    @ApiModelProperty(value = "The Deployed Explorers")
    private List<ExplorerResource> deployedExplorers = new ArrayList<ExplorerResource>();

    @JsonProperty("errors")
    @ApiModelProperty(value = "Errors")
    private List<Error> errors = new ArrayList<Error>();

    public SurfaceResource id(String id) {
        this.id = id;
        return this;
    }

    public SurfaceResource dimension(Dimension dimension) {
        this.dimension = dimension;
        return this;
    }

    public SurfaceResource createdDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public SurfaceResource deployedExplorers(List<ExplorerResource> deployedExplorers) {
        this.deployedExplorers = deployedExplorers;
        return this;
    }

    public SurfaceResource errors(List<Error> errors) {
        this.errors = errors;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<ExplorerResource> getDeployedExplorers() {
        return deployedExplorers;
    }

    public void setDeployedExplorers(List<ExplorerResource> deployedExplorers) {
        this.deployedExplorers = deployedExplorers;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.dimension);
        hash = 97 * hash + Objects.hashCode(this.createdDate);
        hash = 97 * hash + Objects.hashCode(this.deployedExplorers);
        hash = 97 * hash + Objects.hashCode(this.errors);
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
        final SurfaceResource other = (SurfaceResource) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dimension, other.dimension)) {
            return false;
        }
        if (!Objects.equals(this.createdDate, other.createdDate)) {
            return false;
        }
        if (!Objects.equals(this.deployedExplorers, other.deployedExplorers)) {
            return false;
        }
        if (!Objects.equals(this.errors, other.errors)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Surface[" + "id=" + id
                + ", dimension=" + dimension
                + ", createdDate=" + createdDate
                + ", deployedExplorers=" + deployedExplorers
                + ", errors=" + errors + ']';
    }
}
