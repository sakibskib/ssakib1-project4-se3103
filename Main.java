import javax.swing.JFrame;

import view.Gameboard;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Space Invader Game");
        window.setLocation(600, 200);

        var game = new Gameboard(window);
        game.init();

        window.setResizable(false);
        window.pack();
        window.setVisible(true);

    }
}
