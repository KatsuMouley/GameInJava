package players;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import colisions.Hitbox;
import keylistener.KeyHandler;

public class Player extends JPanel {
    // Game window
    private JFrame window = new JFrame();
    private Color cor;
    // Player dimensions
    private int playerWidth = 50, playerHeight = 50;

    // Movement parameters
    private int x = 500, y = 320;
    private int player_Speed = 7;
    private int player_boost = 5;
    private int move_X = 0;
    private ArrayList<Position> Positions = new ArrayList<>();

    // Physics parameters
    private double gravity = 0.5; // Gravity force
    private double jumpStrength = -18; // Initial jump velocity
    private double velocityY = 0; // Current vertical velocity
    private boolean isJumping = false; // Is the player currently jumping?

    // Speed and friction for dashing
    public int maxSpeed = 14;
    public int acceleration = 3;
    public int deceleration = 1;

    // Inner class to store positions
    public Hitbox hitbox = new Hitbox();

    // ---------------------------------------------------------------------------------------

    public Player(Color cor) {
        this.cor = cor;
    }

    public Player(Color cor, int x, int y) {
        this.x = x;
        this.y = y;
        this.cor = cor;
    }

    // ---------------------------------------------------------------------------------------
    public JFrame getWindow() {
        return window;
    }

