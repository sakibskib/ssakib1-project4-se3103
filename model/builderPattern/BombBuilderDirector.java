package model.builderPattern;

import model.Bomb;

public class BombBuilderDirector {
    private BombBuilder builder; 
    public void setBombBuilder(BombBuilder builder){
        this.builder= builder;
    }
    public Bomb getBomb(){
        return builder.getBomb();
    }
    public void createBomb(){
        builder.createBomb();
        builder.buildShape();
        builder.buildColor();
        builder.buildStrategy();
        
    }
    
}
