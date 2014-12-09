package model;

import view.GUI;

public class ChangeUnitCommand extends Command<GUI> {
	Cell cellwithunit;
	public ChangeUnitCommand(Cell unit){
		this.cellwithunit = unit;
	}
	@Override
	public void execute(GUI executeOn) {
		// TODO Auto-generated method stub
		executeOn.changeUnit(cellwithunit);
	}

	@Override
	public void setCurrentCell(Cell unit) {
		// TODO Auto-generated method stub
		return;
	}

}