    public void setWindow(JFrame window) {
        this.window = window;
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

    public int getWidth() {
        return playerWidth;
    }

    public void setWidth(int playerWidth) {
        this.playerWidth = playerWidth;
    }

    public int getHeight() {
        return playerHeight;
    }

    public void setHeight(int playerHeight) {
        this.playerHeight = playerHeight;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPlayer_Speed() {
        return player_Speed;
    }

    public void setPlayer_Speed(int player_Speed) {
        this.player_Speed = player_Speed;
    }

    public int getPlayer_boost() {
        return player_boost;
    }

    public void setPlayer_boost(int player_boost) {
        this.player_boost = player_boost;
    }

    public int getMove_X() {
        return move_X;
    }

    public void setMove_X(int move_X) {
        this.move_X = move_X;
    }

    public ArrayList<Position> getPositions() {
        return Positions;
    }

    public void setPositions(ArrayList<Position> positions) {
        Positions = positions;
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public double getJumpStrength() {
        return jumpStrength;
    }

    public void setJumpStrength(double jumpStrength) {
        this.jumpStrength = jumpStrength;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public int getDeceleration() {
        return deceleration;
    }

    public void setDeceleration(int deceleration) {
        this.deceleration = deceleration;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public void setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    // ---------------------------------------------------------------------------------------

    public void movement(int Type, KeyHandler keyH) {
        if (Type == 1) {
            arrowsMovement(keyH);
        } else if (Type == 2) {
            WASDMovent(keyH);
        } else if (Type == 3) {
            arrowsMovementDash(keyH);
        } else if (Type == 4) {
            WASDMovementDash(keyH);
        } else if (Type == 5) {
            WASDMoventSlide(keyH);
        } else if (Type == 6) {
            arrowsMoventSlide(keyH);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void arrowsMovement(KeyHandler keyH) {
        // Horizontal movement
        int move_X = 0;
        if (keyH.leftPressed) {
            move_X = -1;
        } else if (keyH.rightPressed) {
            move_X = 1;
        }
        x += move_X * player_Speed;

        // Jumping mechanics
        if (keyH.upPressed && !isJumping) {
            velocityY = jumpStrength;
            isJumping = true; // Player starts jumping
        }

        // Apply gravity
        velocityY += gravity;
        y += velocityY; // Corrected to modify y instead of x

        // Check for collisions and boundaries
    }

    public void WASDMovent(KeyHandler keyH) {
        // Horizontal movement
        int move_X = 0;
        if (keyH.leftAPressed) {
            move_X = -1;
        } else if (keyH.rightDPressed) {
            move_X = 1;
        }
        x += move_X * player_Speed;

        // Jumping mechanics
        if (keyH.upWPressed && !isJumping) {
            velocityY = jumpStrength;
            isJumping = true; // Player starts jumping
        }

        // Apply gravity
        velocityY += gravity;
        y += velocityY;

        // Check for collisions and boundaries
    }

    // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void arrowsMovementDash(KeyHandler keyH) {
        if (keyH.dash1) {
            player_Speed = 20;

            // Movement logic
            int move_X = 0;
            if (keyH.leftPressed) {
                move_X = -1;
            } else if (keyH.rightPressed) {
                move_X = 1;
            }
            x += move_X * player_Speed;

            // Jumping mechanics
            if (keyH.upPressed && !isJumping) {
                velocityY = jumpStrength;
                isJumping = true; // Player starts jumping
            }

            // Apply gravity
            velocityY += gravity;
            y += velocityY;

            // Store positions for dash effect
            setPositions();

            // Check for collisions and boundaries
        } else {
            player_Speed = 7;

            int move_X = 0;
            if (keyH.leftPressed) {
                move_X = -1;
            } else if (keyH.rightPressed) {
                move_X = 1;
            }
            x += move_X * player_Speed;

            // Jumping mechanics
            if (keyH.upPressed && !isJumping) {
                velocityY = jumpStrength;
                isJumping = true; // Player starts jumping
            }

            // Apply gravity
            velocityY += gravity;
            y += velocityY;

            // Check for collisions and boundaries
        }
    }

    public void WASDMovementDash(KeyHandler keyH) {
        if (keyH.dash1) {
            player_Speed = 20;

            // Movement logic
            int move_X = 0;
            if (keyH.leftAPressed) {
                move_X = -1;
            } else if (keyH.rightDPressed) {
                move_X = 1;
            }
            x += move_X * player_Speed;

            // Jumping mechanics
            if (keyH.upWPressed && !isJumping) {
                velocityY = jumpStrength;
                isJumping = true; // Player starts jumping
            }

            // Apply gravity
            velocityY += gravity;
            y += velocityY;

            // Store positions for dash effect
            setPositions();

            // Check for collisions and boundaries
        } else {
            player_Speed = 7;

            int move_X = 0;
            if (keyH.leftAPressed) {
                move_X = -1;
            } else if (keyH.rightDPressed) {
                move_X = 1;
            }
            x += move_X * player_Speed;

            // Jumping mechanics
            if (keyH.upWPressed && !isJumping) {
                velocityY = jumpStrength;
                isJumping = true; // Player starts jumping
            }

            // Apply gravity
            velocityY += gravity;
            y += velocityY;

            // Check for collisions and boundaries
        }
    }
    // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void WASDMoventSlide(KeyHandler keyH) {

        // Horizontal movement with friction
        if (keyH.leftAPressed) {
            if (move_X > -14) {
                move_X -= 6; // acelera para a esquerda
            }
        } else if (keyH.rightDPressed) {
            if (move_X < 14) {
                move_X += 6; // acelera para a direita
            }
        } else {
            // aplica fricção para desacelerar
            if (move_X > 0) {
                move_X -= 1; // desliza para a direita
            } else if (move_X < 0) {
                move_X += 1; // desliza para a esquerda
            }
        }

        x += move_X;

        // Jumping mechanics
        if (keyH.upWPressed && !isJumping) {
            velocityY = jumpStrength;
            isJumping = true; // Player starts jumping
        }

        // Apply gravity
        velocityY += gravity;
        y += velocityY;

        setPositions();
        // Check for collisions and boundaries
    }

    public void arrowsMoventSlide(KeyHandler keyH) {

        // Horizontal movement with friction
        if (keyH.leftPressed) {
            if (move_X > -14) {
                move_X -= 3; // acelera para a esquerda
            }
        } else if (keyH.rightPressed) {
            if (move_X < 14) {
                move_X += 3; // acelera para a direita
            }
        } else {
            // aplica fricção para desacelerar
            if (move_X > 0) {
                move_X -= 1; // desliza para a direita
            } else if (move_X < 0) {
                move_X += 1; // desliza para a esquerda
            }
        }

        x += move_X;

        // Jumping mechanics
        if (keyH.upPressed && !isJumping) {
            velocityY = jumpStrength;
            isJumping = true; // Player starts jumping
        }

        // Apply gravity
        velocityY += gravity;
        y += velocityY;

        setPositions();
        // Check for collisions and boundaries
    }

    // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void drawRect(Graphics2D g2) {
        g2.setColor(cor);
        g2.fillOval(x, y, playerWidth, playerHeight);
    }

    public void setPositions() {
        // Store the player's current position with initial lifetime
        Positions.add(new Position(x, y));

        // Limit the size of the positions list if needed
        if (Positions.size() > 60) {
            Positions.remove(0); // Remove the oldest position
        }
    }

    public void drawDash(Graphics2D g2, KeyHandler keyH) {
        if (keyH.dash1) {
            // Iterate over positions and draw blue squares
            for (int i = 0; i < Positions.size(); i++) {
                Position position = Positions.get(i);
                int r = cor.getRed();
                int g = cor.getGreen();
                int b = cor.getBlue();
                g2.setColor(new Color(r, g, b, 35));
                g2.fillOval(position.x, position.y, playerWidth, playerHeight);

                // Reduce the lifetime of the position
                position.reduceLifetime();

                // Remove the position if its lifetime has expired
                if (position.isExpired()) {
                    Positions.remove(i);
                    i--; // Adjust index after removal to prevent skipping elements
                }
            }
        }
    }
}