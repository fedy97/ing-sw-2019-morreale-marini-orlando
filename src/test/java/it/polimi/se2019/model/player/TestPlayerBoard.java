package it.polimi.se2019.model.player;

import it.polimi.se2019.exceptions.InvalidCharacterException;
import it.polimi.se2019.model.enumeration.Character;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author Simone Orlando
 */
public class TestPlayerBoard {

    @Test
    public void testAddDamage() {
        PlayerBoard myBoard = new PlayerBoard();

        /*
            Testing the standard addition of damages
         */
        int damage = 6;
        try {
            myBoard.addDamage(Character.BANSHEE, damage);
        }
        catch (InvalidCharacterException e) {
            fail();
        }


        ArrayList<Character> line = myBoard.getDamageLine();
        assertEquals(line.size(),damage);
        for (Character c: line) {
            assertEquals(Character.BANSHEE, c);
        }

        /*
            Testing when value plus current damages is greater than 12
         */
        try {
            myBoard.addDamage(Character.BANSHEE, 10);
        }
        catch (InvalidCharacterException e) {
            fail();
        }

        line = myBoard.getDamageLine();
        assertEquals(12, line.size());

        /*
            Test of the impossibility of having a null character and a negative value
         */
        try {
            myBoard.addDamage(null, 2);
            fail();
        }
        catch (InvalidCharacterException e) {

        }
       

    }

    @Test
    public void testResetDamageLine() throws InvalidCharacterException{
        PlayerBoard myBoard = new PlayerBoard();

        myBoard.addDamage(Character.DISTRUCTOR, 5);

        myBoard.resetDamageLine();
        ArrayList<Character> line = myBoard.getDamageLine();
        assertEquals(0, line.size());
    }

    @Test
    public void testAddRevengeMarks() {
        PlayerBoard myBoard = new PlayerBoard();

        /*
            Testing the standard addition of marks
         */

        myBoard.addRevengeMark(Character.BANSHEE,2);


        ArrayList<Character> line = myBoard.getRevengeMarks();
        assertEquals(2, line.size());
        for (Character c: line) {
            assertEquals(Character.BANSHEE, c);
        }

        /*
            Testing when value plus current marks is greater than 3
         */
        myBoard.addRevengeMark(Character.BANSHEE,2);

        line = myBoard.getRevengeMarks();
        assertEquals(3, line.size());
        for (Character c: line) {
            assertEquals(Character.BANSHEE, c);
        }


    }

    @Test
    public void testRemoveRevengeMarks() {

        PlayerBoard myBoard = new PlayerBoard();

        /*
            Testing the mark removal
         */
        myBoard.addRevengeMark(Character.DOZER,2);
        try {
            myBoard.removeRevengeMarks(Character.DOZER);
        }
        catch (InvalidCharacterException e) {
            fail();
        }
        int counter = 0;
        ArrayList<Character> line = myBoard.getRevengeMarks();
        for (Character c: line) {
            if (c == Character.DOZER)
                counter++;
        }
        assertEquals(0, counter);

        /*
            Test of the impossibility of having a null character
         */
        try {
            myBoard.removeRevengeMarks(null);
            fail();
        }
        catch (InvalidCharacterException e) {

        }
    }
}
