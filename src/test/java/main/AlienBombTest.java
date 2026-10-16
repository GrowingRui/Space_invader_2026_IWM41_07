package main;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;

public class AlienBombTest {
    private Alien alien;
    @BeforeEach
    public void setUp() {
        alien = new Alien(0, 0);
    }
    @Test
    public void testBOMB1_Nominal() {
        Alien.Bomb bomb = alien.new Bomb(150, 150);
        assertEquals(150, bomb.getX());
        assertEquals(150, bomb.getY());
        assertTrue(bomb.isDestroyed());
        assertNotNull(bomb.getImage());
    }
    @Test
    public void testBOMB2_MinValidX() {
        Alien.Bomb bomb = alien.new Bomb(0, 150);

        assertEquals(0, bomb.getX());
        assertEquals(150, bomb.getY());
        assertTrue(bomb.isDestroyed());
    }
    @Test
    public void testBOMB3_MinInvalidX_Robust() {
        Alien.Bomb bomb = alien.new Bomb(-1, 150);
        assertEquals(0, bomb.getX());
        assertEquals(150, bomb.getY());
        assertTrue(bomb.isDestroyed());
    }
    @Test
    public void testBOMB4_MaxValidX() {
        Alien.Bomb bomb = alien.new Bomb(Commons.BOARD_WIDTH, 150);
        assertEquals(Commons.BOARD_WIDTH - Commons.BORDER_RIGHT, bomb.getX());
        assertEquals(150, bomb.getY());
        assertTrue(bomb.isDestroyed());
    }
    @Test
    public void testBOMB5_MaxInvalidX_Robust() {
        Alien.Bomb bomb = alien.new Bomb(Commons.BOARD_WIDTH + 1, 150);
        assertEquals(Commons.BOARD_WIDTH, bomb.getX());
        assertEquals(150, bomb.getY());
        assertTrue(bomb.isDestroyed());
    }
    @Test
    public void testBOMB6_MinValidY() {
        Alien.Bomb bomb = alien.new Bomb(150, 0);
        assertEquals(150, bomb.getX());
        assertEquals(0, bomb.getY());
        assertTrue(bomb.isDestroyed());
    }
    @Test
    public void testBOMB7_MinInvalidY_Robust() {
        Alien.Bomb bomb = alien.new Bomb(150, -1);
        assertEquals(150, bomb.getX());
        assertEquals(0, bomb.getY());
        assertTrue(bomb.isDestroyed());
    }
    @Test
    public void testBOMB8_MaxValidY() {
        Alien.Bomb bomb = alien.new Bomb(150, Commons.BOARD_HEIGHT);
        assertEquals(150, bomb.getX());
        assertEquals(Commons.BOARD_HEIGHT, bomb.getY());
        assertTrue(bomb.isDestroyed());
    }
    @Test
    public void testBOMB9_MaxInvalidY_Robust() {
        Alien.Bomb bomb = alien.new Bomb(150, Commons.BOARD_HEIGHT + 1);
        assertEquals(Commons.BOARD_HEIGHT, bomb.getY());
        assertEquals(150, bomb.getX());
        assertTrue(bomb.isDestroyed());
    }
}
