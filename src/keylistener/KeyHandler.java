package keylistener;
 
 import java.awt.event.KeyEvent;
 import java.awt.event.KeyListener;
//Classe para "Ouvir" se o player está pressionando alguma tecla
public class KeyHandler implements KeyListener {
    //Player movements
    public boolean upWPressed, downSPressed, leftAPressed, rightDPressed, upPressed, downPressed, leftPressed, rightPressed;
    public boolean dash1;
    //User Interface
    public boolean gamePaused = false;
    public boolean gridVisible = false;

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
        
        if (code == KeyEvent.VK_R && dash1 == false) 
        {dash1 = true;} 
        if (code == KeyEvent.VK_SPACE && dash1 == true) 
        {dash1 = false;} 


        //ativa a grade
        if (code == KeyEvent.VK_G) {
            gridVisible = !gridVisible; // Alterna o estado da grade
        }
        
        //DETECTA SE WSAD ESTÁ PRESSIONADO
        if (code == KeyEvent.VK_W) {
            upWPressed = true;           }
        if (code == KeyEvent.VK_S) {
            downSPressed = true;         }
        if (code == KeyEvent.VK_A) {
            leftAPressed = true;         }
        if (code == KeyEvent.VK_D) {
            rightDPressed = true;        }

            
        //DETECTA SE Up Down Right Left ESTÁ PRESSIONADO
        if (code == KeyEvent.VK_UP) {
            upPressed = true;           }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = true;         }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = true;         }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = true;        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        
        //DETECTA SE WSAD FOI SOLTO
        if (code == KeyEvent.VK_W) {
            upWPressed = false;           }
        if (code == KeyEvent.VK_S) {
            downSPressed = false;         }
        if (code == KeyEvent.VK_A) {
            leftAPressed = false;         }
        if (code == KeyEvent.VK_D) {
            rightDPressed = false;        }

            
        //DETECTA SE Up Down Right Left FOI SOLTO
        if (code == KeyEvent.VK_UP) {
            upPressed = false;           }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = false;         }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = false;         }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = false;        }
    }
    
}