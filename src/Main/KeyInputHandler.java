package Main;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyInputHandler implements KeyListener
{
    public boolean upPressed, downPressed, leftPressed, rightPressed,
            arrowdownPressed, arrowupPressed, enterPressed, spacePressed;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_DOWN)
        {
            arrowdownPressed = true;
        }
        if (code == KeyEvent.VK_UP)
        {
            arrowupPressed = true;
        }
        if (code == KeyEvent.VK_ENTER)
        {
            enterPressed = true;
        }
        if (code == KeyEvent.VK_SPACE)
        {
            spacePressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W)
            upPressed = false;
        if(code == KeyEvent.VK_S)
            downPressed = false;
        if(code == KeyEvent.VK_A)
            leftPressed = false;
        if(code == KeyEvent.VK_D)
            rightPressed = false;
        if (code == KeyEvent.VK_DOWN)
        {
            arrowdownPressed = false;
        }
        if (code == KeyEvent.VK_UP)
        {
            arrowupPressed = false;
        }
        if (code == KeyEvent.VK_ENTER)
        {
            enterPressed = false;
        }
        if (code == KeyEvent.VK_SPACE)
        {
            spacePressed = false;
        }
    }
}
