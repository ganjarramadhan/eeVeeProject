package sense.nl.eevee;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import sense.nl.eevee.main.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by ganjarramadhan on 12/22/16.
 */

@RunWith(AndroidJUnit4.class)
public class EeveeUITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void enableButtonOnEmailAndPasswordValid() {
        onView(withId(R.id.edt_email)).perform(typeText("malo.dumont@example.com"), closeSoftKeyboard());
        onView(withId(R.id.edt_pin)).perform(typeText("300781"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).check(matches(isEnabled()));
    }




}
