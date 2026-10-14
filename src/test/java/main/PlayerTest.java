package main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;
import space_invaders.sprites.Shot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;
    private Component dummyComponent;
    @BeforeEach
    public void setUp(){
        player = new Player();
        dummyComponent = new JPanel();
    }
    private KeyEvent createKeyEvent(int id, int keyCode) {
        return new KeyEvent(dummyComponent, id, System.currentTimeMillis(), 0, keyCode, KeyEvent.CHAR_UNDEFINED);
    }
    private int getPlayerDx() throws Exception {
        Field dxField = player.getClass().getSuperclass().getDeclaredField("dx");
        dxField.setAccessible(true);
        return (int) dxField.get(player);
    }
    @Test
    public void testInitPlayer(){
        assertNotNull(player);
        assertEquals(player.getX(),Commons.BOARD_WIDTH/2);
        assertEquals(player.getY(),Commons.GROUND -10);
    }
    @Test
    public void testBVT1_ActMoveLeftNominal() {
        player.setX(150);
        player.keyPressed(createKeyEvent(KeyEvent.KEY_PRESSED, KeyEvent.VK_LEFT));
        player.act();
        assertEquals(148, player.getX());
    }
    @Test
    public void testBVT2_ActMoveRightNominal() {
        player.setX(150);
        player.keyPressed(createKeyEvent(KeyEvent.KEY_PRESSED, KeyEvent.VK_RIGHT));
        player.act();
        assertEquals(152, player.getX());
    }

    @Test
    public void testBVT3_ActReachLeftBoundary() {
        int leftLimit = Commons.BORDER_LEFT;
        player.setX(leftLimit + 2);
        player.keyPressed(createKeyEvent(KeyEvent.KEY_PRESSED, KeyEvent.VK_LEFT));
        player.act();
        assertEquals(leftLimit, player.getX());
    }

    @Test
    public void testBVT4_ActExceedLeftBoundary() {
        int leftLimit = Commons.BORDER_LEFT;
        player.setX(leftLimit);
        player.keyPressed(createKeyEvent(KeyEvent.KEY_PRESSED, KeyEvent.VK_LEFT));
        player.act();
        assertEquals(leftLimit, player.getX());
    }

    @Test
    public void testBVT5_ActReachRightBoundary() {
        int rightLimit = Commons.BOARD_WIDTH - Commons.BORDER_RIGHT;
        player.setX(rightLimit - 2);
        player.keyPressed(createKeyEvent(KeyEvent.KEY_PRESSED, KeyEvent.VK_RIGHT));
        player.act();
        assertEquals(rightLimit, player.getX());
    }

    @Test
    public void testBVT6_ActExceedRightBoundary() {
        int rightLimit = Commons.BOARD_WIDTH - Commons.BORDER_RIGHT;
        player.setX(rightLimit);
        player.keyPressed(createKeyEvent(KeyEvent.KEY_PRESSED, KeyEvent.VK_RIGHT));
        player.act();
        assertEquals(rightLimit, player.getX());
    }
    @Test
    public void testKR1_KeyReleasedLeft() throws Exception {
        player.keyPressed(createKeyEvent(KeyEvent.KEY_PRESSED, KeyEvent.VK_LEFT));
        player.keyReleased(createKeyEvent(KeyEvent.KEY_RELEASED, KeyEvent.VK_LEFT));
        assertEquals(0, getPlayerDx());
    }

    @Test
    public void testKR2_KeyReleasedRight() throws Exception {
        player.keyPressed(createKeyEvent(KeyEvent.KEY_PRESSED, KeyEvent.VK_RIGHT));
        player.keyReleased(createKeyEvent(KeyEvent.KEY_RELEASED, KeyEvent.VK_RIGHT));
        assertEquals(0, getPlayerDx());
    }

    @Test
    public void testKR3_KeyReleasedOtherKey() throws Exception {
        player.keyPressed(createKeyEvent(KeyEvent.KEY_PRESSED, KeyEvent.VK_LEFT));
        player.keyReleased(createKeyEvent(KeyEvent.KEY_RELEASED, KeyEvent.VK_SPACE));
        assertEquals(-2, getPlayerDx());
    }
}
