package main;

import org.junit.jupiter.api.Test;
import space_invaders.sprites.Shot;

import static org.junit.jupiter.api.Assertions.*;

class ShotTest {

    private static final int H_SPACE = 6;
    private static final int V_SPACE = 1;

    @Test
    public void testSHOT1_Nominal() {
        Shot shot = new Shot(150, 280);
        assertEquals(150 + H_SPACE, shot.getX());
        assertEquals(280 - V_SPACE, shot.getY());
    }

    @Test
    public void testSHOT2_LimiteInferiorX() {
        Shot shot = new Shot(0, 280);
        assertEquals(0 + H_SPACE, shot.getX());
        assertEquals(280 - V_SPACE, shot.getY());
    }

    @Test
    public void testSHOT3_LimiteSuperiorX() {
        Shot shot = new Shot(Commons.BOARD_WIDTH, 280);
        assertEquals(Commons.BOARD_WIDTH + H_SPACE, shot.getX());
        assertEquals(280 - V_SPACE, shot.getY());
    }

    @Test
    public void testSHOT4_LimiteSuperiorY() {
        Shot shot = new Shot(150, Commons.GROUND);
        assertEquals(150 + H_SPACE, shot.getX());
        assertEquals(Commons.GROUND - V_SPACE, shot.getY());
    }
}
