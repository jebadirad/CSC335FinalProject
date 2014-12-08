package model;

import java.io.Serializable;

public abstract class Command<T> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4838155228547508978L;




	public abstract void execute(T executeOn,String direction);
	
	

	
	public void undo(T undoOn){
		
	}
	
}
