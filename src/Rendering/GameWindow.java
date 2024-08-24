package Rendering;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
    public void run() { //Mantem o jogo rodando
        while (gameThread != null) {
    //GameLoop vem aqui dentro
            
            System.out.println("Game Running");

            //Atualizar informações como posição
            update();
            //Renderizar a tela com a informação atualizada
            repaint();
        }
    }

    public void update(){
    //Atualização
    
    }

    public void paintComponent(Graphics g){
        //Renderizador de grafícos 2D

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.GREEN);
        g2.fillRect(400, 400, 70, 70);
        g2.dispose();
    }
}
