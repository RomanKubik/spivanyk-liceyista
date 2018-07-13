package com.roman.kubik.songer.domain.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Tests for {@link ChordsRecognizer}
 * Created by kubik on 1/28/18.
 */

public class ChordsRecognizerTest {

    private ChordsRecognizer chordsRecognizer;

    @Before
    public void init() {
        chordsRecognizer = new ChordsRecognizer();
    }

    @Test
    public void recognizeChords() {
        String toRecognize =
                "A B C D E F G H\n" +
                        "Song text to not be\n" +
                        "Am Bm Cm Dm Em Fm Gm Hm\n" +
                        "recognized as chords\n" +
                        "SECOND LINE\n" +
                        "A7 B7 C7 D7 E7 F7 G7 H7\n" +
                        "Song text to not be\n" +
                        "Am7 Bm7 Cm7 Dm7 Em7 Fm7 Gm7 Hm7\n" +
                        "recognized as chords\n" +
                        "THIRD LINE\n" +
                        "A7# B7# C7# D7# E7# F7# G7# H7#\n" +
                        "Song text to not be\n" +
                        "Am7# Bm7# Cm7# Dm7# Em7# Fm7# Gm7# Hm7#\n" +
                        "recognized as chords\n" +
                        "FOURTH LINE\n" +
                        "A7b B7b C7b D7b E7b F7b G7b H7b\n" +
                        "Song text to not be\n" +
                        "Am7b Bm7b Cm7b Dm7b Em7b Fm7b Gm7b Hm7b\n" +
                        "recognized as chords\n" +
                        "FIFTH LINE\n" +
                        "Ab Bb Cb Db Eb Fb Gb Hb\n" +
                        "Song text to not be\n" +
                        "Amb Bmb Cmb Dmb Emb Fmb Gmb Hmb\n" +
                        "recognized as chords\n" +
                        "SIXTH LINE\n" +
                        "A# B# C# D# E# F# G# H#\n" +
                        "Song text to not be\n" +
                        "Am# Bm# Cm# Dm# Em# Fm# Gm# Hm#\n" +
                        "recognized as chords";
        String expectedResult =
                "<A> <B> <C> <D> <E> <F> <G> <H>\n" +
                        "Song text to not be\n" +
                        "<Am> <Bm> <Cm> <Dm> <Em> <Fm> <Gm> <Hm>\n" +
                        "recognized as chords\n" +
                        "SECOND LINE\n" +
                        "<A7> <B7> <C7> <D7> <E7> <F7> <G7> <H7>\n" +
                        "Song text to not be\n" +
                        "<Am7> <Bm7> <Cm7> <Dm7> <Em7> <Fm7> <Gm7> <Hm7>\n" +
                        "recognized as chords\n" +
                        "THIRD LINE\n" +
                        "<A7#> <B7#> <C7#> <D7#> <E7#> <F7#> <G7#> <H7#>\n" +
                        "Song text to not be\n" +
                        "<Am7#> <Bm7#> <Cm7#> <Dm7#> <Em7#> <Fm7#> <Gm7#> <Hm7#>\n" +
                        "recognized as chords\n" +
                        "FOURTH LINE\n" +
                        "<A7b> <B7b> <C7b> <D7b> <E7b> <F7b> <G7b> <H7b>\n" +
                        "Song text to not be\n" +
                        "<Am7b> <Bm7b> <Cm7b> <Dm7b> <Em7b> <Fm7b> <Gm7b> <Hm7b>\n" +
                        "recognized as chords\n" +
                        "FIFTH LINE\n" +
                        "<Ab> <Bb> <Cb> <Db> <Eb> <Fb> <Gb> <Hb>\n" +
                        "Song text to not be\n" +
                        "<Amb> <Bmb> <Cmb> <Dmb> <Emb> <Fmb> <Gmb> <Hmb>\n" +
                        "recognized as chords\n" +
                        "SIXTH LINE\n" +
                        "<A#> <B#> <C#> <D#> <E#> <F#> <G#> <H#>\n" +
                        "Song text to not be\n" +
                        "<Am#> <Bm#> <Cm#> <Dm#> <Em#> <Fm#> <Gm#> <Hm#>\n" +
                        "recognized as chords";

        Assert.assertEquals(expectedResult, chordsRecognizer.format(toRecognize));
    }

    @Test
    public void findSpecificChord() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String textToFind = "A\n" +
                "Some sAmple Apple text\n" +
                "A B MappAAAAA";
        String expectedText = "<A>\n" +
                "Some sAmple Apple text\n" +
                "<A> B MappAAAAA";

        Method method = ChordsRecognizer.class.getDeclaredMethod("findAndReplace", String.class, String.class);
        method.setAccessible(true);
        String actual = (String) method.invoke(chordsRecognizer, textToFind, ChordsRecognizer.A);
        Assert.assertEquals(expectedText, actual);
    }


}
