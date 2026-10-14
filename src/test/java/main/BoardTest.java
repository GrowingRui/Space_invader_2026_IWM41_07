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
        board.getTimer().stop();
    }
    public void invokePrivateMethod(String method) throws Exception{
        Method update = board.getClass().getDeclaredMethod(method);
        update.setAccessible(true);
        update.invoke(board);
    }
    @Test
    public void testGameInit() {
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
    public void testUpdateGameWon() throws Exception {
        board.setDeaths(Commons.NUMBER_OF_ALIENS_TO_DESTROY);
        invokePrivateMethod("update");
        assertFalse(board.isInGame());
        assertEquals("Game won!", board.getMessage());
    }
    @Test
    public void testUpdateGameNotWon() throws Exception{
        board.setDeaths(23);
        invokePrivateMethod("update");
        assertTrue(board.isInGame());
        assertEquals("", board.getMessage());
    }
    @Test
    public void testUpdateShotsMoveUp() throws Exception {
        Shot shot = new Shot(150, 200);
        int initialY = shot.getY();
        board.setShot(shot);
        invokePrivateMethod("update_shots");
        assertEquals(initialY - Commons.SHOT_SPEED, board.getShot().getY());
    }
    @Test
    public void testUpdateShotsCollisionWithAlien() throws Exception {
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
    public void testUpdateAliensRightBorderBounce() throws Exception {
        Alien alien = board.getAliens().getFirst();
        alien.setX(Commons.BOARD_WIDTH - Commons.BORDER_RIGHT);
        int initialY = alien.getY();
        board.setDirection(1);
        invokePrivateMethod("update_aliens");
        assertEquals(-1, board.getDirection());
        assertEquals(initialY + Commons.GO_DOWN, alien.getY());
    }
    @Test
    public void testUpdateAliensInvasion() throws Exception {
        Alien alien = board.getAliens().getFirst();
        alien.setY(Commons.GROUND + Commons.ALIEN_HEIGHT);
        invokePrivateMethod("update_aliens");
        assertFalse(board.isInGame());
        assertEquals("Invasion!", board.getMessage());
    }
    @Test
    public void testUpdateBombHitsPlayer() throws Exception {
        Board board = new Board();
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
}
