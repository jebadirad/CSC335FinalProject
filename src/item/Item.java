package item;

public enum Item {
	//items will have their buffs stored in an array
	
	//example superitem will give +1 to hp +2 to attack +3attack range and +4 to moverange
	//[1,2,3,4]
	superitem(new int[] {1,2,3,4});
	
	private int[] modifiers;
	
	
	private Item(int[] array){
		this.modifiers = array;
		
	}
	
	public int[] getModifiers(){
		return modifiers;
	}
	
	
	
}
