package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import main.Commons;
import space_invaders.sprites.Shot;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class BoardTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
        board.getTimer().stop();
    }

    private void invokeMethod(String nombre) {
        try {
            Method m = Board.class.getDeclaredMethod(nombre);
            m.setAccessible(true);
            m.invoke(board);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Alien setupSingleAlien(int x, int y, int dir) {
        Alien alien = new Alien(0, 0);
        alien.setX(x);
        alien.setY(y);
        List<Alien> lista = new ArrayList<>();
        lista.add(alien);
        board.setAliens(lista);
        board.setDirection(dir);
        return alien;
    }

    @Test
    public void testINITB1_EstadoInicial() {
        assertEquals(Commons.ALIEN_ROWS * Commons.ALIEN_COLUMNS, board.getAliens().size());
        for (int i = 0; i < Commons.ALIEN_ROWS; i++) {
            for (int j = 0; j < Commons.ALIEN_COLUMNS; j++) {
                Alien alien = board.getAliens().get(i * Commons.ALIEN_COLUMNS + j);
                assertEquals(Commons.ALIEN_INIT_X + j * Commons.ALIEN_SEPARATOR, alien.getX());
                assertEquals(Commons.ALIEN_INIT_Y + i * Commons.ALIEN_SEPARATOR, alien.getY());
            }
        }
        assertNotNull(board.getPlayer());
        assertNotNull(board.getShot());
        assertEquals(true, board.isInGame());
    }

    @Test
    public void testUPD1_MuertesZero() {
        board.setDeaths(0);
        invokeMethod("update");
        assertEquals(true, board.isInGame());
    }

    @Test
    public void testUPD2_MuertesUno() {
        board.setDeaths(1);
        invokeMethod("update");
        assertEquals(true, board.isInGame());
    }

    @Test
    public void testUPD3_MuertesDoce() {
        board.setDeaths(12);
        invokeMethod("update");
        assertEquals(true, board.isInGame());
    }

    @Test
    public void testUPD4_MuertesVeintitres() {
        board.setDeaths(23);
        invokeMethod("update");
        assertEquals(true, board.isInGame());
    }

    @Test
    public void testUPD5_MuertesVeinticuatroGana() {
        board.setDeaths(Commons.NUMBER_OF_ALIENS_TO_DESTROY);
        invokeMethod("update");
        assertEquals(false, board.isInGame());
        assertEquals("Game won!", board.getMessage());
    }

    @Test
    public void testUSHOT1_MovimientoNormal() {
        Shot shot = new Shot(0, 0);
        shot.setX(300);
        shot.setY(200);
        board.setShot(shot);
        invokeMethod("update_shots");
        assertEquals(200 - Commons.SHOT_SPEED, board.getShot().getY());
        assertEquals(true, board.getShot().isVisible());
    }

    @Test
    public void testUSHOT2_ColisionAlien() {
        Alien alien = board.getAliens().get(0);
        alien.setX(100);
        alien.setY(100);
        Shot shot = new Shot(0, 0);
        shot.setX(106);
        shot.setY(106);
        board.setShot(shot);
        board.setDeaths(0);
        invokeMethod("update_shots");
        assertEquals(true, alien.isDying());
        assertEquals(1, board.getDeaths());
    }

    @Test
    public void testUSHOT3_LimiteSuperior() {
        Shot shot = new Shot(0, 0);
        shot.setX(300);
        shot.setY(Commons.SHOT_SPEED - 1);
        board.setShot(shot);
        invokeMethod("update_shots");
        assertEquals(false, board.getShot().isVisible());
    }

    @Test
    public void testUSHOT4_DisparoNoVisible() {
        Shot shot = new Shot(0, 0);
        shot.setX(300);
        shot.setY(200);
        shot.die();
        board.setShot(shot);
        invokeMethod("update_shots");
        assertEquals(200, board.getShot().getY());
    }

    @Test
    public void testUALIEN1_Nominal() {
        Alien alien = setupSingleAlien(166, 151, 1);
        invokeMethod("update_aliens");
        assertEquals(167, alien.getX());
        assertEquals(151, alien.getY());
        assertEquals(1, board.getDirection());
        assertEquals(true, board.isInGame());
    }

    @Test
    public void testUALIEN2_XMinMenos1() {
        Alien alien = setupSingleAlien(Commons.BORDER_LEFT - 1, 151, -1);
        invokeMethod("update_aliens");
        assertEquals(1, board.getDirection());
        assertEquals(151 + Commons.GO_DOWN, alien.getY());
    }

    @Test
    public void testUALIEN3_XMin() {
        Alien alien = setupSingleAlien(Commons.BORDER_LEFT, 151, -1);
        invokeMethod("update_aliens");
        assertEquals(1, board.getDirection());
        assertEquals(151 + Commons.GO_DOWN, alien.getY());
    }

    @Test
    public void testUALIEN4_XMinMas1() {
        Alien alien = setupSingleAlien(Commons.BORDER_LEFT + 1, 151, -1);
        invokeMethod("update_aliens");
        assertEquals(5, alien.getX());
        assertEquals(151, alien.getY());
        assertEquals(-1, board.getDirection());
    }

    @Test
    public void testUALIEN5_XMaxMenos1() {
        Alien alien = setupSingleAlien(Commons.BOARD_WIDTH - Commons.BORDER_RIGHT - 1, 151, 1);
        invokeMethod("update_aliens");
        assertEquals(328, alien.getX());
        assertEquals(151, alien.getY());
        assertEquals(1, board.getDirection());
    }

    @Test
    public void testUALIEN6_XMax() {
        Alien alien = setupSingleAlien(Commons.BOARD_WIDTH - Commons.BORDER_RIGHT, 151, 1);
        invokeMethod("update_aliens");
        assertEquals(-1, board.getDirection());
        assertEquals(151 + Commons.GO_DOWN, alien.getY());
    }

    @Test
    public void testUALIEN7_XMaxMas1() {
        Alien alien = setupSingleAlien(Commons.BOARD_WIDTH - Commons.BORDER_RIGHT + 1, 151, 1);
        invokeMethod("update_aliens");
        assertEquals(-1, board.getDirection());
        assertEquals(151 + Commons.GO_DOWN, alien.getY());
    }

    @Test
    public void testUALIEN8_YMinMenos1() {
        Alien alien = setupSingleAlien(166, -1, 1);
        invokeMethod("update_aliens");
        assertEquals(167, alien.getX());
        assertEquals(-1, alien.getY());
        assertEquals(true, board.isInGame());
    }

    @Test
    public void testUALIEN9_YMin() {
        Alien alien = setupSingleAlien(166, 0, 1);
        invokeMethod("update_aliens");
        assertEquals(167, alien.getX());
        assertEquals(0, alien.getY());
        assertEquals(true, board.isInGame());
    }

    @Test
    public void testUALIEN10_YMinMas1() {
        Alien alien = setupSingleAlien(166, 1, 1);
        invokeMethod("update_aliens");
        assertEquals(167, alien.getX());
        assertEquals(1, alien.getY());
        assertEquals(true, board.isInGame());
    }

    @Test
    public void testUALIEN11_YMaxMenos1() {
        setupSingleAlien(166, Commons.GROUND + Commons.ALIEN_HEIGHT - 1, 1);
        invokeMethod("update_aliens");
        assertEquals(true, board.isInGame());
    }

    @Test
    public void testUALIEN12_YMax() {
        setupSingleAlien(166, Commons.GROUND + Commons.ALIEN_HEIGHT, 1);
        invokeMethod("update_aliens");
        assertEquals(true, board.isInGame());
    }

    @Test
    public void testUALIEN13_YMaxMas1() {
        setupSingleAlien(166, Commons.GROUND + Commons.ALIEN_HEIGHT + 1, 1);
        invokeMethod("update_aliens");
        assertEquals(false, board.isInGame());
        assertEquals("Invasion!", board.getMessage());
    }


    @Test
    public void testUBOMB1_BombaGeneradaAlAzar() {
        Alien alien = board.getAliens().get(0);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(true);
        int vueltas = 0;
        while (bomb.isDestroyed() && vueltas < 300) {
            invokeMethod("update_bomb");
            vueltas++;
        }
        assertEquals(false, bomb.isDestroyed());
        assertEquals(alien.getX(), bomb.getX());
    }

    @Test
    public void testUBOMB2_MovimientoNormal() {
        Alien alien = board.getAliens().get(0);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(false);
        bomb.setX(10);
        bomb.setY(150);
        invokeMethod("update_bomb");
        assertEquals(150 + Commons.BOMB_SPEED, bomb.getY());
    }

    @Test
    public void testUBOMB3_ColisionJugador() {
        Alien alien = board.getAliens().get(0);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(false);
        bomb.setX(board.getPlayer().getX() + 1);
        bomb.setY(board.getPlayer().getY() + 1);
        invokeMethod("update_bomb");
        assertEquals(true, board.getPlayer().isDying());
    }

    @Test
    public void testUBOMB4_ImpactoSuelo() {
        Alien alien = board.getAliens().get(0);
        Alien.Bomb bomb = alien.getBomb();
        bomb.setDestroyed(false);
        bomb.setX(10);
        bomb.setY(Commons.GROUND - Commons.BOMB_HEIGHT);
        invokeMethod("update_bomb");
        assertEquals(true, bomb.isDestroyed());
    }

}
