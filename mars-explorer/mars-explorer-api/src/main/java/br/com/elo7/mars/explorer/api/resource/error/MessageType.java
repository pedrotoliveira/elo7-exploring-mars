package br.com.elo7.mars.explorer.api.resource.error;

/**
 * ErrorType is a Error Message Type Group.
 * 
 * @author pedrotoliveira
 */
public enum MessageType {
		
	Unexpected_Error("Some type of error occurred."),	
	User_Not_Authorized("Authentication failed or access to some resource is denied."),	
	Parameter_Error("A require param was missing, or malformed."),	
	Range_Limit_Error("The limit of query range is too high."),	
	Endpoint_Error("Some error occurred when try to access the resource."),	
	Business_Logic_Error("Business logic error."),	
	Internal_Architecture_Error("Ooops! some big problem found.");
		
	private final String description;
	
	private MessageType(final String des) {
		description = des;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
}
