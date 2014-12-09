package model;

import view.GUI;

public class AI {
	public static final int WAIT_TIME = 500;
	public AI() {
		new Thread(new Runner()).start();
		
	}

	public void makeMove() {
				
				for(int i = 0; i < GUI.gameboard.getPlayer1Units().size(); i++){
					Cell cell = GUI.gameboard.getPlayer1Units().get(i);
					for(int j=0; j < cell.getUnit().getMoveRange(); j++){
						
						ActionCommand moveleft = new ActionCommand("L", cell);
						ActionCommand moveright = new ActionCommand("R", cell);
						if(i < 1){
								GUI.gameboard.commandqueue.add(moveleft);
								System.out.println(GUI.gameboard.commandqueue.size());
						}
							else{
								GUI.gameboard.commandqueue.add(moveright);
								System.out.println(GUI.gameboard.commandqueue.size());
							}
					}
				
				
				}
				
				
				
				
				
		
	}
	
	private class Runner implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try{
				while(true){
					
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}

}
