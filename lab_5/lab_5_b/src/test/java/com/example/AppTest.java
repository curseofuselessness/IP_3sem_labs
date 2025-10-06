package com.example;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class AppTest {

    @Test
    public void testAddMatrices() {
        double[][] A = {
            {1, 0},
            {0, 2}
        };
        double[][] B = {
            {3, 4},
            {0, 1}
        };
        double[][] expected = {
            {4, 4},
            {0, 3}
        };

        double[][] result = App.addMatrices(A, B);

        assertArrayEquals(expected[0], result[0], 0.0001);
        assertArrayEquals(expected[1], result[1], 0.0001);
    }

    @Test
    public void testMultiplyMatrices() {
        double[][] A = {
            {1, 2},
            {3, 4}
        };
        double[][] B = {
            {5, 6},
            {7, 8}
        };
        double[][] expected = {
            {19, 22},
            {43, 50}
        };

        double[][] result = App.multiplyMatrices(A, B);

        assertArrayEquals(expected[0], result[0], 0.0001);
        assertArrayEquals(expected[1], result[1], 0.0001);
    }

    @Test
    public void testBuildListFromMatrix() {
        double[][] matrix = {
            {0, 1},
            {2, 0}
        };

        App.Node head = App.buildListFromMatrix(matrix);

        assertNotNull(head);

        int count = 0;
        App.Node current = head;
        do {
            count++;
            current = current.next;
        } while (current != head);

        assertEquals(2, count);
    }
}
