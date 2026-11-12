package main;

import org.junit.jupiter.api.Test;
import space_invaders.sprites.Shot;
import main.Commons;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ShotTest {

    private static final int H_SPACE = 6;
    private static final int V_SPACE = 1;

    @Test
    public void testSHOT1_Nominal() {
        Shot shot = new Shot(150, 280);
        assertEquals(150 + H_SPACE, shot.getX());
        assertEquals(280 - V_SPACE, shot.getY());
    }

    @Test
    public void testSHOT2_XMin() {
        Shot shot = new Shot(0, 280);
        assertEquals(0 + H_SPACE, shot.getX());
        assertEquals(280 - V_SPACE, shot.getY());
    }

    @Test
    public void testSHOT3_XMinMas1() {
        Shot shot = new Shot(1, 280);
        assertEquals(1 + H_SPACE, shot.getX());
        assertEquals(280 - V_SPACE, shot.getY());
    }

    @Test
    public void testSHOT4_XMaxMenos1() {
        Shot shot = new Shot(Commons.BOARD_WIDTH - 1, 280);
        assertEquals((Commons.BOARD_WIDTH - 1) + H_SPACE, shot.getX());
        assertEquals(280 - V_SPACE, shot.getY());
    }

    @Test
    public void testSHOT5_XMax() {
        Shot shot = new Shot(Commons.BOARD_WIDTH, 280);
        assertEquals(Commons.BOARD_WIDTH + H_SPACE, shot.getX());
        assertEquals(280 - V_SPACE, shot.getY());
    }

    @Test
    public void testSHOT6_YMin() {
        Shot shot = new Shot(150, 0);
        assertEquals(150 + H_SPACE, shot.getX());
        assertEquals(0 - V_SPACE, shot.getY());
    }

    @Test
    public void testSHOT7_YMinMas1() {
        Shot shot = new Shot(150, 1);
        assertEquals(150 + H_SPACE, shot.getX());
        assertEquals(1 - V_SPACE, shot.getY());
    }

    @Test
    public void testSHOT8_YMaxMenos1() {
        Shot shot = new Shot(150, Commons.BOARD_HEIGHT - 1);
        assertEquals(150 + H_SPACE, shot.getX());
        assertEquals((Commons.BOARD_HEIGHT - 1) - V_SPACE, shot.getY());
    }

    @Test
    public void testSHOT9_YMax() {
        Shot shot = new Shot(150, Commons.BOARD_HEIGHT);
        assertEquals(150 + H_SPACE, shot.getX());
        assertEquals(Commons.BOARD_HEIGHT - V_SPACE, shot.getY());
    }
}
