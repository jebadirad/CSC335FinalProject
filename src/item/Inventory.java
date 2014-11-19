package item;
import java.io.Serializable;
import java.util.HashMap;
import view.GUI;
public class Inventory implements Serializable {
	private static final long serialVersionUID = 4612177081063302900L;
	private HashMap<String, Item> items;
	private String username;
	private int credits;

	/**
	 * @deprecated
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
}