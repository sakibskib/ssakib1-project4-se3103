package model.builderPattern;

import model.Bomb;

public abstract class BombBuilder {
    protected Bomb bomb;
    int x, y;
    public Bomb getBomb(){
        return bomb;
    }
    public void createBomb(){
        bomb =new Bomb(x,y);
    }
    public abstract void buildShape();
    public abstract void buildColor();
    public abstract void buildStrategy();

}
