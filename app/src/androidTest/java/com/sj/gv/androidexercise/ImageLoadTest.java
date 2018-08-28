package com.sj.gv.androidexercise;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.sj.gv.androidexercise.view.ui.NewsFeedActivity;

import junit.framework.Assert;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.sj.gv.androidexercise.NewsFeedScreenTest.waitFor;

@RunWith(AndroidJUnit4.class)
public class ImageLoadTest {

    @Rule
    public ActivityTestRule mNewsActivityRule = new ActivityTestRule<>(NewsFeedActivity.class);


    @Test
    public void imageLoadedCheck(){

        Espresso.onView(isRoot()).perform(waitFor(9000));

        Espresso.onView(withId(R.id.news_recycler_view)).perform(RecyclerViewActions.scrollToPosition(0));

        Assert.assertTrue(withId(R.id.news_image) != null && withId(R.id.news_image) != withId(R.drawable.no_image));
    }

}
