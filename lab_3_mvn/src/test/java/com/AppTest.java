package com;

import java.util.Vector;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AppTest 
{

    @Test
    public void testMultipleWords() {
        assertEquals(vectorOf("this", "is", "test"), App.splitToWords("this is test"));
        assertEquals(vectorOf("Java", "programming"), App.splitToWords("Java programming"));
        assertEquals(vectorOf("multiple", "words", "here"), App.splitToWords("multiple,words.here"));
    }

    @Test
    public void testCaseSensitivity() {
        assertEquals(vectorOf("Hello", "WORLD", "Mixed"), 
                    App.splitToWords("Hello WORLD Mixed"));
        assertEquals(vectorOf("UPPERCASE", "lowercase"), 
                    App.splitToWords("UPPERCASE lowercase"));
    }

    @Test
    public void testNumbersInWords() {
        assertEquals(vectorOf("word123", "test456"), 
                    App.splitToWords("word123 test456"));
        assertEquals(vectorOf("abc123def"), 
                    App.splitToWords("abc123def"));
    }

    @Test
    public void testOnlyNumbers() {
        assertEquals(new Vector<String>(), App.splitToWords("123 456 789"));
        assertEquals(new Vector<String>(), App.splitToWords("000"));
    }

    @Test
    public void testMixedNumbersAndWords() {
        assertEquals(vectorOf("hello", "world"), 
                    App.splitToWords("123 hello 456 world 789"));
        assertEquals(vectorOf("test"), 
                    App.splitToWords("999 test 888"));
    }

    private Vector<String> vectorOf(String... elements) {
        Vector<String> result = new Vector<>();
        for (String element : elements) {
            result.add(element);
        }
        return result;
    }
}