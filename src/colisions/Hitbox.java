package colisions;

import players.Player;

public class Hitbox {
    public Hitbox() {
    }

    public void ScreenHitBox(Player player) {
        int playerHeight = player.getHeight();
        int playerWidth = player.getWidth();

        if (player.getY() >= 1080 - playerHeight) {
            player.setY(1080 - playerHeight); // Ground level
            player.setVelocityY(0);
            player.setJumping(false); // Reset jumping state when touching the ground
        }
        if (player.getX() > 1920 - playerWidth) {
            player.setX(1920 - playerWidth);
        }
        if (player.getX() < 0) {
            player.setX(5);
        }
        if (player.getY() < 0) {
            player.setY(5);
            player.setVelocityY(0); // Prevent falling out of the top of the screen
        }
    }

    public void checkPlayerCollision(Player player1, Player player2) {
        if (isColliding(player1, player2)) {
            System.out.println("Collision detected between " + player1.getName() + " and " + player2.getName());
            resolveCollision(player1, player2);
        }else {
            player1.setGravity(0.5);
            player2.setGravity(0.5);
        }
    }

    // Function to check if two players are colliding
    private boolean isColliding(Player player1, Player player2) {
        return player1.getX() < player2.getX() + player2.getWidth() &&
                player1.getX() + player1.getWidth() > player2.getX() &&
                player1.getY() < player2.getY() + player2.getHeight() &&
                player1.getY() + player1.getHeight() > player2.getY();
    }

    public void resolveCollision(Player player1, Player player2) {

        float overlapX = Math.min(
            player1.getX() + player1.getWidth() - player2.getX()-2, 
            player2.getX() + player2.getWidth() - player1.getX()-2
        );
    
        float overlapY = Math.min(
            player1.getY() + player1.getHeight() - player2.getY()-2, 
            player2.getY() + player2.getHeight() - player1.getY()-2
        );
    
        // Definir um limite para considerar quando a colisão no eixo Y é significativa
        float verticalThreshold = -10;  // Pequena tolerância para evitar movimento indesejado no eixo Y
    
        // Resolver a colisão
        if (overlapX < overlapY) {
            // Resolver no eixo X
            if (player1.getX() < player2.getX()) {
                player1.setX((int) (player1.getX() - overlapX)); 
                player2.setX((int) (player2.getX() + overlapX)); 
            } else {
                player1.setX((int) (player1.getX() + overlapX)); 
                player2.setX((int) (player2.getX() - overlapX)); 
            }
        } else if (overlapY > verticalThreshold) {
            // Resolver no eixo Y apenas se a colisão for maior que o limite
            if (player1.getY() < player2.getY()) {
                player1.setY((int) (player1.getY() - overlapY)); // Move player1 para cima
                player2.setY((int) (player2.getY() + overlapY)); // Move player2 para baixo
                player1.setGravity(0); 
                player2.setGravity(0.5);
                player1.setJumping(false); // Reset jumping state when touching the ground
            } else if (player1.getY() > player2.getY()) {
                player1.setY((int) (player1.getY() + overlapY)); // Move player1 para baixo
                player2.setY((int) (player2.getY() - overlapY)); // Move player2 para cima
                player2.setGravity(0);
                player1.setGravity(0.5);
                player2.setJumping(false); // Reset jumping state when touching the ground
            }
        }
    }
    
}


