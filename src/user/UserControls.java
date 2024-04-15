package user;

import main.SimulationMain;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserControls implements KeyListener {

    public final SimulationMain simMain;

    public UserControls(SimulationMain simMain) {
        this.simMain = simMain;
    }

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shiftPressed, spacePressed;


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if(code == KeyEvent.VK_SHIFT) {
            shiftPressed = true;
            simMain.user.speed = 4;
        }
        if(code == KeyEvent.VK_F1) {
            simMain.debug = !simMain.debug;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if(code == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
            simMain.user.speed = 2;
        }
    }


}
