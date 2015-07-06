package pl.gmatusz.confitura2015.viacom.duplicates;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by gmatusz on 2015-07-06.
 */
public class DuplicateProcessorTest {
    DuplicateProcessor<String> processor;

    @Before
    public void setUp() {
        processor = new DuplicateProcessor(16, 0.75f);
    }

    @Test
    public void emptyListTest() {
        uniqTest(Arrays.asList(), Arrays.asList());
    }

    @Test
    public void simpleDuplicationTest() {
        uniqTest(Arrays.asList("1"), Arrays.asList("1"));
        uniqTest(Arrays.asList("1", "1"), Arrays.asList("1"));
        uniqTest(Arrays.asList("1", "1", "1", "1", "12".substring(0,1)), Arrays.asList("1"));
    }

    @Test
    public void sequenceTest() {
        uniqTest(Arrays.asList("1", "2", "3", "2", "1", "4", "2", "5"),
                 Arrays.asList("1", "2", "3", "4", "5"));
    }

    public void uniqTest(List input, List output) {
        assertArrayEquals(output.toArray(), processor.uniq(input).toArray());
    }
}
