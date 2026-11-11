package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Player;
import main.Commons;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player();
    }

    private int getPlayerDx() {
        try {
            Field field = player.getClass().getSuperclass().getDeclaredField("dx");
            field.setAccessible(true);
            return field.getInt(player);
        } catch (Exception e) {
            throw new RuntimeException("Error al acceder mediante reflexión al campo dx", e);
        }
    }

    private void setPlayerDx(int dx) {
        try {
            Field field = player.getClass().getSuperclass().getDeclaredField("dx");
            field.setAccessible(true);
            field.setInt(player, dx);
        } catch (Exception e) {
            throw new RuntimeException("Error al modificar mediante reflexión el campo dx", e);
        }
    }

    private KeyEvent createKeyEvent(int id, int keyCode) {
        Component component = new JPanel();
        return new KeyEvent(component, id, System.currentTimeMillis(), 0, keyCode, KeyEvent.CHAR_UNDEFINED);
    }



    // Casos de Prueba: initPlayer()

    @Test
    public void testINIT1_PosicionInicial() {
        assertEquals(Commons.BOARD_WIDTH / 2, player.getX());
        assertEquals(Commons.GROUND - 10, player.getY());
    }



    // Casos de Prueba: act()

    @Test
    public void testBVT1_XMinimoDxNegativo() {
        player.setX(0);
        setPlayerDx(-2);
        player.act();
        assertEquals(0, player.getX());
    }

    @Test
    public void testBVT2_XIntermedioIzquierdaDxNegativo() {
        player.setX(1);
        setPlayerDx(-2);
        player.act();
        assertEquals(0, player.getX());
    }

    @Test
    public void testBVT3_XCentroDxNegativo() {
        player.setX(179);
        setPlayerDx(-2);
        player.act();
        assertEquals(177, player.getX());
    }

    @Test
    public void testBVT4_XIntermedioDerechaDxPositivo() {
        player.setX(357);
        setPlayerDx(2);
        player.act();
        assertEquals(Commons.BOARD_WIDTH, player.getX());
    }

    @Test
    public void testBVT5_XMaximoDxPositivo() {
        player.setX(Commons.BOARD_WIDTH);
        setPlayerDx(2);
        player.act();
        assertEquals(Commons.BOARD_WIDTH, player.getX());
    }




    // Casos de Prueba: keyPressed(KeyEvent e)

    @Test
    public void testKP1_TeclaIzquierda() {
        setPlayerDx(0);
        KeyEvent event = createKeyEvent(KeyEvent.KEY_PRESSED, KeyEvent.VK_LEFT);
        player.keyPressed(event);
        assertEquals(-2, getPlayerDx());
    }

    @Test
    public void testKP2_TeclaDerecha() {
        setPlayerDx(0);
        KeyEvent event = createKeyEvent(KeyEvent.KEY_PRESSED, KeyEvent.VK_RIGHT);
        player.keyPressed(event);
        assertEquals(2, getPlayerDx());
    }

    @Test
    public void testKP3_TeclaEspacio() {
        setPlayerDx(0);
        KeyEvent event = createKeyEvent(KeyEvent.KEY_PRESSED, KeyEvent.VK_SPACE);
        player.keyPressed(event);
        assertEquals(0, getPlayerDx());
    }




    // Casos de Prueba: keyReleased(KeyEvent e)

    @Test
    public void testKR1_TeclaIzquierdaLiberada() {
        setPlayerDx(-2);
        KeyEvent event = createKeyEvent(KeyEvent.KEY_RELEASED, KeyEvent.VK_LEFT);
        player.keyReleased(event);
        assertEquals(0, getPlayerDx());
    }

    @Test
    public void testKR2_TeclaDerechaLiberada() {
        setPlayerDx(2);
        KeyEvent event = createKeyEvent(KeyEvent.KEY_RELEASED, KeyEvent.VK_RIGHT);
        player.keyReleased(event);
        assertEquals(0, getPlayerDx());
    }

    @Test
    public void testKR3_TeclaEspacioLiberada() {
        setPlayerDx(2);
        KeyEvent event = createKeyEvent(KeyEvent.KEY_RELEASED, KeyEvent.VK_SPACE);
        player.keyReleased(event);
        assertEquals(2, getPlayerDx());
    }
}
