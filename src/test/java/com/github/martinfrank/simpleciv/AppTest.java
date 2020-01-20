package com.github.martinfrank.simpleciv;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);

        //this is 4x2
        List<String> lines = new ArrayList<>();
        lines.add ( "  +---+       +---+       +");
        lines.add ( " /     \\     /     \\     /");
        lines.add ( "+       +---+       +---+");
        lines.add ( " \\     /     \\     /     \\");
        lines.add ( "  +---+       +---+       +");
        lines.add ( " /     \\     /     \\     /");
        lines.add ( "+       +---+       +---+");
        lines.add ( " \\     /     \\     /     \\");
        lines.add ( "  +---+       +---+       +");
        lines.add ( " /     \\     /     \\     /");
        lines.add ( "+       +---+       +---+");
        lines.forEach(System.out::println);
    }
}
