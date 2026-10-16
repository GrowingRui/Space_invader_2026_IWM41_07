package main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;
import space_invaders.sprites.Shot;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private Board board;
    @BeforeEach
    public void setUp(){
        board = new Board();
    }
    public void invokePrivateMethod(String method) throws Exception{
        Method update = board.getClass().getDeclaredMethod(method);
        update.setAccessible(true);
        update.invoke(board);
    }
    @Test
    public void testINITB1_GameInit() {
        List<Alien> aliens = board.getAliens();
        assertNotNull(aliens);
        assertEquals(Commons.ALIEN_ROWS * Commons.ALIEN_COLUMNS, aliens.size());
        assertEquals(Commons.ALIEN_INIT_X, aliens.getFirst().getX());
        assertEquals(Commons.ALIEN_INIT_Y, aliens.getFirst().getY());
        int index = 0;
        for (int i = 0; i < Commons.ALIEN_ROWS; i++) {
            for (int j = 0; j < Commons.ALIEN_COLUMNS; j++) {
                Alien alien = aliens.get(index);
                int expectedX = Commons.ALIEN_INIT_X + (j * Commons.ALIEN_SEPARATOR);
                int expectedY = Commons.ALIEN_INIT_Y + (i * Commons.ALIEN_SEPARATOR);
                assertEquals(expectedX, alien.getX());
                assertEquals(expectedY, alien.getY());
                index++;
            }
        }
        assertNotNull(board.getPlayer());
        assertNotNull(board.getShot());
        assertTrue(board.isInGame());
    }
    @Test
    public void testUPD2_Update() throws Exception {
        board.setDeaths(Commons.NUMBER_OF_ALIENS_TO_DESTROY);
        invokePrivateMethod("update");
        assertFalse(board.isInGame());
        assertEquals("Game won!", board.getMessage());
    }
    @Test
    public void testUPD1_Update() throws Exception{
        board.setDeaths(23);
        invokePrivateMethod("update");
        assertTrue(board.isInGame());
        assertEquals("", board.getMessage());
    }
    @Test
    public void testUSHOT1_Update_shots() throws Exception {
        Shot shot = new Shot(150, 200);
        int initialY = shot.getY();
        board.setShot(shot);
        invokePrivateMethod("update_shots");
        assertEquals(initialY - Commons.SHOT_SPEED, board.getShot().getY());
    }
    @Test
    public void testUSHOT2_Update_shots() throws Exception {
        Alien targetAlien = board.getAliens().getFirst();
        targetAlien.setX(100);
        targetAlien.setY(100);
        Shot shot = new Shot(100, 100);
        board.setShot(shot);
        board.setDeaths(0);
        invokePrivateMethod("update_shots");
        assertTrue(targetAlien.isDying());
        assertEquals(1, board.getDeaths());
    }
    @Test
    public void testUSHOT3_Update_shots() throws Exception {
        Shot shot = new Shot(150, 200);
        int initialY = shot.getY();
        board.setShot(shot);
        invokePrivateMethod("update_shots");
        assertEquals(initialY - Commons.SHOT_SPEED, board.getShot().getY());
    }
    @Test
    public void testUALIEN1_Update_aliens() throws Exception {
        Alien alien = board.getAliens().getLast();
        alien.setX(Commons.BOARD_WIDTH);
        int initialY = alien.getY();
        board.setDirection(1);
        invokePrivateMethod("update_aliens");
        assertEquals(-1, board.getDirection());
        assertEquals(initialY + Commons.GO_DOWN, alien.getY());
    }
    @Test
    public void testUALIEN2_Update_aliens() throws Exception {
        Alien alien = board.getAliens().getFirst();
        alien.setX(Commons.BOARD_WIDTH);
        int initialY = alien.getY();
        board.setDirection(-1);
        invokePrivateMethod("update_aliens");
        assertEquals(1, board.getDirection());
        assertEquals(initialY + Commons.GO_DOWN, alien.getY());
    }
    @Test
    public void testUALIEN3_Update_aliens() throws Exception {
        Alien alien = board.getAliens().getFirst();
        alien.setY(Commons.GROUND - 1);
        invokePrivateMethod("update_aliens");
        assertTrue(board.isInGame());
        assertEquals("", board.getMessage());
    }
    @Test
    public void testUALIEN4_Update_aliens() throws Exception {
        Alien alien = board.getAliens().getFirst();
        alien.setY(Commons.GROUND + Commons.ALIEN_HEIGHT);
        invokePrivateMethod("update_aliens");
        assertFalse(board.isInGame());
        assertEquals("Invasion!", board.getMessage());
    }
    @Test
    public void testUBOMB1_UpdateBombRandomGeneration() throws Exception {
        Alien alien = board.getAliens().getFirst();
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(true);
        for (int i = 0; i < 150 && !bomb.isDestroyed(); i++) {
            invokePrivateMethod("update_bomb");
        }
        assertFalse(bomb.isDestroyed());
        assertEquals(alien.getX(), bomb.getX());
        assertTrue(bomb.getY() == alien.getY() && bomb.getX() == alien.getX());
    }
    @Test
    public void testUBOMB2_Update_bomb() throws Exception {
        Alien.Bomb bomb = board.getAliens().getFirst().getBomb();
        bomb.setX(150);
        bomb.setY(150);
        invokePrivateMethod("update_bomb");
        assertEquals(150 + Commons.BOMB_SPEED,bomb.getY());
    }
    @Test
    public void testUBOMB3_Update_bomb() throws Exception {

        Player player = board.getPlayer();
        player.setX(150);
        player.setY(280);
        Alien.Bomb bomb = board.getAliens().getFirst().getBomb();
        bomb.setDestroyed(false);
        bomb.setX(150);
        bomb.setY(280);
        invokePrivateMethod("update_bomb");
        assertTrue(player.isDying());
    }
    @Test
    public void testUBOMB4_Update_bomb() throws Exception {
        Alien.Bomb bomb = board.getAliens().getFirst().getBomb();
        bomb.setDestroyed(false);
        bomb.setX(150);
        bomb.setY(Commons.GROUND);
        invokePrivateMethod("update_bomb");
        assertTrue(bomb.isDestroyed());
    }
}
