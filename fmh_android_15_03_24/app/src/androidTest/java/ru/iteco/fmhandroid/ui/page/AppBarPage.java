package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AppBarPage {
    private final ViewInteraction menuButton = onView(withId(R.id.main_menu_image_button));
    private final ViewInteraction menuMainButton = onView(allOf(withId(android.R.id.title), withText("Main"), isDisplayed()));
    private final ViewInteraction menuNewsButton = onView(allOf(withId(android.R.id.title), withText("News"), isDisplayed()));
    private final ViewInteraction menuAboutButton = onView(allOf(withId(android.R.id.title), withText("About"), isDisplayed()));
    private final ViewInteraction ourMissionButton = onView(allOf(withId(R.id.our_mission_image_button), isDisplayed()));
    private final ViewInteraction authButton = onView(allOf(withId(R.id.authorization_image_button), withContentDescription("Authorization"), isDisplayed()));
    private final ViewInteraction logOutButton = onView(allOf(withId(android.R.id.title), withText("Log out"), isDisplayed()));

    // Кнопки меню
    public void MenuMainButton() {
        menuButton.perform(click());
        menuMainButton.perform(click());
    }

    public void MenuNewsButton() {
        menuButton.perform(click());
        menuNewsButton.perform(click());
    }

    public void MenuAboutButton() {
        menuButton.perform(click());
        menuAboutButton.perform(click());
    }

    // Цитаты
    public void OurMissionButton() {
        ourMissionButton.check(matches(isDisplayed()));
        ourMissionButton.perform(click());
    }

    // Выход из приложения
    public void LogOutButton() {
        authButton.perform(click());
        logOutButton.perform(click());
    }
}
