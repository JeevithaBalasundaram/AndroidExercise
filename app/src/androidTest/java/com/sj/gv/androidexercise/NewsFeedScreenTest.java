package com.sj.gv.androidexercise;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.test.espresso.Espresso;
import com.sj.gv.androidexercise.view.ui.NewsFeedActivity;

import junit.framework.Assert;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class NewsFeedScreenTest {

    @Rule
    public ActivityTestRule mNewsActivityRule = new ActivityTestRule<>(NewsFeedActivity.class);


    //test case to check if the count of items in recycler view is more than zero, meaning it has some data
    @Test
    public void newsLoadedTest(){
        //asking to wait for 9 seconds, until the view loads(assuming the view will not a null by then)
        // have to use IdlingResource until the view loads, waiting for random seconds is not the recommended way

        Espresso.onView(isRoot()).perform(waitFor(9000));

        // once the view is loaded check if the item count is more than 0

        Assert.assertTrue(getRecyclerViewItemCount() > 0);
    }

    private int getRecyclerViewItemCount(){
        RecyclerView recyclerView =  mNewsActivityRule.getActivity().findViewById(R.id.news_recycler_view);
        return recyclerView.getAdapter().getItemCount();
    }


    public static ViewAction waitFor(final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "Wait for " + millis + " milliseconds.";
            }

            @Override
            public void perform(UiController uiController, final View view) {
                uiController.loopMainThreadForAtLeast(millis);
            }
        };
    }
}
