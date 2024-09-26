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
    private int PYR3 = (int) (baseScreenHeight * 0.45577); // Posição Y inicial do Retângulo 3
    private int speed = 2; // Velocidade do movimento do Retângulo 3

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
        double drawInterval = 1000000000 / FPS; // INTERVALO DE TEMPO DE FRAMES
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

        // Atualizar a posição do Retângulo 3
        PYR3 += speed;
        if (PYR3 > baseScreenHeight - (int) (baseScreenHeight * 0.047534) || PYR3 < 0) {
            speed = -speed; // Inverte a direção
        }
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

        // Desenhar o fundo
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, baseScreenWidth, baseScreenHeight); // Desenha o fundo preto

        // Desenhar a grade se estiver visível
        if (keyH.gridVisible) { // Verifica se a grade deve ser desenhada
            g2.setColor(Color.GRAY); // Cor da grade
            int gridSize = 50; // Tamanho de cada célula da grade

            for (int x = 0; x < baseScreenWidth; x += gridSize) {
                g2.drawLine(x, 0, x, baseScreenHeight); // Linhas verticais
            }

            for (int y = 0; y < baseScreenHeight; y += gridSize) {
                g2.drawLine(0, y, baseScreenWidth, y); // Linhas horizontais
            }
        }

        // desenha o cenario
        // sumario
        // R = retangulo
        // Q = quadrado

        // retangulo 1
        double squarePercent = 0.1; // 10% do tamanho da tela
        int baseR1 = (int) (baseScreenWidth * 0.365);
        int alturaR1 = (int) (baseScreenHeight * 0.047534);
        int PXR1 = (int) (baseScreenWidth * 0.55);
        int PYR1 = (int) (baseScreenHeight * 0.75577);

        g2.setColor(Color.white);
        g2.fillRect(PXR1, PYR1, baseR1, alturaR1);

        // quadrado 2
        int baseQ2 = (int) (baseScreenWidth * 0.115);
        int altura2 = (int) (baseScreenHeight * 0.047534);
        int PX2 = (int) (baseScreenWidth * 0.01);
        int PYQ2 = (int) (baseScreenHeight * 0.75577);

        g2.setColor(Color.blue);
        g2.fillRect(PX2, PYQ2, baseQ2, altura2);

        // retangulo 3 (agora se move)
        int baseR3 = (int) (baseScreenWidth * 0.365);
        int alturaR3 = (int) (baseScreenHeight * 0.047534);
        int PXR3 = (int) (baseScreenWidth * 0.0800);

        g2.setColor(Color.white);
        g2.fillRect(PXR3, PYR3, baseR3, alturaR3); // Usa a nova posição PYR3

        // quadrado 4 (parado)
        int baseQ4 = (int) (baseScreenWidth * 0.115);
        int altura4 = (int) (baseScreenHeight * 0.047534);
        int PX4 = (int) (baseScreenWidth * 0.799);
        int PYQ4 = (int) (baseScreenHeight * 0.45577); // Valor fixo para quadrado parado

        g2.setColor(Color.blue);
        g2.fillRect(PX4, PYQ4, baseQ4, altura4); // Quadrado 4 permanece parado

        // Desenhar jogadores ajustados à escala e centralizados
        players.getPlayers().get(0).drawDash(g2, keyH);
        players.getPlayers().get(1).drawDash(g2, keyH);
        players.getPlayers().get(0).drawRect(g2);
        players.getPlayers().get(1).drawRect(g2);

        g2.dispose();
    }
}
