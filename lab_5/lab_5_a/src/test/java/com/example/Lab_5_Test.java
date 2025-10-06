package com.example;

import org.junit.Test;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class Lab_5_Test {

    @Test
    public void testSimpleCase() {
        List<Double> I = Arrays.asList(1.0, 2.0, 3.0);
        List<Double> U = Arrays.asList(2.0, 4.0, 6.0); 
        double R = Lab_5_A.approximateResistance(I, U);
        assertEquals(2.0, R, 0.1);
    }

    @Test
    public void testApproximateCase() {
        List<Double> I = Arrays.asList(1.0, 2.0, 3.0);
        List<Double> U = Arrays.asList(2.1, 4.0, 5.9); 
        double R = Lab_5_A.approximateResistance(I, U);
        assertEquals(1.9667, R, 0.1);
    }

    @Test
    public void testZeroCurrent() {
        List<Double> I = Arrays.asList(0.0, 0.0, 0.0);
        List<Double> U = Arrays.asList(1.0, 2.0, 3.0);
        double R = Lab_5_A.approximateResistance(I, U);
        assertTrue(Double.isInfinite(R) || Double.isNaN(R));
    }

    @Test
    public void testEmptyLists() {
        List<Double> I = Collections.emptyList();
        List<Double> U = Collections.emptyList();
        double R = Lab_5_A.approximateResistance(I, U);
        assertTrue(Double.isNaN(R));
    }
}
