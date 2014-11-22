package item;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

import view.GUI;
public class Inventory implements Serializable {
	private static final long serialVersionUID = 4612177081063302900L;
	private static final String saveDir = System.getProperty("user.dir") + File.separator + "gamesaves" + File.separator;
	private HashMap<String, Item> items;
	private String username;
	private int credits;

	/**
	 * @deprecated pls do not use.
	 */
	public Inventory() {
		items = new HashMap<String, Item>();
		//will need to handle how to get the different usernames when switching teams, for right now its static but im sure we can make it dynamic ezpz 
		username = GUI.getPlayer1();
		credits = 0;
	}

	/**
	 * The username is passed as an argument to the constructor.
	 * Each player will have their own inventory that no one but they need to know.
	 *  
	 * @param username
	 */
	public Inventory(String username) {		// username is now passed as an argument to the constructor
		items = new HashMap<String, Item>();
		this.username = username;			// no longer needs to be static
		credits = 0;
		saveData();
	}

	public boolean addItem(String s) {
		if(s.equalsIgnoreCase("Super Item")) {
			return items.put(s, Item.superitem) != null;
		}
		else if(s.equalsIgnoreCase("Hyper Potion")) {
			return items.put(s, Item.hyperpotion) != null;
		}
		else if(s.equalsIgnoreCase("Apollo's Helm")) {
			return items.put(s, Item.attackRange) != null;
		}
		else if(s.equalsIgnoreCase("Speed Enhancer")) {
			return items.put(s, Item.moveRange) != null;
		}
		else return false;
	}

	public boolean removeItem(String s) {
		return items.remove(s) != null;
	}

	public boolean hasItem(String item) {
		return items.containsKey(item);
	}

	public Item getItem(String s) {
		return items.get(s);
	}

	public String getUsername() {
		return username;
	}

	/**
	 * This method expects a subtraction or an addition operation as its argument.
	 * Since your credit balance cannot be less than zero, arguments that result
	 * in a negative balance will be ignored.
	 * 
	 *  
	 * @param newBalance
	 * 
	 * Arguments should look like {@code totalCredits - itemCost}
	 */
	public void setCredits(int newBalance) {
		if(newBalance>=0) {
			credits = newBalance;
		} else return;
	}

	public int getCredits() {
		return credits;
	}

	/**
	 * This method attempts to load inventory data from "./<username>-inventory.dat"
	 * 
	 * @return true if successful, false otherwise
	 */
	@SuppressWarnings("unchecked")
	public boolean loadData(String username) {
		try {
			FileInputStream fileIn = new FileInputStream(new File(saveDir + username + "-inventory.dat"));
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			this.items = (HashMap<String, Item>) objectIn.readObject();
			this.credits = (int) objectIn.readObject();
			this.username = (String) objectIn.readObject();
			objectIn.close();
			return true;
		} catch (Exception e){
			System.err.println("Unable to load data!");
		}
		return false;
	}

	/**
	 * This method attempts to save the inventory map in "./<username>-inventory.dat"
	 */
	public void saveData() {
		try {
			FileOutputStream fileOut = new FileOutputStream(new File(saveDir + username + "-inventory.dat"));
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(items);
			objectOut.writeObject(credits);
			objectOut.writeObject(username);
			objectOut.close();
		} catch (Exception e){
			System.err.println("Error! Could not save data.");
		}
	}

}