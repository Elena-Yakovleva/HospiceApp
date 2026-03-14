package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.not;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.Utils;

public class NewsPage {

    AppBarPage appBarPage = new AppBarPage();
    // Элементы страницы
    private final ViewInteraction title = onView(withText("News"));
    private final ViewInteraction menuButton = onView(withId(R.id.main_menu_image_button));
    private final ViewInteraction menuNewsButton = onView(allOf(withId(android.R.id.title), withText("News"), isDisplayed()));

    private final ViewInteraction sortButton = onView(withId(R.id.sort_news_material_button));
    private final ViewInteraction filterButton = onView(withId(R.id.filter_news_material_button));
    private final ViewInteraction controlPanelButton = onView(withId(R.id.edit_news_material_button));

    // Проверка загрузки страницы
    public void newsPageLoad() {
        onView(isRoot()).perform(Utils.waitDisplayed(R.id.edit_news_material_button, 8000));
    }

    // Видимость элементов
    public void newsPageVisible() {
        title.check(matches(isDisplayed()));
        sortButton.check(matches(isDisplayed())).check(matches(isClickable()));
        filterButton.check(matches(isDisplayed())).check(matches(isClickable()));
        controlPanelButton.check(matches(isDisplayed())).check(matches(isClickable()));
    }

    // перемещение по страницам
    public void newsPageMenuNewsButton() {
        menuButton.perform(click());
        menuNewsButton.check(matches(not(isClickable())));
    }

    public MainPage newsPageMenuMainButton() {
        appBarPage.MenuMainButton();
        return new MainPage();
    }

    public AboutPage newsPageMenuAboutButton() {
        appBarPage.MenuAboutButton();
        return new AboutPage();
    }

    public OurMissionPage newsPageOurMissionButton() {
        appBarPage.OurMissionButton();
        return new OurMissionPage();
    }

    public AuthPage newsPageLogOutButton() {
        appBarPage.LogOutButton();
        return new AuthPage();
    }

    public ControlPanelPage newsPageControlPanelButton() {
        controlPanelButton.perform(click());
        return new ControlPanelPage();
    }

}



/*
    // Открытие новости
    public void newsCardOpen() {
        onView(withId(R.id.news_list_recycler_view)).perform(actionOnItemAtPosition(0, click()));
        onView(allOf(withId(R.id.news_item_description_text_view), isCompletelyDisplayed()))
               .check(matches(isDisplayed()));
    //
    }
*/


