package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

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

    private void setPlayerDx(int dx) throws Exception {
        Field dxField = player.getClass().getSuperclass().getDeclaredField("dx");
        dxField.setAccessible(true);
        dxField.set(player, dx);
    }

    private int getPlayerDx() throws Exception {
        Field dxField = player.getClass().getSuperclass().getDeclaredField("dx");
        dxField.setAccessible(true);
        return (int) dxField.get(player);
    }

    @Test
    public void testInitPlayer(){
        assertNotNull(player);
        assertEquals(player.getX(), Commons.BOARD_WIDTH / 2);
        assertEquals(player.getY(), Commons.GROUND - 10);
    }

    @Test
    public void testBVT1_ActMoveLeftNominal() throws Exception {
        player.setX(150);
        setPlayerDx(-2);
        player.act();
        assertEquals(148, player.getX());
    }

    @Test
    public void testBVT2_ActMoveRightNominal() throws Exception {
        player.setX(150);
        setPlayerDx(2);
        player.act();
        assertEquals(152, player.getX());
    }

    @Test
    public void testBVT3_ActReachLeftBoundary() throws Exception {
        player.setX(2);
        setPlayerDx(-2);
        player.act();
        assertEquals(0, player.getX());
    }

    @Test
    public void testBVT4_ActExceedLeftBoundary() throws Exception {
        player.setX(Commons.BORDER_LEFT);
        setPlayerDx(-2);
        player.act();
        assertEquals(Commons.BORDER_LEFT, player.getX());
    }

    @Test
    public void testBVT5_ActReachRightBoundary() throws Exception {
        player.setX(Commons.BOARD_WIDTH - 2);
        setPlayerDx(2);
        player.act();
        assertEquals(Commons.BOARD_WIDTH, player.getX());
    }

    @Test
    public void testBVT6_ActExceedRightBoundary() throws Exception {
        player.setX(Commons.BOARD_WIDTH);
        setPlayerDx(2);
        player.act();
        assertEquals(Commons.BOARD_WIDTH, player.getX());
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