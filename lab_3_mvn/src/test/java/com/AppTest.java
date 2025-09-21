package com;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Vector;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void testEmptyString() {
        assertEquals(new Vector<String>(), App.splitToWords(""));
    }

    @Test
    public void testOnlyDelimiters() {
        assertEquals(new Vector<String>(), App.splitToWords("   "));
        assertEquals(new Vector<String>(), App.splitToWords("!@#$%"));
        assertEquals(new Vector<String>(), App.splitToWords("123456"));
    }

    @Test
    public void testSingleWord() {
        assertEquals(vectorOf("hello"), App.splitToWords("hello"));
        assertEquals(vectorOf("hello"), App.splitToWords("  hello  "));
        assertEquals(vectorOf("hello"), App.splitToWords("!hello!"));
        assertEquals(vectorOf("hello"), App.splitToWords("\nhello\n"));
    }

    @Test
    public void testMultipleWords() {
        assertEquals(vectorOf("this", "is", "test"), App.splitToWords("this is test"));
        assertEquals(vectorOf("Java", "programming"), App.splitToWords("Java programming"));
        assertEquals(vectorOf("multiple", "words", "here"), App.splitToWords("multiple,words.here"));
    }

    @Test
    public void testMixedContent() {
        assertEquals(vectorOf("test", "with", "numbers", "and", "symbols"), 
                    App.splitToWords("test123with456numbers@and#symbols"));
        assertEquals(vectorOf("start", "middle", "end"), 
                    App.splitToWords("!!!start---middle+++end???"));
    }

    @Test
    public void testCaseSensitivity() {
        assertEquals(vectorOf("Hello", "WORLD", "Mixed"), 
                    App.splitToWords("Hello WORLD Mixed"));
        assertEquals(vectorOf("UPPERCASE", "lowercase"), 
                    App.splitToWords("UPPERCASE lowercase"));
    }

    private Vector<String> vectorOf(String... elements) {
        Vector<String> result = new Vector<>();
        for (String element : elements) {
            result.add(element);
        }
        return result;
    }
}
