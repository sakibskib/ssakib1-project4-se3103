package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.KeyController;
import controller.TimeListener;
import model.EnemyComposite;
import model.Shooter;
import model.ShooterElement;

public class Gameboard {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 300;
    public static final int FPS = 20 ;

    public static final int DELAY= 1000/FPS ;

    private JFrame window;
    private MyCanvas canvas;
    private Shooter shooter;
    private Timer timer;
    private TimeListener timeListener;
    private EnemyComposite enemyComposite;


    public Gameboard(JFrame window){
        this.window= window;

    }

    public void init(){
        Container cp = window.getContentPane();
        canvas  = new MyCanvas(this, WIDTH, HEIGHT);
        cp.add(BorderLayout.CENTER, canvas);
        canvas.addKeyListener(new KeyController(this));
        canvas.requestFocusInWindow();
        canvas.setFocusable(true);

        JButton startButton = new JButton("Start ");
        JButton quitButton = new JButton("Quit");
        startButton.setFocusable(false);
        quitButton.setFocusable(false);

        JPanel southPanel = new JPanel();
        southPanel.add(startButton);
        southPanel.add(quitButton);
        cp.add(BorderLayout.SOUTH,southPanel);

        canvas.getGameElements().add(new TextDraw("Click<Start> to Play ", 100, 100, Color.yellow  , 30));

        //shooter = new Shooter(Gameboard.WIDTH/2, Gameboard.HEIGHT-ShooterElement.SIZE);
        timeListener = new TimeListener(this);
        timer = new Timer(DELAY, timeListener);
        // canvas.getGameElements().add(shooter);

        startButton.addActionListener(event -> {
        shooter = new Shooter(Gameboard.WIDTH/2, Gameboard.HEIGHT-ShooterElement.SIZE);
        enemyComposite = new EnemyComposite();
        canvas.getGameElements().clear();
        canvas.getGameElements().add(shooter);
        canvas.getGameElements().add(enemyComposite);
            timer.start();
        });

        quitButton.addActionListener(event -> System.exit(0));

    }
    public MyCanvas getCanvas() {
        return canvas;
    }
    public TimeListener getTimeListener() {
        return timeListener;
    }
    public Timer getTimer() {
        return timer;
    }
    public Shooter getShooter() {
        return shooter;
    }
    public EnemyComposite getEnemyComposite() {
        return enemyComposite;
    }
}
