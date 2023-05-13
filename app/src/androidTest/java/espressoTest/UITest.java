package espressoTest;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.assertion.PositionAssertions.isCompletelyLeftOf;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import static org.hamcrest.CoreMatchers.anything;
import static java.util.EnumSet.allOf;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.techhelper.ListActivity;
import com.example.techhelper.R;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.matchers.JUnitMatchers.*;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class UITest {

    @Rule
    public ActivityScenarioRule<ListActivity> activityScenarioRule = new ActivityScenarioRule<ListActivity>(ListActivity.class);

    @Test
    public void editBoxBesideImage(){
        //test to see if enter textfield is beside the add button
        onView(withHint("Manually Add Item")).check(matches(isDisplayed()));
        onView(withHint("Manually Add Item")).check(isCompletelyLeftOf(withId(R.id.add)));
    }

    @Test
    public void populatesList(){
        //cheeks if anything displayed in the listview
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).check(matches(isDisplayed()));
    }
}
