package br.com.elo7.mars.explorer.engine.domain.surface;

import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import org.apache.commons.lang.Validate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import static br.com.elo7.mars.explorer.engine.domain.surface.SurfaceScanResult.COLLISION;
import static br.com.elo7.mars.explorer.engine.domain.surface.SurfaceScanResult.OK;
import static br.com.elo7.mars.explorer.engine.domain.surface.SurfaceScanResult.OUT_OF_BOUNDARY;

/**
 * A Common Area that the Explorers will have to scan.
 *
 * @author pedrotoliveira
 */
@Document(collection = "surfaces")
class Plateau implements Surface {

	public static final String ALREDY_DEPLOYED = "Alredy deployed";

	@Id
	private final String id;
	private Date createdDate;
	private int xAxis;
	private int yAxis;
	private List<Explorer> deployedExplorers;

	@PersistenceConstructor
	public Plateau(String id, int xAxis, int yAxis) {
		this.id = id;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.createdDate = new Date();
		this.deployedExplorers = new ArrayList<>();
	}

	@Override
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public Explorer deployExplorer(final Explorer explorer) {
		Validate.isTrue(notDeployed(explorer), createDeployErrorMessage(explorer, ALREDY_DEPLOYED));
		SurfaceScanResult scanResult = scan(explorer, explorer.getCurrentPosition());
		Validate.isTrue(OK.equals(scanResult), createDeployErrorMessage(explorer, scanResult.getMessage()));
		deployedExplorers.add(explorer);
		return explorer;
	}

	private boolean notDeployed(final Explorer explorer) {
		Predicate<Explorer> sameId = deployed -> deployed.getId().equals(explorer.getId());
		return getDeployedExplorers().stream().noneMatch(sameId);
	}

	private String createDeployErrorMessage(Explorer explorer, String message) {
		return "Cannot deploy explorer: " + explorer.getId() + " cause: " + message;
	}

	@Override
	public SurfaceScanResult scan(final Explorer explorer, final ExplorerPosition position) {
		if (isPositionOutOfBoundary(position)) {
			return OUT_OF_BOUNDARY;
		}
		if (isCollisionPosition(explorer, position)) {
			return COLLISION;
		}
		return OK;
	}

	private boolean isPositionOutOfBoundary(final ExplorerPosition position) {
		return (position.getxAxis() < 0
				|| position.getyAxis() < 0
				|| position.getxAxis() > getxAxis()
				|| position.getyAxis() > getyAxis());
	}

	private boolean isCollisionPosition(final Explorer explorer, final ExplorerPosition position) {
		Predicate<Explorer> collisionDetected = (deployed) -> {
			return isNotSameId(deployed, explorer) && isSamePosition(deployed.getCurrentPosition(), position);
		};
		return getDeployedExplorers().stream().anyMatch(collisionDetected);
	}

	private boolean isNotSameId(final Explorer deployed, final Explorer explorer) {
		return !(deployed.getId().equals(explorer.getId()));
	}

	private boolean isSamePosition(final ExplorerPosition deployedPosition, final ExplorerPosition position) {
		return deployedPosition.equals(position);
	}

	@Override
	public int getxAxis() {
		return xAxis;
	}

	public void setxAxis(int xAxis) {
		this.xAxis = xAxis;
	}

	@Override
	public int getyAxis() {
		return yAxis;
	}

	public void setyAxis(int yAxis) {
		this.yAxis = yAxis;
	}

	@Override
	public List<Explorer> getDeployedExplorers() {
		if (deployedExplorers == null) {
			this.deployedExplorers = new ArrayList<>();
		}
		return Collections.unmodifiableList(deployedExplorers);
	}

	public void setDeployedExplorers(List<Explorer> deployedExplorers) {
		this.deployedExplorers = deployedExplorers;
	}

	@Override
	public Collection<String> getExplorersPosition() {
		Collection<String> positions = new ArrayList<>();
		getDeployedExplorers().forEach((deployed) -> {
			positions.add(deployed.getCurrentPosition().getFormmatedPosition());
		});
		return positions;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 97 * hash + Objects.hashCode(this.id);
		hash = 97 * hash + this.xAxis;
		hash = 97 * hash + this.yAxis;
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
		final Plateau other = (Plateau) obj;
		if (this.xAxis != other.xAxis) {
			return false;
		}
		if (this.yAxis != other.yAxis) {
			return false;
		}
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		if (!Objects.equals(this.deployedExplorers, other.deployedExplorers)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Plateau[" + "id=" + id + ", xAxis=" + xAxis + ", yAxis=" + yAxis + ", deployedExplorers=" + deployedExplorers + ']';
	}
}
