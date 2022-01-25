package com.example.maru;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.maru.RecyclerViewUtils.clickChildView;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
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

    @Before
    public void setUp() {
        MainActivity mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        currentMeetingsSize = mActivityRule.getActivity().mMeetingApiService.getMeetings().size();
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
      onView(ViewMatchers.withId(R.id.start_add_activity)).perform(click());
      String place= "Reunion A";
      String hour="15";
      String subject="financement";
      String recipient="rori@gmail.com";

      onView(withId(R.id.place)).perform(ViewActions.typeText(place));
      onView(withId(R.id.hour)).perform(ViewActions.typeText(hour));
      onView(withId(R.id.subject)).perform(ViewActions.typeText(subject));
      onView(withId(R.id.recipient)).perform(ViewActions.typeText(recipient));
      Espresso.closeSoftKeyboard();
      onView(withId(R.id.submitButton)).perform(click());
      onView(withText(place)).check(matches(isDisplayed()));
      onView(withText(hour)).check(matches(isDisplayed()));
      onView(withText(subject)).check(matches(isDisplayed()));
      onView(withText(recipient)).check(matches(isDisplayed()));
      onView(withId(R.id.recyclerview)).check(new RecyclerViewUtils.ItemCount(currentMeetingsSize + 1));
    }

    @Test
    public void checkIfRemovingMeetingIsWorking() {
        onView(ViewMatchers.withId(R.id.recyclerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, clickChildView(R.id.delete_button)));
        onView(withId(R.id.recyclerview)).check(new RecyclerViewUtils.ItemCount(currentMeetingsSize - 1));
    }
}

