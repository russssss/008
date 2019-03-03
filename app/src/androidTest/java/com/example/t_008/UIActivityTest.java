package com.example.t_008;


import android.content.pm.ActivityInfo;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.example.t_008.app.MainActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.repeatedlyUntil;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.Is.is;


@RunWith(AndroidJUnit4.class)
public class UIActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureTextChangesWork() {

        onView(allOf(withId(R.id.currency_sum_from), isDisplayed())).perform(swipeLeft());
        onView(withId(R.id.currency_sum_from))
                .perform(typeText("1"), closeSoftKeyboard());
        onView(withId(R.id.currency_from))
                .perform(swipeLeft(), swipeRight(), swipeLeft());
        onView(withId(R.id.currency_to))
                .perform(swipeLeft(), swipeRight(), swipeLeft(), swipeLeft(), swipeLeft());
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        onView(withId(R.id.currency_to)).perform(swipeLeft());
        onView(withId(R.id.currency_sum_from))
                .perform(typeText("5"), closeSoftKeyboard(), clearText());
        onView(withId(R.id.currency_to)).perform(swipeLeft(), swipeLeft(), swipeLeft(), swipeRight());
        onView(withId(R.id.currency_from))
                .perform(withCustomConstraints(swipeLeft(), isDisplayingAtLeast(85)));
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    private ViewAction withCustomConstraints(final ViewAction action, final Matcher<View> constraints) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return constraints;
            }

            @Override
            public String getDescription() {
                return action.getDescription();
            }

            @Override
            public void perform(UiController uiController, View view) {
                action.perform(uiController, view);
            }
        };
    }
}
