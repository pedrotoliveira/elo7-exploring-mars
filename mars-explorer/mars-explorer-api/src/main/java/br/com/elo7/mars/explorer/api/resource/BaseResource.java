package br.com.elo7.mars.explorer.api.resource;

/**
 * Base Resource
 *
 * @author pedrotoliveira
 */
public abstract class BaseResource {

    /**
     * Endpoint Class that expose this resource.
     *
     * @return Endpoint Class
     */
    public abstract Class<?> getEndpointClass();

    /**
     * Link Relation to this Resource
     *
     * @return Link relation
     */
    public abstract String getRel();

}
