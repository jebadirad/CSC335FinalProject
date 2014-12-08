package model;

import view.GUI;

public class ActionCommand extends Command<GUI>{


	String directions;
	Cell cellwithunit;
	public ActionCommand(String directions, Cell cellwithunit){
		this.directions = directions;
		this.cellwithunit = cellwithunit;
	}
	
		
	public Cell getCurrentUnit(){
		return cellwithunit;
	}
	

	@Override
	public void execute(GUI executeOn) {
		// TODO Auto-generated method stub
		executeOn.move(this.directions, this.cellwithunit);
	}

}
