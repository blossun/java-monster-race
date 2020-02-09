package dev.solar;

public class Fly extends Monster {
    public Fly(String monsterName, String monsterType) {
        super(monsterName, monsterType);
        setForwardCount();
    }

    @Override
    public void setForwardCount() {
        forwardCount = 3;
    }
}
