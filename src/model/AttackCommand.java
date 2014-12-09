package model;

import view.GUI;


public class AttackCommand extends Command<GUI>{

	Cell cellattack;
	Cell celldefense;
	public AttackCommand(Cell cellattack, Cell celldefense){
		this.cellattack = cellattack;
		this.celldefense = celldefense;
		
	}
	
		
	public Cell getCellDefense(){
		return celldefense;
	}
	

	@Override
	public void execute(GUI executeOn) {
		// TODO Auto-generated method stub
		executeOn.attack(this.cellattack, this.celldefense);
	}

}
