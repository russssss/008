package com.example.t_008;


import android.content.pm.ActivityInfo;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.t_008.feature_app.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.repeatedlyUntil;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.text.method.TextKeyListener.clear;
import static org.hamcrest.core.AllOf.allOf;


@RunWith(AndroidJUnit4.class)
public class UIActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureTextChangesWork() {
        onView(withId(R.id.currency_sum_from))
                .perform(typeText("3"), closeSoftKeyboard());
        onView(withId(R.id.currency_from))
            .perform(swipeLeft())
            .perform(swipeRight())
            .perform(swipeLeft());
        onView(withId(R.id.currency_to))
                .perform(swipeLeft())
                .perform(swipeRight())
                .perform(swipeLeft())
                .perform(swipeLeft());
        onView(withId(R.id.currency_to)).perform(swipeLeft());
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        onView(withId(R.id.currency_to)).perform(swipeLeft());
        onView(withId(R.id.currency_sum_from))
                .perform(typeText("5"), closeSoftKeyboard());
        onView(withId(R.id.currency_from)).perform(swipeRight());
        onView(withId(R.id.currency_to)).perform(swipeLeft());
    }
}
