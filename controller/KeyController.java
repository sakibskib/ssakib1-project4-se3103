package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.Gameboard;

public class KeyController implements KeyListener{
    private Gameboard gameBoard;

    public KeyController(Gameboard gameboard){
        this.gameBoard = gameboard;


    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        var eventQueue = gameBoard.getTimeListener().getEventQueue();
        switch(keyCode){
            case KeyEvent.VK_LEFT:
            eventQueue.add(TimeListener.EventType.KEY_LEFT);
            break;
            case KeyEvent.VK_RIGHT:
            // System.out.println("right");
            
            eventQueue.add(TimeListener.EventType.KEY_RIGHT);

            break;
            case KeyEvent.VK_SPACE:

            // System.out.println("space");
            eventQueue.add(TimeListener.EventType.KEY_SPACE);

            break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
