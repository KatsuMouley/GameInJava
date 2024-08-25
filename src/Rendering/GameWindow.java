package Rendering;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import KeyListener.KeyHandler;

public class GameWindow extends JPanel implements Runnable{
    //SETTINGS
    final int screenWidth = 1600, screenHeight = 900;
    
    //FRAMES POR SEGUNDO
    final int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //Player Default Position
    int player_X = 400, player_Y = 400;
    int player_Speed = 4;

    public GameWindow(){
        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() { //Mantem o jogo rodando

        // 1 segundo dividido por FPS
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
    //GameLoop vem aqui dentro

            //checa os nanosegundos do sistema
            //Atualizar informações como posição
            update();
            //Renderizar a tela com a informação atualizada
            repaint();

            //Pausa o programa por //0.01666 segundos
            // No que resulta em um jogo que roda 60 frames por segundo
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                
                nextDrawTime += drawInterval;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){
    //Atualização

    if(keyH.upPressed == true){
        player_Y -= player_Speed;
    }
    else if(keyH.downPressed == true){
        player_Y += player_Speed;
    }
    else if(keyH.leftPressed == true){
        player_X -= player_Speed;
    }
    else if(keyH.rightPressed == true){
        player_X += player_Speed;
    }
    
    }

    public void paintComponent(Graphics g){
        //Renderizador de grafícos 2D

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.GREEN);
        g2.fillRect(player_X, player_Y, 70, 70 );
        g2.dispose();
    }
}
