package item;
import java.io.Serializable;
import java.util.HashMap;
import view.GUI;
public class Inventory implements Serializable {
	private static final long serialVersionUID = 4612177081063302900L;
	private HashMap<String, Item> items;
	private static String username;
	private int credits;

	public Inventory() {
		items = new HashMap<String, Item>();
		//will need to handle how to get the different usernames when switching teams, for right now its static but im sure we can make it dynamic ezpz 
		username = GUI.getPlayer1();
		credits = 0;
	}

	public boolean addItem(String s) {
		if(s.equalsIgnoreCase("superitem")) {
			return items.put(s, Item.superitem) != null;
		}
		else if(s.equalsIgnoreCase("" /*item name*/)) {
			return items.put(s, Item.superitem) != null;  // TODO change the Item enum to
														  // whatever the string is, add other item types 
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