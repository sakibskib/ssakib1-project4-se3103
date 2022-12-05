package model.strategyPattern;

import java.awt.Color;

import model.EnemyComposite;
import view.Gameboard;
import view.MyCanvas;
import view.TextDraw;

public class LevelOne implements LevelSelector {

    private Gameboard gameboard;
    private MyCanvas canvas;

public LevelOne(Gameboard gameboard){
    this.gameboard= gameboard;
    canvas = gameboard.getCanvas();
    canvas.getGameElements().add(new TextDraw("Level 1", 0, 270, Color.red, 15));
}


    @Override
    public void restartEnemies() {
        //gameboard.getEnemyComposite().resetEnemyComposite();
        
    }

    @Override
    public void levelSettings() {
        EnemyComposite.UNIT_MOVE = 3;
        EnemyComposite.NCOLS = 5;
        
    }
    
}
