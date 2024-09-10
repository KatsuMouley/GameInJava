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
        }
    }

    // Function to check if two players are colliding
    private boolean isColliding(Player player1, Player player2) {
        return player1.getX() < player2.getX() + player2.getWidth() &&
                player1.getX() + player1.getWidth() > player2.getX() &&
                player1.getY() < player2.getY() + player2.getHeight() &&
                player1.getY() + player1.getHeight() > player2.getY();
    }

    private void resolveCollision(Player player1, Player player2) {
        // // Calculate the overlap on the x and y axes
        // int overlapX = (player1.getX() + player1.getWidth()) - player2.getX();
        // int overlapY = (player1.getY() + player1.getHeight()) - player2.getY();

        // // Check which overlap is smaller and resolve accordingly
        // if (overlapX < overlapY) {
        // // Resolve collision along the x-axis
        // if (player1.getX() < player2.getX()) {
        // player1.setX(player1.getX() - overlapX); // Push player1 left
        // } else {
        // player1.setX(player1.getX() + overlapX); // Push player1 right
        // }
        // } else {
        // // Resolve collision along the y-axis
        // if (player1.getY() < player2.getY()) {
        // player1.setY(player1.getY() - overlapY); // Push player1 up
        // } else {
        // player1.setY(player1.getY() + overlapY); // Push player1 down
        // }
        // }
        // ESTA COLISÃO NÃO ESTÁ FUNCIONANDO
    }

}
