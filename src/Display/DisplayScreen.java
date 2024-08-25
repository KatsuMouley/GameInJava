package Display;

import javax.swing.JFrame;

import Rendering.GameWindow;
public class DisplayScreen {

    public String nomeDoJogo;

    public DisplayScreen (String nomeDoJogo)
    {
        this.nomeDoJogo = nomeDoJogo;
    }

    public void run(){
        //tela
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle(nomeDoJogo);

        GameWindow gamePanel = new GameWindow();
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
    
}
