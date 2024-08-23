package Rendering;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

public class GameWindow extends JPanel implements Runnable{
    //SETTINGS
    final int screenWidth = 1600, screenHeight = 900;

    Thread gameThread;

    public GameWindow(){
        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() { 
    //GameLoop goes in here
    
    }
}
