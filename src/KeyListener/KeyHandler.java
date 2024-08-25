package KeyListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//Classe para "Ouvir" se o player está pressionando alguma tecla
public class KeyHandler implements KeyListener {
    //Player movements
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    //User Interface
    public boolean gamePaused = false;

    @Override
    public void keyTyped(KeyEvent e) {
        int code = e.getKeyCode();

        //Detecta se o usuário apertou ESC
        if (code == KeyEvent.VK_ESCAPE && gamePaused == false) 
        {gamePaused = true;} else {gamePaused = false;}

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //DETECTA SE WSAD ESTÁ PRESSIONADO
        if (code == KeyEvent.VK_W) {
            upPressed = true;           }
        if (code == KeyEvent.VK_S) {
            downPressed = true;         }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;         }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        
        //DETECTA SE WSAD FOI SOLTO
        if (code == KeyEvent.VK_W) {
            upPressed = false;          }
        if (code == KeyEvent.VK_S) {
            downPressed = false;        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;       }
    }
    
}
