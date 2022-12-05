package model;

import java.awt.Color;
import java.awt.Graphics2D;

public class Sheilds extends GameElement {

    public Sheilds(int x, int y, int size, Color color, boolean filled){
        super(x,y, color, filled, size, size);
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(color);
        if(filled)
        g2.fillRect(x, y, width, height);
        else g2.drawRect(x, y, width, height);
    }

    @Override
    public void animate() {
        // TODO Auto-generated method stub
        
    }
    
}
