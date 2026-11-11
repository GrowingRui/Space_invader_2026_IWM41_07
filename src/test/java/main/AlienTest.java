package main;

import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import main.Commons;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlienTest {

    @Test
    public void testALIEN1_Nominal() {
        Alien a = new Alien(150, 150);
        assertEquals(150, a.getX());
        assertEquals(150, a.getY());
    }

    @Test
    public void testALIEN2_XMinMenos1() {
        Alien a = new Alien(-1, 150);
        assertEquals(0, a.getX());
        assertEquals(150, a.getY());
    }

    @Test
    public void testALIEN3_XMin() {
        Alien a = new Alien(0, 150);
        assertEquals(0, a.getX());
        assertEquals(150, a.getY());
    }

    @Test
    public void testALIEN4_XMinMas1() {
        Alien a = new Alien(1, 150);
        assertEquals(1, a.getX());
        assertEquals(150, a.getY());
    }

    @Test
    public void testALIEN5_XMaxMenos1() {
        Alien a = new Alien(Commons.BOARD_WIDTH - 1, 150);
        assertEquals(Commons.BOARD_WIDTH - 1, a.getX());
        assertEquals(150, a.getY());
    }

    @Test
    public void testALIEN6_XMax() {
        Alien a = new Alien(Commons.BOARD_WIDTH, 150);
        assertEquals(Commons.BOARD_WIDTH, a.getX());
        assertEquals(150, a.getY());
    }

    @Test
    public void testALIEN7_XMaxMas1() {
        Alien a = new Alien(Commons.BOARD_WIDTH + 1, 150);
        assertEquals(Commons.BOARD_WIDTH, a.getX());
        assertEquals(150, a.getY());
    }

    @Test
    public void testALIEN8_YMinMenos1() {
        Alien a = new Alien(150, -1);
        assertEquals(150, a.getX());
        assertEquals(0, a.getY());
    }

    @Test
    public void testALIEN9_YMin() {
        Alien a = new Alien(150, 0);
        assertEquals(150, a.getX());
        assertEquals(0, a.getY());
    }

    @Test
    public void testALIEN10_YMinMas1() {
        Alien a = new Alien(150, 1);
        assertEquals(150, a.getX());
        assertEquals(1, a.getY());
    }

    @Test
    public void testALIEN11_YMaxMenos1() {
        Alien a = new Alien(150, Commons.BOARD_HEIGHT - 1);
        assertEquals(150, a.getX());
        assertEquals(Commons.BOARD_HEIGHT - 1, a.getY());
    }

    @Test
    public void testALIEN12_YMax() {
        Alien a = new Alien(150, Commons.BOARD_HEIGHT);
        assertEquals(150, a.getX());
        assertEquals(Commons.BOARD_HEIGHT, a.getY());
    }

    @Test
    public void testALIEN13_YMaxMas1() {
        Alien a = new Alien(150, Commons.BOARD_HEIGHT + 1);
        assertEquals(150, a.getX());
        assertEquals(Commons.BOARD_HEIGHT, a.getY());
    }


    @Test
    public void testACTA1_DireccionNegativa() {
        Alien a = new Alien(150, 100);
        a.act(-1);
        assertEquals(149, a.getX());
        assertEquals(100, a.getY());
    }

    @Test
    public void testACTA2_DireccionCero() {
        Alien a = new Alien(150, 100);
        a.act(0);
        assertEquals(150, a.getX());
        assertEquals(100, a.getY());
    }

    @Test
    public void testACTA3_DireccionPositiva() {
        Alien a = new Alien(150, 100);
        a.act(1);
        assertEquals(151, a.getX());
        assertEquals(100, a.getY());
    }
}
