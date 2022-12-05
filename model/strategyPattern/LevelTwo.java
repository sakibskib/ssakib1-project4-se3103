package model.strategyPattern;

import java.awt.Canvas;

import model.EnemyComposite;
import view.Gameboard;
import view.MyCanvas;

public class LevelTwo implements LevelBuilder{
MyCanvas canvas;
EnemyComposite enemyComposite;
    @Override
    public void update() {
        enemyComposite= new EnemyComposite();
        enemyComposite.NCOLS = 15;
        enemyComposite.UNIT_MOVE = 7;
       Gameboard.FPS = 30;
       
       render();



        
    }

    @Override
    public void render() {
        canvas.getGameElements().add(enemyComposite);
        canvas.repaint();
        
    }
    
}
