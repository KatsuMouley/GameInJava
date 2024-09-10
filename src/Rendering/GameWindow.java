
package rendering;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import colisions.Hitbox;
import keylistener.KeyHandler;
import players.Players;
import players.Player;

public class GameWindow extends JPanel implements Runnable {
    // SETTINGS
    public final int baseScreenWidth = 1920;
    public final int baseScreenHeight = 1080;

    // FRAMES POR SEGUNDO
    final int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Players players;
    Hitbox hitbox = new Hitbox();

    // Variáveis de escala e offset
    private double scale;
    private int offsetX;
    private int offsetY;

    // Criação de um objeto estático (plataforma)
    public GameWindow() {
        this.players = new Players();
        players.setPlayers();
        this.setPreferredSize(new DimensionUIResource(baseScreenWidth, baseScreenHeight));
        this.setBackground(Color.black); // Mantém o fundo branco
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() { // Mantém o jogo rodando

        // 1 segundo dividido por FPS
        double drawInterval = 1000000000 / FPS;// INTERVALO DE TEMPO DE FRAMES
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            // Atualizar informações como posição
            update();
            // Renderizar a tela com a informação atualizada
            repaint();
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
        if (players.getPlayers().size() < 2) {
            System.out.println("Not enough players to update.");
            return;
        }

        // Get references to the players
        Player player1 = players.getPlayers().get(1);
        Player player2 = players.getPlayers().get(0);

        // Move the players first
        player1.movement(5, keyH);
        player2.movement(6, keyH);

        // Set the new positions
        player1.setPositions();
        player2.setPositions();

        // Check for collisions
        hitbox.checkPlayerCollision(player1, player2);

        // Check screen boundaries
        hitbox.ScreenHitBox(player1);
        hitbox.ScreenHitBox(player2);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Calcular a escala uniforme com base no tamanho da janela
        Dimension screenSize = getSize();
        double scaleWidth = screenSize.getWidth() / baseScreenWidth;
        double scaleHeight = screenSize.getHeight() / baseScreenHeight;

        // Escolher a menor escala para manter a proporção
        scale = Math.min(scaleWidth, scaleHeight);

        // Calcular o offset para centralizar o conteúdo
        offsetX = (int) ((screenSize.getWidth() - (baseScreenWidth * scale)) / 2);
        offsetY = (int) ((screenSize.getHeight() - (baseScreenHeight * scale)) / 2);

        // Aplicar a transformação de escala ao contexto gráfico
        g2.translate(offsetX, offsetY); // Centralizar
        g2.scale(scale, scale); // Aplicar escala uniforme

        // Desenhar o quadrado preto como background (atrás de tudo)
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, baseScreenWidth, baseScreenHeight); // Desenha o fundo preto

        // Desenhar jogadores ajustados à escala e centralizados
        players.getPlayers().get(0).drawDash(g2, keyH);
        players.getPlayers().get(1).drawDash(g2, keyH);
        players.getPlayers().get(0).drawRect(g2);
        players.getPlayers().get(1).drawRect(g2);

        g2.dispose();
    }
}