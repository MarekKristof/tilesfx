package eu.hansolo.tilesfx.tools;

import javafx.scene.paint.Color;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static eu.hansolo.tilesfx.tools.Helper.*;
import static org.junit.Assert.*;

/**
 * @author Marek Krištof
 * Created: 13-Nov-18
 */
public class HelperTest {

    @Test
    public void colorToCssTest() {
        String blackCss        = "#00000000";
        Color  blackColorToCss = Color.web("0x00000000");
        assertEquals(blackCss, colorToCss(blackColorToCss));
    }

    @Test
    public void getMinutesTest() {
        assertSame(1, getMinutes(60000));
    }

    @Test
    public void getSecondsTest() {
        assertSame(1, getSeconds(1000));
    }

    @Test
    public void roundDoubleToIntTest() {
        assertEquals(1, roundDoubleToInt(1.1));
        assertEquals(1, roundDoubleToInt(1.4));
        assertEquals(1, roundDoubleToInt(1.01));
        assertEquals(1, roundDoubleToInt(1.4999999999));
        assertEquals(1, roundDoubleToInt(0.51));
        assertEquals(1, roundDoubleToInt(0.5));

        assertNotSame(1, roundDoubleToInt(1.5));
        assertNotSame(1, roundDoubleToInt(0.49));
        assertNotSame(1, roundDoubleToInt(0));
    }

    @Test
    public void distanceCalcTest() {
        double distance = 5.0;
        assertEquals(distance, distance(new Point(0, 5), new Point(0, 0)), 0.01);
    }

    @Test
    public void stopTaskTest() {
        ScheduledFuture<?> f = Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
            public void run() {
            }
        }, 0, 10000, TimeUnit.MILLISECONDS);

        stopTask(f);
        assertEquals(f.isCancelled(), true);
    }

    @Test
    public void colorToYUVTest() {
        System.out.println(Arrays.toString(colorToYUV(Color.BLUE)));
        assertTrue(Arrays.equals(new double[]{0, 128, 128}, colorToYUV(Color.BLACK)));
    }

    @Test
    public void isBrightTest() {
        assertFalse(isBright(Color.BLACK));
        assertTrue(isBright(Color.WHITE));
        assertFalse(isBright(Color.RED));
    }

    @Test
    public void isDarkTest() {
        assertFalse(isDark(Color.WHITE));
        assertTrue(isDark(Color.BLACK));
        assertTrue(isDark(Color.RED));
    }

    @Test
    public void getContrastColorTest() {
        assertEquals(Color.WHITE, getContrastColor(Color.BLACK));
        assertEquals(Color.BLACK, getContrastColor(Color.WHITE));
        assertNotSame(Color.WHITE, getContrastColor(Color.RED));
        assertSame(Color.WHITE, getContrastColor(Color.BLACK));
    }
}