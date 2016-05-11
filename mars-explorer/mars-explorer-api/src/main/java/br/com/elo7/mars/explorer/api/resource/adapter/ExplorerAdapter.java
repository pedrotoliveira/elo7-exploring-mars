package br.com.elo7.mars.explorer.api.resource.adapter;

import br.com.elo7.mars.explorer.api.resource.ExecutionResultResource;
import br.com.elo7.mars.explorer.api.resource.ExplorerResource;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExecutionResult;
import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

/**
 * Explorer Adapter
 *
 * @author pedrotoliveira
 */
@Component
public class ExplorerAdapter implements ResourceAdapter<Explorer, ExplorerResource> {

    @Override
    public Resource<ExplorerResource> adaptExpandedResource(Explorer domain) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Resource<ExplorerResource> adaptResource(Explorer domain) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Resources<ExplorerResource> adaptAll(Collection<Explorer> domainCollection) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<ExplorerResource> map(Collection<Explorer> deployedExplorers) {
        List<ExplorerResource> explorerResources = new ArrayList<>();
        deployedExplorers.stream().forEach((Explorer explorer) -> {
            ExplorerResource explorerResource = new ExplorerResource()
                    .id(explorer.getId())
                    .currentPosition(explorer.getCurrentPosition())
                    .instructions(explorer.getRegisteredInstructions())
                    .executionResults(mapExecutions(explorer.getExecutionResults()));

            explorerResources.add(explorerResource);
        });
        return explorerResources;
    }

    private List<ExecutionResultResource> mapExecutions(Collection<ExecutionResult> executionResults) {
        List<ExecutionResultResource> executionResultResources = new ArrayList<>();
        executionResults.stream().forEach((ExecutionResult result) -> {
            ExecutionResultResource executionResultResource = new ExecutionResultResource()
                    .startPosition(result.getStartPosition())
                    .finalPosition(result.getFinalPosition())
                    .status(result.getStatus())
                    .instruction(result.getInstructionRepresentation())
                    .notifications(result.getNotifications());

            executionResultResources.add(executionResultResource);
        });
        return executionResultResources;
    }
}
