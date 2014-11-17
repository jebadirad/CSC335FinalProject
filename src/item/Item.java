package item;

public enum Item {
	//items will have their buffs stored in an array
	
	//example superitem will give +1 to hp +2 to attack +3attack range and +4 to moverange
	//[1,2,3,4]
	superitem(new int[] {1,2,3,4}, "/images/thisisacoolimage.jpg");
	
	private int[] modifiers;
	private String path;
	
	private Item(int[] array, String path){
		this.modifiers = array;
		this.path = path;
		
	}
	
	public int[] getModifiers(){
		return modifiers;
	}
	public String getPath(){
		return path;
	}
	
	
	
}
