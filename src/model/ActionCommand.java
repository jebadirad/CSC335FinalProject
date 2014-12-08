package model;

import view.GUI;

public class ActionCommand extends Command<GUI>{


	
	
		
	

	@Override
	public void execute(GUI executeOn, String direction) {
		// TODO Auto-generated method stub
		executeOn.move(direction);
	}

}
