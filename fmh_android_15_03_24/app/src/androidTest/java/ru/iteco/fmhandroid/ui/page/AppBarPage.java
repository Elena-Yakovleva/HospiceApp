package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.Utils;

public class AppBarPage {

    //app bar
    private final ViewInteraction menuButton = onView(withId(R.id.main_menu_image_button));
    private final ViewInteraction menuMainButon = onView(withText("Main"));
    private final ViewInteraction menuNewsButon = onView(withText("News"));
    private final ViewInteraction menuAboutButon = onView(withText("About"));
    private final ViewInteraction ourMissionButton = onView(withId(R.id.our_mission_image_button));
    private final ViewInteraction authButton = onView(withId(R.id.authorization_image_button));
    private final ViewInteraction logOutButton = onView(allOf(withId(android.R.id.title), withText("Log out"), isDisplayed()));

    // Меню
    public void menuMainButton() {
        menuButton.perform(click());
        menuMainButon.perform(click());
    }
    public NewsPage menuNewsButon() {
        menuButton.perform(click());
        menuNewsButon.check(matches(isDisplayed()));
        menuNewsButon.perform(click());
        return new NewsPage();
    }
    public AboutPage menuAboutButon() {
        menuButton.perform(click());
        menuAboutButon.check(matches(isDisplayed()));
        menuAboutButon.perform(click());
        return new AboutPage();
    }

    // Переход к цитатам
    public OurMissionPage ourMissionButton() {
        ourMissionButton.perform(click());
        return new OurMissionPage();
    }

    // Выход из личного кабинета
    public AuthPage logOutButton() {
        Utils.waitDisplayed(R.id.authorization_image_button, 500);
        authButton.perform(click());
        logOutButton.perform(click());
        return new AuthPage();
    }
}
