package item;

public enum Item {
	//items will have their buffs stored in an array
	
	//example superitem will give +1 to hp +2 to attack +3 attack range and +4 to moverange
	//[1,2,3,4]
	superitem(new int[] {4,4,4,4}, "/Images/superitem.png", "Super Item"),
	hyperpotion(new int[] {4,0,0,0}, "/Images/hyperpotion.png", "Hyper Potion"),
	attackRange(new int[] {1,2,4,0}, "/Images/attackRange.png", "Apollo's Helm"),
	moveRange(new int[] {2,1,1,4}, "/Images/moveRange.png", "Speed Enhancer");
	
	
	private int[] modifiers;
	private String path;
	private String name;
	
	private Item(int[] array, String path, String name){
		this.modifiers = array;
		this.path = path;
		this.name = name;
		
	}
	
	public int[] getModifiers(){
		return modifiers;
	}
	public String getPath(){
		return path;
	}
	public String getName(){
		return name;
	}
}
