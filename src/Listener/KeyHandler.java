package Listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//Classe para "Ouvir" se o player está pressionando alguma tecla
public class KeyHandler implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            
        }
        if (code == KeyEvent.VK_S) {
            
        }
        if (code == KeyEvent.VK_A) {
            
        }
        if (code == KeyEvent.VK_D) {
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
