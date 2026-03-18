package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.Utils;

public class NewsPage {

    AppBarPage appBarPage = new AppBarPage();
    // Элементы страницы
    private final ViewInteraction title = onView(withText("News"));
    private final ViewInteraction menuButton = onView(withId(R.id.main_menu_image_button));
    private final ViewInteraction menuNewsButton = onView(allOf(withId(android.R.id.title), withText("News"), isDisplayed()));
    private static final Matcher<View> newsTitle = withId(R.id.news_item_title_text_view);
    private static final Matcher<View> newsCard = withId(R.id.news_item_material_card_view);
    private static final Matcher<View> newsDescription = withId(R.id.news_item_description_text_view);
    // Действия с новостями
    private final ViewInteraction sortButton = onView(withId(R.id.sort_news_material_button));
    private final ViewInteraction filterButton = onView(withId(R.id.filter_news_material_button));
    private final ViewInteraction controlPanelButton = onView(withId(R.id.edit_news_material_button));

    //Фильтр
    private static final ViewInteraction filterCategoryField = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    private static final ViewInteraction filterStartDate = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
    private static final ViewInteraction filterEndDate = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));
    private static final ViewInteraction saveFilterButton = onView(withId(R.id.filter_button));
    private static final ViewInteraction cancelFilterButton = onView(withId(R.id.cancel_button));
    private View decorView;

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

    public CreateNewsPage newsPageControlPanelButton() {
        controlPanelButton.perform(click());
        return new CreateNewsPage();
    }

    public void selectNewsInNewsPage(String string) {
        onView(Matchers.allOf(newsTitle,
                withText(string)))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void expandDescriptionNews(String title, String description) {
        onView(Matchers.allOf(newsCard,
                hasDescendant(Matchers.allOf(newsTitle,
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                        withText(title))),

                hasDescendant(Matchers.allOf(newsDescription,
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                        withText(containsString(description))))))
                .check(matches(isDisplayed()));
    }

    public void collapseDescriptionNews(String title, String description) {
        onView(CoreMatchers.allOf(newsCard,
                hasDescendant(Matchers.allOf(newsTitle,
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                        withText(title))),

                hasDescendant(Matchers.allOf(newsDescription,
                        withEffectiveVisibility(ViewMatchers.Visibility.GONE),
                        withText(containsString(description))))))
                .check(matches(isDisplayed()));
    }

    public void filterNewsPage(String category, String startDate, String endDate) {
        filterButton.check(matches(isDisplayed())).perform(click());
        filterCategoryField.perform(replaceText(category));
        filterStartDate.perform(replaceText(startDate));
        filterEndDate.perform(replaceText(endDate));
    }

    public void clickSaveFilterButtonNews() {
        saveFilterButton.perform(click());
    }

    public void clickCancelFilterButtonNews() {
        cancelFilterButton.perform(click());
    }


    //Проверка текста ошибки
    public void errorTextMessageNews(String text) {
        onView(withText(text))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
        onView(withId(android.R.id.button1)).perform(click());
    }


}



