package it.polimi.se2019.model.player;

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
        catch (IllegalArgumentException e) {
            fail();
        }
        catch (NullPointerException e) {
            fail();
        }

        ArrayList<Character> line = myBoard.getDamageLine();
        assertEquals(line.size(),damage);
        for (Character c: line) {
            assertEquals(c,Character.BANSHEE);
        }

        /*
            Testing when value plus current damages is greater than 12
         */
        try {
            myBoard.addDamage(Character.BANSHEE, 10);
        }
        catch (IllegalArgumentException e) {
            fail();
        }
        catch (NullPointerException e) {
            fail();
        }
        line = myBoard.getDamageLine();
        assertEquals(line.size(),12);

        /*
            Test of the impossibility of having a null character
         */
        try {
            myBoard.addDamage(null, 2);
            fail();
        }
        catch (NullPointerException e) {

        }

        /*
            Test of the impossibility of having a negative value
         */
        try {
            myBoard.addDamage(Character.DISTRUCTOR,-2);
            fail();
        }
        catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void testResetDamageLine() {
        PlayerBoard myBoard = new PlayerBoard();
        myBoard.addDamage(Character.DISTRUCTOR,5);
        myBoard.resetDamageLine();
        ArrayList<Character> line = myBoard.getDamageLine();
        assertEquals(line.size(),0);
    }

    @Test
    public void testAddRevengeMarks() {
        PlayerBoard myBoard = new PlayerBoard();

        /*
            Testing the standard addition of marks
         */
        try {
            myBoard.addRevengeMark(Character.BANSHEE,2);
        }
        catch (IllegalArgumentException e) {
            fail();
        }
        catch (NullPointerException e) {
            fail();
        }

        ArrayList<Character> line = myBoard.getRevengeMarks();
        assertEquals(line.size(),2);
        for (Character c: line) {
            assertEquals(c,Character.BANSHEE);
        }

        /*
            Testing when value plus current marks is greater than 3
         */
        try {
            myBoard.addRevengeMark(Character.BANSHEE,2);
        }
        catch (IllegalArgumentException e) {
            fail();
        }
        catch (NullPointerException e) {
            fail();
        }

        line = myBoard.getRevengeMarks();
        assertEquals(line.size(),3);
        for (Character c: line) {
            assertEquals(c,Character.BANSHEE);
        }

        /*
            Test of the impossibility of having a null character
         */
        try {
            myBoard.addRevengeMark(null, 2);
            fail();
        }
        catch (NullPointerException e) {

        }

        /*
            Test of the impossibility of having a negative value
         */
        try {
            myBoard.addRevengeMark(Character.DISTRUCTOR,-2);
            fail();
        }
        catch (IllegalArgumentException e) {

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
        catch (NullPointerException e) {
            fail();
        }
        int counter = 0;
        ArrayList<Character> line = myBoard.getRevengeMarks();
        for (Character c: line) {
            if (c == Character.DOZER)
                counter++;
        }
        assertEquals(counter,0);

        /*
            Test of the impossibility of having a null character
         */
        try {
            myBoard.removeRevengeMarks(null);
            fail();
        }
        catch (NullPointerException e) {

        }
    }
}
