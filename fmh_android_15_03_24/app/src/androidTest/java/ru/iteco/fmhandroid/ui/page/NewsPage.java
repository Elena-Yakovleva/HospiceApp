package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.not;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matchers;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.Utils;

public class NewsPage {

    // Элементы страницы
    private final ViewInteraction title= onView(withText("News"));

    private final ViewInteraction menuButton = onView(withId(R.id.main_menu_image_button));
    ViewInteraction menuMainButton = onView(allOf(withId(android.R.id.title), withText("Main"),isDisplayed()));
    private final ViewInteraction menuNewsButon = onView(allOf(withId(android.R.id.title), withText("News"), isDisplayed()));
    private final ViewInteraction menuAboutButon = onView(allOf(withId(android.R.id.title), withText("About"), isDisplayed()));

    // Проверка загрузки страницы
    public void newsPageLoad() {
        onView(isRoot()).perform(Utils.waitDisplayed(R.id.edit_news_material_button, 5000));
    }
    // Видимость элементов
    public void newsPageVisible() {
        title.check(matches(isDisplayed()));
    }

    // перемещение по страницам
    public void newsPageMenuNewsButton() {
        menuButton.perform(click());
        menuNewsButon.check(matches(not(isClickable())));
    }
    public MainPage newsPageMenuMainButton() {
        menuButton.perform(click());
        menuMainButton.perform(click());
        return new MainPage();
    }

    public AboutPage newsPageMenuAboutButton() {
        menuButton.perform(click());
        menuAboutButon.perform(click());
        return new AboutPage();
    }
}
