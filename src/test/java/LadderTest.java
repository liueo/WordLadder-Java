import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

/**
 * Unit test for simple ladder
 */
public class LadderTest {
    @Test
    public void testfindLadder() {
        WordLadder wl = new WordLadder();
        String word1 = "azure";
        String word2 = "metal";
        Stack s = wl.findLadder(word1,word2);
        assertTrue(s.isEmpty());
    }
}
