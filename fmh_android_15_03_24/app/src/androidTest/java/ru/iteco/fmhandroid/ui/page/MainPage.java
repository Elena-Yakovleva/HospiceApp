package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.not;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matchers;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.Utils;

public class MainPage {

    // Меню
    private final ViewInteraction menuButton = onView(withId(R.id.main_menu_image_button));
    private final ViewInteraction menuMainButton = onView(allOf(withId(android.R.id.title), withText("Main"),isDisplayed()));
    private final ViewInteraction menuNewsButton = onView(allOf(withId(android.R.id.title), withText("News"),isDisplayed()));
    private final ViewInteraction menuAboutButton = onView(allOf(withId(android.R.id.title), withText("About"), isDisplayed()));

    private final ViewInteraction ourMissionButton = onView(allOf(withId(R.id.our_mission_image_button), withContentDescription("Our Mission"), isDisplayed()));
    private final ViewInteraction authButton = onView(allOf(withId(R.id.authorization_image_button), withContentDescription("Authorization"), isDisplayed()));
    private final ViewInteraction logOutButton = onView(allOf(withId(android.R.id.title), withText("Log out"), isDisplayed()));


    // Блок новости
    private final ViewInteraction mainPageBlockNews = onView(withText("News"));
    private final ViewInteraction expandNewsBlockButton = onView(withId(R.id.expand_material_button));
    private final ViewInteraction allNewsButton = onView(withId(R.id.all_news_text_view));

    // Проверка загрузки страницы
    public void mainPageLoad() {
        onView(isRoot()).perform(Utils.waitDisplayed(R.id.main_menu_image_button, 5000));
    }

    // Проверка видимости элементов
    public void mainPageVisible() {
        mainPageBlockNews.check(matches(isDisplayed()));
    }

    // Кнопки меню
    public void mainPageMenuMainButton() {
        menuButton.perform(click());
        menuMainButton.check(matches(not(isClickable())));
    }
    public NewsPage mainPageMenuNewsButon() {
        menuButton.perform(click());
        menuNewsButton.perform(click());
        return new NewsPage();
    }
    public AboutPage mainPageMenuAboutButon() {
        menuButton.perform(click());
        menuAboutButton.perform(click());
        return new AboutPage();
    }

    // Переход к цитатам
    public OurMissionPage mainPageOurMissionButton() {
        ourMissionButton.perform(click());
        return new OurMissionPage();
    }

    // Выход из личного кабинета
    public AuthPage mainPageLogOutButon() {
        authButton.perform(click());
        logOutButton.perform(click());
        return new AuthPage();
    }

    // Блок новостей
    public void ExpandNewsBlock() {
        mainPageBlockNews.check(matches(isDisplayed()));
        expandNewsBlockButton.perform(click());
        allNewsButton.check(matches(isDisplayed()));
    }
    public void collapseNewsBlock() {
        mainPageBlockNews.check(matches(isDisplayed()));
        expandNewsBlockButton.perform(click());
        allNewsButton.check(matches(not(isDisplayed())));
    }

    // Переход на страницу новостей по кнопке All News
    public NewsPage buttonAllNews() {
        Utils.waitDisplayed(R.id.all_news_text_view, 500);
        allNewsButton.check(matches(isDisplayed()));
        allNewsButton.perform(click());
        return new NewsPage();
    }
}
