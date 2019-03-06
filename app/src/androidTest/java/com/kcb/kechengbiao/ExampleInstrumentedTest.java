package com.kcb.kechengbiao;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.Calendar;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.kcb.timetable", appContext.getPackageName());
    }
    @Test
    public void testData(){
        int w = Calendar.getInstance().get(Calendar.MONTH)+1;
        //The current month
        assertEquals(w,3)
    }
    @Test
    public void testWeek(){
        int w = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1;
        //The current day
        assertEquals(w,6);
    }


}
