package com.sj.gv.androidexercise;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.sj.gv.androidexercise.view.ui.NewsFeedActivity;

import junit.framework.Assert;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/*
* Run this test after turning off the mobile data or wifi connection, else the test will fail
*
* */
@RunWith(AndroidJUnit4.class)
public class ErrorTest {

    @Rule
    public ActivityTestRule mNewsActivityRule = new ActivityTestRule<>(NewsFeedActivity.class);


    @Test
    public void noNetworkErrorCheck(){

        if(!isConnected(mNewsActivityRule.getActivity().getApplicationContext())) {
            //check if the no network message is displayed on the screen
            Espresso.onView(withText(R.string.no_network_message)).check(matches(isDisplayed()));

            //if yes click on ok button
            Espresso.onView(withText(R.string.button_ok)).perform(click());
        }
    }



    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
