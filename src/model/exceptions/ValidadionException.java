package model.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValidadionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, String> errosMap = new HashMap<String, String>();

	public ValidadionException(String msg) {
		super(msg);
	}

	public Map<String, String> getErros(){
		return errosMap;
	}
	
	public void addErros(String fielName, String errorMessage) {
		errosMap.put(fielName, errorMessage);
	}
	
}
