package com.example;

import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class AppTest 
{
    @Test
    public void testBasicDecryption() {
        // "147258369" -> "123456789"
        char[] result = App.decrypt("147258369");
        assertArrayEquals("123456789".toCharArray(), result);
    }

    @Test
    public void testShortString() {
        // "123" -> "123" 
        char[] result = App.decrypt("123");
        assertArrayEquals("123".toCharArray(), result);
    }

    @Test
    public void testMediumString() { 
        char[] result = App.decrypt("Heocedhcei itt\"sdnna uor eo tutes k t.vy vet dwhn s?aeAo");
        assertArrayEquals("Have you covered the code with unit tests?\" asked Anton.".toCharArray(), result);
    }

    @Test
    public void testEmptyString() {
        char[] result = App.decrypt("pcirioesn");
        assertArrayEquals("precision".toCharArray(), result);
    }

    @Test
    public void testLetters() {
        char[] result = App.decrypt("abcdefghi");
        char[] expected = "adgbehcfi".toCharArray();
        assertArrayEquals(expected, result);
    }
}