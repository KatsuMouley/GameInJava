package players;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import keylistener.KeyHandler;

public class Player extends JPanel {
    // Game window
    JFrame window = new JFrame();
    Color cor;
    // Player dimensions
    int playerWidth = 50, playerHeight = 50;

    // Movement parameters
    int player1_X = 500, player1_Y = 575;
    int player_Speed = 7;
    int player_boost = 5;

    // Physics parameters
    double gravity = 0.5; // Gravity force
    double jumpStrength = -20; // Initial jump velocity
    double velocityY = 0; // Current vertical velocity
    boolean isJumping = false; // Is the player currently jumping?

    public Player(Color cor) {
        this.cor = cor;
    }

    public void Hitbox() {
        if (player1_Y >= 675 - playerHeight) {
            player1_Y = 675 - playerHeight; // Ground level
            velocityY = 0;
            isJumping = false; // Reset jumping state when touching the ground
        }
        if (player1_X > 1200 - playerWidth) {
            player1_X = 1200 - playerWidth;
        }
        if (player1_X < 0) {
            player1_X = 0;
        }
        if (player1_Y < 0) {
            player1_Y = 0;
            velocityY = 0; // Prevent falling out of the top of the screen
        }
    }

    public void movement(int Type, KeyHandler keyH) {
        if (Type == 1) {
            arrowsMovement(keyH);
        } else if (Type == 2) {
            WASDMovent(keyH);
        }
    }

    public void arrowsMovement(KeyHandler keyH) {
        // Horizontal movement
        int move_X = 0;
        if (keyH.leftPressed) {
            move_X = -1;
        } else if (keyH.rightPressed) {
            move_X = 1;
        }
        player1_X += move_X * player_Speed;

        // Jumping mechanics
        if (keyH.upPressed && !isJumping) {
            velocityY = jumpStrength;
            isJumping = true; // Player starts jumping
        }

        // Apply gravity
        velocityY += gravity;
        player1_Y += velocityY;

        // Check for collisions and boundaries
        Hitbox();
    }

    public void WASDMovent(KeyHandler keyH) {
        // Horizontal movement
        int move_X = 0;
        if (keyH.leftAPressed) {
            move_X = -1;
        } else if (keyH.rightDPressed) {
            move_X = 1;
        }
        player1_X += move_X * player_Speed;

        // Jumping mechanics
        if (keyH.upWPressed && !isJumping) {
            velocityY = jumpStrength;
            isJumping = true; // Player starts jumping
        }

        // Apply gravity
        velocityY += gravity;
        player1_Y += velocityY;

        // Check for collisions and boundaries
        Hitbox();
    }

    public void drawRect(Graphics2D g2) {
        g2.setColor(cor);
        g2.fillRect(player1_X, player1_Y, playerWidth, playerHeight);
    }
}
