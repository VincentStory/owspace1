package redroid.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * TODO
 *
 * @author RobinVanYang created at 2017-07-17 20:01.
 */
public class StringUtilsTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void md5() throws Exception {
        assertEquals("900150983cd24fb0d6963f7d28e17f72", StringUtils.md5("abc"));
    }

    @Test
    public void notNull() throws Exception {
        assertFalse(StringUtils.notNull(""));
        assertFalse(StringUtils.notNull("null"));
    }

    @Test
    public void toPrettyJson() throws Exception {
    }

}