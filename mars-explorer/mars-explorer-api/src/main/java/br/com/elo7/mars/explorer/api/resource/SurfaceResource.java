package br.com.elo7.mars.explorer.api.resource;

import br.com.elo7.mars.explorer.api.endpoint.SurfaceEndpoint;
import br.com.elo7.mars.explorer.api.resource.json.DateTimeJsonSerializer;
import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

/**
 * SurfaceResource Resource
 */
@ApiModel(description = "surface")
public class SurfaceResource extends BaseResource {

    private static final long serialVersionUID = 6438815192131169412L;

    @Override
    public Class<?> getEndpointClass() {
        return SurfaceEndpoint.class;
    }

    @Override
    public String getRel() {
        return "surface";
    }

    @Override
    public Resource<SurfaceResource> buildResource(Link... links) {
        return new Resource<>(this, links);
    }

    @Override
    public Resource<SurfaceResource> buildResource(List<Link> links) {
        return new Resource<>(this, links);
    }

    @JsonProperty("id")
    @ApiModelProperty(value = "UUID of Created Surface")
    private String id;

    @JsonProperty("dimension")
    @ApiModelProperty(required = true, value = "Surface Dimension")
    @NotNull
    private Dimension dimension;

    @JsonProperty("explorerPositions")
    @ApiModelProperty(value = "Deployed Explorer Positions")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Position> explorersPositions;

    @JsonProperty("createdDate")
    @ApiModelProperty(value = "Creation Date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonSerialize(using = DateTimeJsonSerializer.class)
    private DateTime createdDate;

    @JsonProperty("deployedExplorers")
    @ApiModelProperty(value = "Deployed Explorers")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Resources<ExplorerResource> deployedExplorers;

    public SurfaceResource id(String id) {
        this.id = id;
        return this;
    }

    public SurfaceResource dimension(Dimension dimension) {
        this.dimension = dimension;
        return this;
    }

    public SurfaceResource createdDate(Date createdDate) {
        this.createdDate = new DateTime(createdDate.getTime());
        return this;
    }

    public SurfaceResource deployedExplorers(Resources<ExplorerResource> deployedExplorers) {
        this.deployedExplorers = deployedExplorers;
        return this;
    }

    public SurfaceResource explorersPositions(Collection<Explorer> deployedExplorers) {
        List<Position> positions = new ArrayList<>();
        deployedExplorers.forEach((Explorer explorer) -> {
            ExplorerPosition explorerPosition = explorer.getCurrentPosition();
            positions.add(new Position(explorerPosition.getxAxis(),
                    explorerPosition.getyAxis(),
                    explorerPosition.getDirectionAsString()));
        });
        this.explorersPositions = positions;
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

    public List<Position> getExplorersPositions() {
        return explorersPositions;
    }

    public void setExplorerPositions(List<Position> explorersPositions) {
        this.explorersPositions = explorersPositions;
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    public boolean hasDeployedExplorers() {
        return getDeployedExplorers().getContent() != null && !getDeployedExplorers().getContent().isEmpty();
    }

    public Resources<ExplorerResource> getDeployedExplorers() {
        return deployedExplorers;
    }

    public void setDeployedExplorers(Resources<ExplorerResource> deployedExplorers) {
        this.deployedExplorers = deployedExplorers;
    }

    public Collection<String> extractExplorersInputCollection() {
        Collection<String> inputs = new ArrayList<>();
        if (hasDeployedExplorers()) {
            getDeployedExplorers().forEach((ExplorerResource deployed) -> {
                inputs.addAll(deployed.extractInputCollection());
            });
        }
        return inputs;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.dimension);
        hash = 97 * hash + Objects.hashCode(this.deployedExplorers);
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
        if (!Objects.equals(this.deployedExplorers, other.deployedExplorers)) {
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
                + ", errors=" + getErrors() + ']';
    }
}
