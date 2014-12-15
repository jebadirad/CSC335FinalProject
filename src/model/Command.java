package model;

import java.io.Serializable;

public abstract class Command<T> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4838155228547508978L;




	public abstract void execute(T executeOn);
	
	public Cell getCurrentCell(){
		return null;
	}
	public abstract void setCurrentCell(Cell unit);
	public abstract String getCommandType();
	public void undo(T undoOn){
		
	}
	
}
