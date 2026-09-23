package main;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BoardTest {
    @Test
    public  void creacionBoardTest(){
        Board board = new Board();
        assertNotNull(board, "Board should be created succesfully");
    }

}
