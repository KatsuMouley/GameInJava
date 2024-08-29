
package rendering;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import keylistener.KeyHandler;
import players.DrawPlayer;

public class GameWindow extends JPanel implements Runnable {
    // SETTINGS
    public final int screenWidth = 1200;
    public final int screenHeight = 675;

    // FRAMES POR SEGUNDO
    final int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    DrawPlayer player = new DrawPlayer();

    public GameWindow() {
        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() { // Mantem o jogo rodando

        // 1 segundo dividido por FPS
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            // GameLoop vem aqui dentro

            // checa os nanosegundos do sistema
            // Atualizar informações como posição
            update();
            // Renderizar a tela com a informação atualizada
            repaint();

            // Pausa o programa por //0.01666 segundos
            // No que resulta em um jogo que roda 60 frames por segundo
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

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

    public void update() {
        // Atualização
        player.getPlayers().get(1).movement(1, keyH);
        player.getPlayers().get(1).Hitbox();
        player.getPlayers().get(0).movement(2, keyH);
        player.getPlayers().get(0).Hitbox();
    }

    // Agora precisamos adicionar as seguintes mecânicas
    // Gravidade Colisão com objetos e os próprios objetos em si
    public void paintComponent(Graphics g) {
        // Renderizador de grafícos 2D

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        player.getPlayers().get(0).drawRect(g2);
        player.getPlayers().get(1).drawRect(g2);
        g2.dispose();
    }
}
