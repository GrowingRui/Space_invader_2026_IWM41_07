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
        assertEquals(Commons.BOARD_WIDTH / 2, player.getX());
        assertEquals(Commons.GROUND - 10, player.getY());
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
    public void testBVT3_ActNotMove() throws Exception {
        player.setX(150);
        setPlayerDx(0);
        player.act();
        assertEquals(150, player.getX());
    }

    @Test
    public void testBVT4_ActReachLeftBoundary() throws Exception {
        player.setX(0);
        setPlayerDx(-2);
        player.act();
        assertEquals(0, player.getX());
    }

    @Test
    public void testBVT5_ActNearLeftBoundary() throws Exception {
        player.setX(1);
        setPlayerDx(-2);
        player.act();
        assertEquals(0, player.getX());
    }

    @Test
    public void testBVT6_ActNearRightBoundary() throws Exception {
        player.setX(357);
        setPlayerDx(2);
        player.act();
        assertEquals(Commons.BOARD_WIDTH, player.getX());
    }

    @Test
    public void testBVT7_ActReachRightBoundary() throws Exception {
        player.setX(Commons.BOARD_WIDTH);
        setPlayerDx(2);
        player.act();
        assertEquals(Commons.BOARD_WIDTH, player.getX());
    }

    @Test
    public void testKR1_KeyReleasedLeft() throws Exception {
        setPlayerDx(-2);
        player.keyReleased(createKeyEvent(KeyEvent.KEY_RELEASED, KeyEvent.VK_LEFT));
        assertEquals(0, getPlayerDx());
    }

    @Test
    public void testKR2_KeyReleasedRight() throws Exception {
        setPlayerDx(2);
        player.keyReleased(createKeyEvent(KeyEvent.KEY_RELEASED, KeyEvent.VK_RIGHT));
        assertEquals(0, getPlayerDx());
    }

    @Test
    public void testKR3_KeyReleasedOtherKey() throws Exception {
        setPlayerDx(2);
        player.keyReleased(createKeyEvent(KeyEvent.KEY_RELEASED, KeyEvent.VK_SPACE));
        assertEquals(2, getPlayerDx());
    }

    @Test
    public void testKP1_KeyPressedLeft() throws Exception {
        setPlayerDx(0);
        player.keyPressed(createKeyEvent(KeyEvent.KEY_PRESSED, KeyEvent.VK_LEFT));
        assertEquals(-2, getPlayerDx());
    }

    @Test
    public void testKP2_KeyPressedRight() throws Exception {
        setPlayerDx(0);
        player.keyPressed(createKeyEvent(KeyEvent.KEY_PRESSED, KeyEvent.VK_RIGHT));
        assertEquals(2, getPlayerDx());
    }

    @Test
    public void testKP3_KeyPressedOtherKey() throws Exception {
        setPlayerDx(0);
        player.keyPressed(createKeyEvent(KeyEvent.KEY_PRESSED, KeyEvent.VK_SPACE));
        assertEquals(0, getPlayerDx());
    }

}