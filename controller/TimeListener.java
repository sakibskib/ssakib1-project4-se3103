package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import model.Bullet;
import model.EnemyComposite;
import model.GameElement;
import model.Sheilds;
import model.SheildsComposite;
import model.Shooter;
import model.EnemyComposite.Event;
import model.observerPattern.Subject;
import model.strategyPattern.LevelBuilder;
import model.strategyPattern.LevelTwo;
import view.Gameboard;
import view.MyCanvas;
import view.TextDraw;

public class TimeListener implements ActionListener {
    public enum EventType {
        KEY_RIGHT, KEY_LEFT, KEY_SPACE
    }

    private Gameboard gameBoard;

    private EnemyComposite enemyComposite;
    private EnemyComposite eComposite;
    private SheildsComposite sheildsComposite;
    private LinkedList<EventType> eventQueue;
    private final int BOMB_DROP_FREQ = 20;
    private int frameCounter = 0;
    private int levelCount=0;
    

    public TimeListener(Gameboard gameBoard) {
        this.gameBoard = gameBoard;
        eventQueue = new LinkedList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        ++frameCounter;

        if (gameBoard.isGameOver() == false) {

            update();
            processEventQueue();
            processCollision();
            gameBoard.scoreDisplay();
            gameBoard.getCanvas().repaint();
        }

    }

    private void processEventQueue() {
        while (!eventQueue.isEmpty()) {
            var e = eventQueue.getFirst();
            eventQueue.removeFirst();
            Shooter shooter = gameBoard.getShooter();
            if (shooter == null)
                return;
            switch (e) {
                case KEY_LEFT:
                    shooter.moveLeft();
                    break;
                case KEY_RIGHT:
                    shooter.moveRight();
                    break;
                case KEY_SPACE:
                    if (shooter.canFireMoreBullets())
                        shooter.getWeapons().add(new Bullet(shooter.x, shooter.y));
                    break;

            }
        }
        if (frameCounter == BOMB_DROP_FREQ) {
            gameBoard.getEnemyComposite().dropBombs();
            frameCounter = 0;
        }
    }

    private void processCollision() {

        var shooter = gameBoard.getShooter();
        enemyComposite = gameBoard.getEnemyComposite();
        var sheildsComposite = gameBoard.getSheildsComposite();
        
     
       
        

        if (enemyComposite.getHeightOfEnemy() == Gameboard.HEIGHT) {

            System.out.println("touched bottom");
 enemyComposite.notifyObserver(Event.BottomReachedd);
            //gameBoard.gameOver(Gameboard.Event.BottomReached);
           
            
            // // System.out.println("eikhanei sesh v2");
            // gameBoard.setGameOver(true);
            // gameBoard.getTimer().stop();
            // gameBoard.getCanvas().getGameElements().add(new TextDraw("Game Over - You Lost", 150, 170, Color.red, 30));
            // gameBoard.getCanvas().getGameElements().add(
            //         new TextDraw("Enemy reach bottom Score:" + enemyComposite.getScore(), 150, 200, Color.red, 20));
            
            // gameBoard.gameOver(Gameboard.Event.BottomReached);
            
            // System.out.println("eikhanei sesh v2");
            // gameBoard.setGameOver(true);
            // gameBoard.getTimer().stop();
            // gameBoard.getCanvas().getGameElements().add(new TextDraw("Game Over - You Lost", 150, 170, Color.red, 30));
            gameBoard.getCanvas().getGameElements().add(
                    new TextDraw("Enemy reach bottom Score:" + enemyComposite.getScore(), 150, 200, Color.red, 20));
        }

        else if (shooter.getComponents().isEmpty()) {
            enemyComposite.notifyObserver(Event.ShooterDestroyed);
            // gameBoard.gameOver(Gameboard.Event.ShooterDestroyed);
            // //System.out.println("eikhanei sesh");
            // gameBoard.setGameOver(true);
            // gameBoard.getTimer().stop();
            // gameBoard.getCanvas().getGameElements().add(new TextDraw("Game Over - You Lost", 150, 170, Color.red, 30));
            gameBoard.getCanvas().getGameElements()
                    .add(new TextDraw("Shooter destroyed  Score:" + enemyComposite.getScore(), 150, 200, Color.red, 20));
        } else if (enemyComposite.getRows().get(0).isEmpty() && enemyComposite.getRows().get(1).isEmpty()) {
            // levelCount++;
            // if (levelCount==1){
            //     levelTwo = new LevelTwo();
            //    levelTwo.update();

               



            // }
           // gameBoard.gameOver(Gameboard.Event.EnemyEmpty);
            //System.out.println("eikhanei sesh v3");
            // gameBoard.setGameOver(true);
            // gameBoard.getTimer().stop();
            // gameBoard.getCanvas().getGameElements().add(new TextDraw("Game Over - You Won", 150, 170, Color.green, 30));
            gameBoard.getCanvas().getGameElements()
                    .add(new TextDraw("Enemy destroyed Score:" + enemyComposite.getScore(), 150, 200, Color.green, 20));
                   
       
                } else {
            shooter.removeBulletOutOfBound();
            enemyComposite.removeBombsOutOfBound();
            enemyComposite.processCollision(shooter);
            shooter.processCollision(enemyComposite);
            sheildsComposite.processCollision(shooter, enemyComposite);
            
        
        }

        gameBoard.getCanvas().repaint();
    }

    private void update() {
        for (var e : gameBoard.getCanvas().getGameElements())
            e.animate();
    }

    public LinkedList<EventType> getEventQueue() {
        return eventQueue;
    }


}
