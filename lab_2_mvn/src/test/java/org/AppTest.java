package org;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class AppTest {
    
    @Test
    public void testAscendingRow() {
        assertEquals(5, App.searchMaxEleventIfSequence(new int[][]{
            {1, 2, 3, 4, 5},
            {9, 8, 7, 6, 0},
            {2, 2, 2, 2, 2}
        }, 0));
    }
    
    @Test 
    public void testDescendingRow() {
        assertEquals(9, App.searchMaxEleventIfSequence(new int[][]{
            {5, 4, 3, 2, 1},
            {9, 8, 7, 6, 5},
            {2, 2, 2, 2, 2}
        }, 1));
    }
    
    @Test
    public void testNotOrderedRow() {
        assertEquals(-1, App.searchMaxEleventIfSequence(new int[][]{
            {1, 5, 2, 8, 3},
            {9, 1, 7, 6, 5},
            {2, 3, 1, 2, 4}
        }, 1));
    }
    
    @Test
    public void testAllSameElements() {
        assertEquals(7, App.searchMaxEleventIfSequence(new int[][]{
            {1, 2, 3, 4},
            {9, 8, 7, 6},
            {7, 7, 7, 7}
        }, 2));
    }
    
    @Test
    public void testSingleColumn() {
        assertEquals(3, App.searchMaxEleventIfSequence(new int[][]{
            {5},
            {3}, 
            {7},
            {1}
        }, 1));
    }
    
    @Test
    public void testTwoElementsAscending() {
        assertEquals(2, App.searchMaxEleventIfSequence(new int[][]{
            {1, 2},
            {5, 4},
            {3, 3}
        }, 0));
    }
    
    @Test
    public void testTwoElementsDescending() {
        assertEquals(5, App.searchMaxEleventIfSequence(new int[][]{
            {2, 1},
            {5, 4},
            {3, 3}
        }, 1));
    }
    
    @Test
    public void testCustomDefaultValue() {
        assertEquals(999, App.searchMaxEleventIfSequence(new int[][]{
            {1, 5, 2},
            {9, 1, 7}
        }, 999));
    }
    
    @Test
    public void testAllRowsOrdered() {
        assertEquals(3, App.searchMaxEleventIfSequence(new int[][]{
            {1, 2, 3},
            {6, 5, 4}
        }, 0));
    }
}