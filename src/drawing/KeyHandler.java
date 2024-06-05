package drawing;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {
    private boolean keyW, keyA, keyS, keyD, keyQ, keyE;
    private boolean upArrow, downArrow, leftArrow, rightArrow;

    public KeyHandler() {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> keyW = true;
            case KeyEvent.VK_A -> keyA = true;
            case KeyEvent.VK_S -> keyS = true;
            case KeyEvent.VK_D -> keyD = true;
            case KeyEvent.VK_Q -> keyQ = true;
            case KeyEvent.VK_E -> keyE = true;
            case KeyEvent.VK_UP -> upArrow = true;
            case KeyEvent.VK_DOWN -> downArrow = true;
            case KeyEvent.VK_LEFT -> leftArrow = true;
            case KeyEvent.VK_RIGHT -> rightArrow = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> keyW = false;
            case KeyEvent.VK_A -> keyA = false;
            case KeyEvent.VK_S -> keyS = false;
            case KeyEvent.VK_D -> keyD = false;
            case KeyEvent.VK_Q -> keyQ = false;
            case KeyEvent.VK_E -> keyE = false;
            case KeyEvent.VK_UP -> upArrow = false;
            case KeyEvent.VK_DOWN -> downArrow = false;
            case KeyEvent.VK_LEFT -> leftArrow = false;
            case KeyEvent.VK_RIGHT -> rightArrow = false;
        }
    }

    public boolean keyWPressed() {
        return keyW;
    }

    public boolean keyAPressed() {
        return keyA;
    }

    public boolean keySPressed() {
        return keyS;
    }

    public boolean keyDPressed() {
        return keyD;
    }

    public boolean keyQPressed() {
        return keyQ;
    }

    public boolean keyEPressed() {
        return keyE;
    }

    public boolean upArrowPressed() {
        return upArrow;
    }

    public boolean downArrowPressed() {
        return downArrow;
    }

    public boolean leftArrowPressed() {
        return leftArrow;
    }

    public boolean rightArrowPressed() {
        return rightArrow;
    }
}
