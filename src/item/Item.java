package item;

public enum Item {
	//items will have their buffs stored in an array
	
	//example superitem will give +1 to hp +2 to attack +3 attack range and +4 to moverange
	//[1,2,3,4]
	//path is relative to the OS and current directory
	superitem(new int[] {4,4,4,4},
			System.getProperty("user.dir") + System.getProperty("file.separator") + "images"
					+ System.getProperty("file.separator") + "items" + System.getProperty("file.separator") + "ShopItem1.png",
			"Super Item", 20),
	hyperpotion(new int[] {4,0,0,0},
			System.getProperty("user.dir") + System.getProperty("file.separator") + "images"
					+ System.getProperty("file.separator") + "items" + System.getProperty("file.separator") + "ShopItem2.png",
			"Hyper Potion",5),
	attackRange(new int[] {1,2,4,0}, 
			System.getProperty("user.dir") + System.getProperty("file.separator") + "images"
					+ System.getProperty("file.separator") + "items" + System.getProperty("file.separator") + "ShopItem3.png",
			"Apollo's Helm", 6),
	moveRange(new int[] {2,1,1,4},
			System.getProperty("user.dir") + System.getProperty("file.separator") + "images"
					+ System.getProperty("file.separator") + "items" + System.getProperty("file.separator") + "ShopItem4.png",
			"Speed Enhancer", 3);

	private int[] modifiers;
	private String path;
	private String name;
	private int cost;
	
	private Item(int[] array, String path, String name, int cost){
		this.modifiers = array;
		this.path = path;
		this.name = name;
		this.cost = cost;
	}
	
	public int[] getModifiers(){
		return modifiers;
	}
	public String getPath(){
		return path;
	}
	public int getCost(){
		return cost;
	}

	/**
	 * 
	 * @return the name of the current item.
	 * one of "Super Item", "Hyper Potion", "Apollo's Helm", "Speed Enhancer".
	 */
	public String getName(){
		return name;
	}
}
