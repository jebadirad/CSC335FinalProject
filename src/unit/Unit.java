package unit;

import java.io.Serializable;

/**
 * This class is the super class for all Unit types. It has attackRange, damage,
 * health, moveRange, movesLeft, iconImage(String), username, and canAttack.
 * 
 * @author Kal
 */
public abstract class Unit implements Serializable
{
  private int attackRange, damage, health, moveRange, movesLeft;
  private String iconImage;
  private String username;
  private Boolean canAttack;

  /**
   * This is the constructor of the unit class. This sets all the instance
   * variables to what is send by each individual type of Unit.
   * 
   * @param attackRange
   * @param damage
   * @param health
   * @param moveRange
   * @param imagestring
   * @param username
   */
  public Unit(int attackRange, int damage, int health, int moveRange,
      String imagestring, String username)
  {
    this.attackRange = attackRange;
    this.damage = damage;
    this.health = health;
    this.moveRange = moveRange;
    this.movesLeft = this.moveRange;
    this.iconImage = imagestring;
    this.username = username;
    canAttack = true;

  }

  // Each Type of Unit will have a different attack() and levelUp()
  /**
   * Used by units to attack other units. Either increases or decreases health.
   * 
   * @param victim
   */
  public abstract void attack(Unit victim);

  /**
   * Used to increase status of unit when they kill another unit.
   * 
   */
  public abstract void levelUp();

  /**
   * Method to get Unit status.
   * 
   */
  public String getUnitStatus()
  {
    String status = "";
    status += "Unit Type: " + this.toString() + "\n";
    status += "Attack Range: " + this.getAttackRange() + "\n";
    status += "Damage: " + this.getDamage() + "\n";
    status += "Remaining Health: " + this.getHealth() + "\n";
    status += "Moves Left: " + this.getMovesLeft() + "\n";
    return status;
  }

  // Getters for all instance variables.
  public int getAttackRange()
  {
    return attackRange;
  }

  public int getDamage()
  {
    return damage;
  }

  public int getHealth()
  {
    return health;
  }

  public int getMoveRange()
  {
    return moveRange;
  }

  public int getMovesLeft()
  {
    return movesLeft;
  }

  public String getIconImage()
  {
    return iconImage;
  }

  public String getUsername()
  {
    return username;
  }

  public Boolean getCanAttack()
  {
    return canAttack;
  }

  // Setters for all instance variables
  public void setAttackRange(int attackrange)
  {
    this.attackRange = attackrange;
  }

  public void setDamage(int damage)
  {
    this.damage = damage;
  }

  public void setHealth(int health)
  {
    this.health = health;
  }

  public void setMoveRange(int moverange)
  {
    this.moveRange = moverange;
  }

  public void setMovesLeft(int movesLeft)
  {
    this.movesLeft = movesLeft;
  }

  public void setIconImage(String icon)
  {
    this.iconImage = icon;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public void setCanAttack(Boolean value)
  {
    canAttack = value;
  }

}