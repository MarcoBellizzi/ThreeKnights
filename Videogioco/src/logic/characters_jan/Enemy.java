package logic.characters_jan;

import logic_jan.Coin;

public interface Enemy {
public Coin coin = new Coin();
public int getLeftPoint();
public int getHighestPoint();
public int getLowestPoint();
public int getRightPoint();
public void hurt();
public void pickUpCoin();
}
