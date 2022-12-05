package model.builderPattern;

import java.awt.Color;

import model.Bomb;

public class BigBomb extends BombBuilder {

    public BigBomb(int x, int y){
        this.x=x;
        this.y=y;
    }
    @Override
    public void buildShape() {
        bomb.height = Bomb.SIZE*4;
        bomb.width = Bomb.SIZE*2;

        
    }

    @Override
    public void buildColor() {
        bomb.color= Color.blue;
        
    }

    @Override
    public void buildStrategy() {
        // TODO Auto-generated method stub
        
    }
    
}
