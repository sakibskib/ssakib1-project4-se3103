package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import view.Gameboard;

public class SheildsComposite extends GameElement{

    public static final int SROW = 1;
    public static final int SCOL = 12;
    public static final int SHEILDS_SIZE = 10;
    public static final int SHEILDS_MOVE = 5;
    

    private ArrayList<ArrayList<GameElement>> row;
    private boolean movingToRight = true;
    private boolean touchedEnd = false;

    
    public SheildsComposite(){
        row= new ArrayList<>();
        for (int r = 0; r < SROW; r++) {
            var oneRow = new ArrayList<GameElement>();
            row.add(oneRow);
            for (int c = 0; c < SCOL; c++) {
                oneRow.add(new Sheilds((c * SHEILDS_SIZE), (r * SHEILDS_SIZE *2)+200, SHEILDS_SIZE, Color.red, true));
                oneRow.add(new Sheilds((c * SHEILDS_SIZE)+300, (r * SHEILDS_SIZE *2)+200, SHEILDS_SIZE, Color.red, true));

            }
        }
        // for (int r = 1; r < 2; r++) {
        //     var oneRow = new ArrayList<GameElement>();
        //     row.add(oneRow);
        //     for (int c = 0; c < SCOL; c++) {
        //         oneRow.add(new Sheilds((c * SHEILDS_SIZE)+300, (r * SHEILDS_SIZE *2)+200, SHEILDS_SIZE, Color.red, true));

        //     }
        // }
        

        

    }

    @Override
    public void render(Graphics2D g2) {
        for (var r : row) {
            for (var e : r) {
                e.render(g2);
            }
        }
    }

    @Override
    public void animate() {
        int dx = SHEILDS_MOVE;
        int dy = SHEILDS_SIZE;
        if (movingToRight) {
            if (rightEnd() >= Gameboard.WIDTH) {
                dx = -dx;
                movingToRight = false;
                touchedEnd = true;
                // System.out.println("Touched the right end");
               

            }
        } else {
            dx = -dx;
            if (leftEnd() <= 0) {
                dx = -dx;
                movingToRight = true;
                touchedEnd = true;
                // System.out.println("Touched the left end");
                

            }

        }
        // update xloc
        for (var row : row) {
            for (var e : row) {
                e.x += dx;
            }
        }
        
    }
    private int rightEnd() {
        int xEnd = -100;
        for (var row : row) {
            if (row.size() == 0)
                continue;
            int x = row.get(row.size() - 1).x + SHEILDS_SIZE;
            if (x > xEnd)
                xEnd = x;
        }

        return xEnd;
    }
    
    public int leftEnd() {
        int xEnd = 9000;
        for (var row : row) {
            if (row.size() == 0)
                continue;
            int x = row.get(0).x;
            if (x < xEnd)
                xEnd = x;
        }
        return xEnd;
    }

    public void processCollision(Shooter shooter, EnemyComposite enemyComposite){
        

        //bombs vs shields
        var removeBombs = new ArrayList<GameElement>();
        var bombs= enemyComposite.getBombs();
        //var removeSheilds = new ArrayList<GameElement>();
        for (var rows : row){
            var removeSheilds = new ArrayList<GameElement>();
            for (var c: rows){
                for (var b : bombs ){
                    if (c.collideWith(b)){
                        removeBombs.add(b);
                        removeSheilds.add(c);
                    }
                }
            }
            rows.removeAll(removeSheilds);
            enemyComposite.getBombs().removeAll(removeBombs);

        }

        //shooter vs shields // no friendly fire
        var removeShooter =  new ArrayList<GameElement>();
        for (var row : row) {
            //var removeSheilds = new ArrayList<GameElement>();

            for (var enemy : row) {
                for (var bulles : shooter.getWeapons()) {
                    if (enemy.collideWith(bulles)) {
                        
                        removeShooter.add(bulles);
                        //removeSheilds.add(enemy);

                    }
                }
            }

            //row.removeAll(removeSheilds);

        }
        shooter.getWeapons().removeAll(removeShooter);
        


        //shields vs enemy composite
        //enemy composite gone
        int heightOfEnemy = (enemyComposite.getHeightOfEnemy());
        int heightOfSheilds = (SHEILDS_SIZE*2)+200;
        if (heightOfEnemy == heightOfSheilds){
            for (var rows:row){
                var removeSheilds = new ArrayList<GameElement>();
                for (var c:rows){
                    removeSheilds.add(c);
                }
                rows.removeAll(removeSheilds);
            }
            
        }
    }

    
}
