package main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import space_invaders.sprites.Alien;
import space_invaders.sprites.Player;
import space_invaders.sprites.Shot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlienTest {
    private Alien alien;
    @Test
    public void testALIEN1_InitAlien(){
        alien = new Alien(150,150);
        assertEquals(150,alien.getX());
        assertEquals(150,alien.getY());
    }
    @Test
    public void testALIEN2_InitAlien(){
        alien = new Alien(0,150);
        assertEquals(0,alien.getX());
        assertEquals(150,alien.getY());
    }
    @Test
    public void testALIEN3_InitAlien(){
        alien = new Alien(-1,150);
        assertEquals(0,alien.getX());
        assertEquals(150,alien.getY());
    }
    @Test
    public void testALIEN4_InitAlien(){
        alien = new Alien(358,150);
        assertEquals(358,alien.getX());
        assertEquals(150,alien.getY());
    }
    @Test
    public void testALIEN5_InitAlien(){
        alien = new Alien(359,150);
        assertEquals(Commons.BOARD_WIDTH,alien.getX());
        assertEquals(150,alien.getY());
    }
    @Test
    public void testALIEN6_InitAlien(){
        alien = new Alien(150,0);
        assertEquals(150,alien.getX());
        assertEquals(0,alien.getY());
    }
    @Test
    public void testALIEN7_InitAlien(){
        alien = new Alien(150,-1);
        assertEquals(150,alien.getX());
        assertEquals(0,alien.getY());
    }
    @Test
    public void testALIEN8_InitAlien(){
        alien = new Alien(150,350);
        assertEquals(150,alien.getX());
        assertEquals(350,alien.getY());
    }
    @Test
    public void testALIEN9_InitAlien(){
        alien = new Alien(150,351);
        assertEquals(150,alien.getX());
        assertEquals(Commons.BOARD_HEIGHT,alien.getY());
    }
    @Test
    public void testACTA1_Act(){
        alien = new Alien(100,150);
        alien.act(1);
        assertEquals(101,alien.getX());
    }
    @Test
    public void testACTA2_Act(){
        alien = new Alien(100,150);
        alien.act(-1);
        assertEquals(99,alien.getX());
    }
    @Test
    public void testACTA3_Act(){
        alien = new Alien(100,150);
        alien.act(0);
        assertEquals(100,alien.getX()       );
    }
}
