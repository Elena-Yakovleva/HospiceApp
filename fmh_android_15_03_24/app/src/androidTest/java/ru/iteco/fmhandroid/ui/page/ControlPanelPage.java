package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import ru.iteco.fmhandroid.R;


public class ControlPanelPage {
    AppBarPage appBarPage = new AppBarPage();

    private final ViewInteraction title = onView(withText("Control panel"));

    // Элементы новости
    private static final Matcher<View> newsTitle = withId(R.id.news_item_title_text_view);
    private static final Matcher<View> newsCard = withId(R.id.news_item_material_card_view);
    private static final Matcher<View> newsDescription = withId(R.id.news_item_description_text_view);
    private static final Matcher<View> messageNotNews = withId(R.id.control_panel_empty_news_list_text_view);
    //Элементы фильтра
    private static final ViewInteraction filterButton = onView(withId(R.id.filter_news_material_button));
    private static final ViewInteraction filterCategoryField = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    private static final ViewInteraction filterStartDate = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
    private static final ViewInteraction filterEndDate = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));
    private static final ViewInteraction filterCheckBoxActive = onView(withId(R.id.filter_news_active_material_check_box));
    private static final ViewInteraction filterCheckBoxNotActive = onView(withId(R.id.filter_news_inactive_material_check_box));
    private static final ViewInteraction saveFilterButton = onView(withId(R.id.filter_button));
    private static final ViewInteraction cancelFilterButton = onView(withId(R.id.cancel_button));

    private View decorView;

    public void controlPanelVisible() {
        title.check(matches(isDisplayed()));
    }

    public void controlPanelMenuNewsButton() {
        appBarPage.MenuNewsButton();
    }

    public void selectNews(String string) {
        onView(allOf(newsTitle,
                withText(string)))
                .check(matches(isDisplayed()))
                .perform(click());
    }
    public void selectCancelNews(String string) {
        onView(allOf(newsTitle,
                withText(string)))
                .check(doesNotExist());
    }

    public void checkStatus(String string, String status) {
        onView(allOf(newsTitle,
                withText(string),
                hasSibling(withText(status)))).check(matches(isDisplayed()));
    }

    public void expandDescription(String title, String description) {
        onView(allOf(newsCard,
                hasDescendant(Matchers.allOf(newsTitle,
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                        withText(title))),

                hasDescendant(Matchers.allOf(newsDescription,
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                        withText(containsString(description))))))
                .check(matches(isDisplayed()));
    }

    public void collapseDescription(String title, String description) {
        onView(CoreMatchers.allOf(newsCard,
                hasDescendant(Matchers.allOf(newsTitle,
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                        withText(title))),

                hasDescendant(Matchers.allOf(newsDescription,
                        withEffectiveVisibility(ViewMatchers.Visibility.GONE),
                        withText(containsString(description))))))
                .check(matches(isDisplayed()));
    }

    public void filterNews(String category, String startDate, String endDate) {
        filterButton.check(matches(isDisplayed())).perform(click());
        filterCategoryField.perform(replaceText(category));
        filterStartDate.perform(replaceText(startDate));
        filterEndDate.perform(replaceText(endDate));
    }

    public void clickCheckBoxActive() {
        filterCheckBoxActive.perform(click());
    }

    public void clickCheckBoxInActive() {
        filterCheckBoxNotActive.perform(click());
    }

    public void clickSaveFilterButton() {
        saveFilterButton.perform(click());
    }

    public void clickCancelFilterButton() {
        cancelFilterButton.perform(click());
    }


    //Проверка текста ошибки
    public void errorTextMessage(String text) {
        onView(withText(text))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
        onView(withId(android.R.id.button1)).perform(click());
    }

    public void message() {
        messageNotNews.matches(isDisplayed());
        messageNotNews.matches(withText("There is nothing here yet…"));
    }
}
