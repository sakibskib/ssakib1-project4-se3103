package model;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import view.Gameboard;
import view.MyCanvas;
import view.TextDraw;

public class EnemyComposite extends GameElement {

    public static final int NROW = 2;
    public static final int NCOLS = 10;
    public static final int ENEMY_SIZE = 20;
    public static final int UNIT_MOVE = 5;

    public enum Event {
        Scored, Win, Lose
    }

    private ArrayList<ArrayList<GameElement>> rows;

    private ArrayList<GameElement> bombs;
    private Random random = new Random();

    private Gameboard gameboard;
    private boolean movingToRight = true;
    private int heightOfEnemy = 0;
    private boolean touchedEnd = false;
    private boolean touchedBottom;
    private int score =0;
    private MyCanvas canvas;

    public EnemyComposite() {
        rows = new ArrayList<>();
        bombs = new ArrayList<>();
        for (int r = 0; r < NROW; r++) {
            var oneRow = new ArrayList<GameElement>();
            rows.add(oneRow);
            for (int c = 0; c < NCOLS; c++) {
                oneRow.add(new Enemy(c * ENEMY_SIZE * 2, r * ENEMY_SIZE * 2, ENEMY_SIZE, Color.yellow, true));

            }
        }
    }

    @Override
    public void render(Graphics2D g2) {
        // render enemy array
        for (var r : rows) {
            for (var e : r) {
                e.render(g2);
            }
        }

        // render bombs
        for (var b : bombs) {
            b.render(g2);
        }

    }

    @Override
    public void animate() {
        int dx = UNIT_MOVE;
        int dy = ENEMY_SIZE;
        if (movingToRight) {
            if (rightEnd() >= Gameboard.WIDTH) {
                dx = -dx;
                // dy=+dy;

                // for(var col: rows){
                // for (var e: col){
                // e.y +=dy;
                // }
                // }

                movingToRight = false;
                touchedEnd = true;
                System.out.println("Touched the right end");
                enemyArrayGoingDown();

            }
        } else {
            dx = -dx;
            if (leftEnd() <= 0) {
                dx = -dx;
                movingToRight = true;
                touchedEnd = true;
                System.out.println("Touched the left end");
                enemyArrayGoingDown();

            }

        }
        // update xloc
        for (var row : rows) {
            for (var e : row) {
                e.x += dx;
            }
        }

        // update yloc
        // for(var col: cols){
        // for (var e: col){
        // e.y +=dy;
        // }
        // }

        // animate bombs
        for (var b : bombs) {
            b.animate();
        }

    }

    public int getScore() {
        return score;
    }
public ArrayList<GameElement> getBombs() {
    return bombs;
}

    public void setScore(int score) {
        this.score = score;
    }
    public int getHeightOfEnemy() {
        return heightOfEnemy;
    }

    private void enemyArrayGoingDown() {
        int dy = ENEMY_SIZE;
        //int heightOfEnemy = 0;
        for (var col : rows) {
            for (var e : col) {
                e.y += dy;
                heightOfEnemy = e.y;
            }
        }
        if (heightOfEnemy == Gameboard.HEIGHT) {
            touchedBottom = true;
            System.out.println("touched bottom");
        }
    }

    private int rightEnd() {
        int xEnd = -100;
        for (var row : rows) {
            if (row.size() == 0)
                continue;
            int x = row.get(row.size() - 1).x + ENEMY_SIZE;
            if (x > xEnd)
                xEnd = x;
        }

        return xEnd;
    }

    private int leftEnd() {
        int xEnd = 9000;
        for (var row : rows) {
            if (row.size() == 0)
                continue;
            int x = row.get(0).x;
            if (x < xEnd)
                xEnd = x;
        }
        return xEnd;
    }

    public void dropBombs() {
        for (var row : rows) {
            for (var e : row) {
                if (random.nextFloat() < 0.1F) {
                    bombs.add(new Bomb(e.x, e.y));
                }
            }
        }
    }

    public void removeBombsOutOfBound() {
        var remove = new ArrayList<GameElement>();
        for (var b : bombs) {
            if (b.y >= Gameboard.HEIGHT) {
                remove.add(b);
            }
        }
        bombs.removeAll(remove);
    }

    public void processCollision(Shooter shooter) {
        var removeBullets = new ArrayList<GameElement>();

        // bullets vs enemies
        for (var row : rows) {
            var removeEnemies = new ArrayList<GameElement>();
            
            for (var enemy : row) {
                for (var bulles : shooter.getWeapons()) {
                    if (enemy.collideWith(bulles)) {
                        score+=10;
                        System.out.println("Score is: " + score);
                    
                    //    gameboard.setScore(score);

                       
            // canvas.getGameElements().add(new TextDraw("Score is: "+score,100, 100, Color.red, 15));
        
                        removeBullets.add(bulles);
                        removeEnemies.add(enemy);

                    }
                }
            }
           
            row.removeAll(removeEnemies);

        }
        shooter.getWeapons().removeAll(removeBullets);
        // score += 10;
        // System.out.println("Score is: " + score);

        // bullets vs bombs

        var removeBombs = new ArrayList<GameElement>();
        removeBullets.clear();
        for (var b : bombs) {
            for (var bullet : shooter.getWeapons()) {
                if (b.collideWith(bullet)) {
                    removeBombs.add(b);
                    removeBullets.add(bullet);
                }
            }
        }
        shooter.getWeapons().removeAll(removeBullets);
        bombs.removeAll(removeBombs);

    }
    // public void scoreDisplay(){
            
    //         canvas.getGameElements().add(new TextDraw("Score is:" + score, 100, 150, Color.red, 20));
    //     }

    
    
}
