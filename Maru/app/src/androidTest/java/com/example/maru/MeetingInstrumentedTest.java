package com.example.maru;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.example.maru.RecyclerViewUtils.clickChildView;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.maru.ui.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MeetingInstrumentedTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(MainActivity.class);

    private int currentMeetingsSize = 9;
    private MainActivity mActivity;

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.maru", appContext.getPackageName());
    }

    @Test
    public void checkIfRecyclerViewIsNotEmpty() {
        onView(withId(R.id.recyclerview)).check(new RecyclerViewUtils.ItemCount(currentMeetingsSize));
    }

    @Test
    public void checkIfAddingMeetingIsWorking() {
        MainActivity activity = mActivityRule.getActivity();
        RecyclerView recyclerview = activity.findViewById(R.id.recyclerview);
        onView(withId(R.id.start_add_activity)).perform(click());
        onView(withId(R.id.recyclerview)).check(new RecyclerViewUtils.ItemCount(currentMeetingsSize + 1));
    }

    @Test
    public void checkIfRemovingMeetingIsWorking() {
        onView(ViewMatchers.withId(R.id.recyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildView(R.id.delete_button)));
        onView(withId(R.id.recyclerview)).check(new RecyclerViewUtils.ItemCount(currentMeetingsSize - 1));
    }
}

