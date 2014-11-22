package unit;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public abstract class Unit implements Serializable
{
  private int attackRange, damage, health, moveRange, movesLeft;
  private String iconImage;
  private String username;

  // This is the Constructor of Unit which is the abstract class that all units
  // call.
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
  }

  // Each Type of Unit will have a different attack() and levelUp()
  public abstract void attack(Unit victim);

  public abstract void levelUp();

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
}