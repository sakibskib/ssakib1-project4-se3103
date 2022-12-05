package model.builderPattern;

import java.awt.Color;

public class RegularBomb extends BombBuilder {
    public RegularBomb(int x, int y){
        this.x=x;
        this.y = y;
    }

    @Override
    public void buildShape() {
        bomb.height = bomb.SIZE*2;
        bomb.width = bomb.SIZE;
    }

    @Override
    public void buildColor() {
        bomb.color = Color.green;
        
    }

    @Override
    public void buildStrategy() {
        // TODO Auto-generated method stub
        
    }
    
    
}
