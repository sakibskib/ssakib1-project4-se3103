package model.observerPattern;

import java.awt.Color;

import model.EnemyComposite;
import model.GameElement;
import view.Gameboard;
import view.TextDraw;

public class ShooterObserver implements Observer {
    private Gameboard gameBoard;
    private EnemyComposite enemyComposite;

    public ShooterObserver(Gameboard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void bottomReachedd() {
        System.out.println("bismillah");
        System.out.println("touched bottom");

        // gameBoard.gameOver(Gameboard.Event.BottomReached);
        System.out.println("eikhanei sesh v2");
        gameBoard.setGameOver(true);
        gameBoard.getTimer().stop();
        gameBoard.getCanvas().getGameElements().add(new TextDraw("Game Over - You Lost", 150, 170, Color.red, 30));
        // gameBoard.getCanvas().getGameElements().add(
        // new TextDraw("Enemy reach bottom Score:" + enemyComposite.getScore(), 150,
        // 200, Color.red, 20));

    }

    @Override
    public void shooterDestroyed() {
        // gameBoard.gameOver(Gameboard.Event.ShooterDestroyed);
        // System.out.println("eikhanei sesh");
        gameBoard.setGameOver(true);
        gameBoard.getTimer().stop();
        gameBoard.getCanvas().getGameElements().add(new TextDraw("Game Over - You Lost", 150, 170, Color.red, 30));

    }

    @Override
    public void enemyCompositeEmpty() {
        gameBoard.setGameOver(true);
        gameBoard.getTimer().stop();
        gameBoard.getCanvas().getGameElements().add(new TextDraw("Game Over - You Won", 150, 170, Color.green, 30));
    }

    @Override
    public void hitBullet() {
        // TODO Auto-generated method stub

    }

}
