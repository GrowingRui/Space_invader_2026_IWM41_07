package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import main.Commons;
import static org.junit.jupiter.api.Assertions.assertEquals;


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
    }

    @Test
    public void testBOMB2_XMinMenos1() {
        Alien.Bomb bomb = alien.new Bomb(-1, 150);
        assertEquals(0, bomb.getX());
        assertEquals(150, bomb.getY());
    }

    @Test
    public void testBOMB3_XMin() {
        Alien.Bomb bomb = alien.new Bomb(0, 150);
        assertEquals(0, bomb.getX());
        assertEquals(150, bomb.getY());
    }

    @Test
    public void testBOMB4_XMinMas1() {
        Alien.Bomb bomb = alien.new Bomb(1, 150);
        assertEquals(1, bomb.getX());
        assertEquals(150, bomb.getY());
    }

    @Test
    public void testBOMB5_XMaxMenos1() {
        Alien.Bomb bomb = alien.new Bomb(Commons.BOARD_WIDTH - 1, 150);
        assertEquals(Commons.BOARD_WIDTH - 1, bomb.getX());
        assertEquals(150, bomb.getY());
    }

    @Test
    public void testBOMB6_XMax() {
        Alien.Bomb bomb = alien.new Bomb(Commons.BOARD_WIDTH, 150);
        assertEquals(Commons.BOARD_WIDTH, bomb.getX());
        assertEquals(150, bomb.getY());
    }

    @Test
    public void testBOMB7_XMaxMas1() {
        Alien.Bomb bomb = alien.new Bomb(Commons.BOARD_WIDTH + 1, 150);
        assertEquals(Commons.BOARD_WIDTH, bomb.getX());
        assertEquals(150, bomb.getY());
    }

    @Test
    public void testBOMB8_YMinMenos1() {
        Alien.Bomb bomb = alien.new Bomb(150, -1);
        assertEquals(150, bomb.getX());
        assertEquals(0, bomb.getY());
    }

    @Test
    public void testBOMB9_YMin() {
        Alien.Bomb bomb = alien.new Bomb(150, 0);
        assertEquals(150, bomb.getX());
        assertEquals(0, bomb.getY());
    }

    @Test
    public void testBOMB10_YMinMas1() {
        Alien.Bomb bomb = alien.new Bomb(150, 1);
        assertEquals(150, bomb.getX());
        assertEquals(1, bomb.getY());
    }

    @Test
    public void testBOMB11_YMaxMenos1() {
        Alien.Bomb bomb = alien.new Bomb(150, Commons.BOARD_HEIGHT - 1);
        assertEquals(150, bomb.getX());
        assertEquals(Commons.BOARD_HEIGHT - 1, bomb.getY());
    }

    @Test
    public void testBOMB12_YMax() {
        Alien.Bomb bomb = alien.new Bomb(150, Commons.BOARD_HEIGHT);
        assertEquals(150, bomb.getX());
        assertEquals(Commons.BOARD_HEIGHT, bomb.getY());
    }

    @Test
    public void testBOMB13_YMaxMas1() {
        Alien.Bomb bomb = alien.new Bomb(150, Commons.BOARD_HEIGHT + 1);
        assertEquals(150, bomb.getX());
        assertEquals(Commons.BOARD_HEIGHT, bomb.getY());
    }
}
