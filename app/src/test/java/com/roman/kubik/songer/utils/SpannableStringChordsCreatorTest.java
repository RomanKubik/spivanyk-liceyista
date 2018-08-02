package com.roman.kubik.songer.utils;

import org.junit.Test;

import java.util.List;

import kotlin.ranges.IntRange;

import static org.junit.Assert.assertEquals;

/**
 * Created by kubik on 2/25/18.
 */

public class SpannableStringChordsCreatorTest {

    @Test
    public void findSelectionsTest() throws Exception {
        String text = "Some awesome <Bm> text with <Am> chord";
        SpannableStringChordsCreator chordsCreator = new SpannableStringChordsCreator();
        List<IntRange> list = chordsCreator.findSelections(text);
        assertEquals(2, list.size());
        assertEquals(new IntRange(13, 17), list.get(0));
        assertEquals(new IntRange(28, 32), list.get(1));
    }
}
