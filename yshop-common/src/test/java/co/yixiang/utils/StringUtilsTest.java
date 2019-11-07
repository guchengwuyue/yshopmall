package co.yixiang.utils;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

public class StringUtilsTest {

    @Test
    public void testInString() {
        assertTrue(StringUtils.inString("?", "?"));
        assertFalse(StringUtils.inString("?", new String[]{}));
    }

    @Test
    public void testToCamelCase() {
        assertNull(StringUtils.toCamelCase(null));
    }

    @Test
    public void testToCapitalizeCamelCase() {
        assertNull(StringUtils.toCapitalizeCamelCase(null));
        Assert.assertEquals("HelloWorld", StringUtils.toCapitalizeCamelCase("hello_world"));
    }

    @Test
    public void testToUnderScoreCase() {
        assertNull(StringUtils.toUnderScoreCase(null));
        Assert.assertEquals("hello_world", StringUtils.toUnderScoreCase("helloWorld"));
        Assert.assertEquals("\u0000\u0000", StringUtils.toUnderScoreCase("\u0000\u0000"));
        Assert.assertEquals("\u0000_a", StringUtils.toUnderScoreCase("\u0000A"));
    }

    @Test
    public void testGetWeekDay() {
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("E");
        Assert.assertEquals(simpleDateformat.format(new Date()), StringUtils.getWeekDay());
    }

    @Test
    public void testGetIP() {
        Assert.assertEquals("127.0.0.1", StringUtils.getIP(new MockHttpServletRequest()));
    }
}