import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler extends KeyAdapter {
    private boolean keyW, keyA, keyS, keyD, keyQ, keyE;

    public KeyHandler() {
        keyW = false;
        keyA = false;
        keyS = false;
        keyD = false;
        keyQ = false;
        keyE = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> keyW = true;
            case KeyEvent.VK_A -> keyA = true;
            case KeyEvent.VK_S -> keyS = true;
            case KeyEvent.VK_D -> keyD = true;
            case KeyEvent.VK_Q -> keyQ = true;
            case KeyEvent.VK_E -> keyE = true;
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
}
