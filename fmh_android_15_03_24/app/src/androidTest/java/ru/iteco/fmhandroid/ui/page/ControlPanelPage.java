package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.data.Utils;


public class ControlPanelPage {
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
    private final ViewInteraction sortButtonNews = onView(withId(R.id.sort_news_material_button));
    private View decorView;

    public void controlPanelVisible() {
        Allure.step("Подтверждаем видимость элемента страницы Control Panel");
        title.check(matches(isDisplayed()));
    }

    public void clickSortButton() {
        Allure.step("Нажимаем на кнопку сортировки публикаций");
        sortButtonNews.perform(click());
    }
    public void selectNews(String string) {
        Allure.step("Выбираем новость с заголовком: " + string);
        onView(allOf(newsTitle,
                withText(string)))
                .check(matches(isDisplayed()))
                .perform(click());
    }
    public void selectCancelNews(String string) {
        Allure.step("Подтверждаем отсутствие новости с заголовком: " + string);
        onView(allOf(newsTitle,
                withText(string)))
                .check(doesNotExist());
    }

    public void checkStatus(String string, String status) {
        Allure.step("Проверяем наличие статуса: " + status + " у новости с заголовком: " + string);
        onView(allOf(newsTitle,
                withText(string),
                hasSibling(withText(status)))).check(matches(isDisplayed()));
    }

    public void expandDescription(String title, String description) {
        Allure.step("Разворачиваем новость с заголовком: " + title + " и проверяем видимость описания");
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
        Allure.step("Сворачиваем новость с заголовком: " + title + " и проверяем отсутствие описания");
        onView(allOf(newsCard,
                hasDescendant(allOf(newsTitle,
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                        withText(title))),

                hasDescendant(allOf(newsDescription,
                        withEffectiveVisibility(ViewMatchers.Visibility.GONE),
                        withText(containsString(description))))))
                .check(matches(isDisplayed()));
    }

    public void filterNews(String category, String startDate, String endDate) {
        Allure.step("Нажимаем на кнопку фильтра новостей");
        filterButton.check(matches(isDisplayed())).perform(click());
        Allure.step("Вводим название категории: " + category);
        filterCategoryField.perform(replaceText(category));
        Allure.step("Указываем дату начала фильтрации: " + startDate);
        filterStartDate.perform(replaceText(startDate));
        Allure.step("Указываем дату окончания фильтрации: " + endDate);
        filterEndDate.perform(replaceText(endDate));
    }

    public void clickCheckBoxActive() {
        Allure.step("Нажать на чек-бокс Active");
        filterCheckBoxActive.perform(click());
    }

    public void clickCheckBoxInActive() {
        Allure.step("Нажать на чек-бокс Not Active");
        filterCheckBoxNotActive.perform(click());
    }

    public void clickSaveFilterButton() {
        Allure.step("Нажимаем на кнопку Save для сохранения выбранных фильтров");
        saveFilterButton.perform(click());
    }

    public void clickCancelFilterButton() {
        Allure.step("Нажимаем на кнопку Cancel для отмены сохранения фильтров");
        cancelFilterButton.perform(click());
    }


    //Проверка текста ошибки
    public void errorTextMessage(String text) {
        Allure.step("Подтверждаем наличие сообщения об ошибке: " + text);
        onView(withText(text))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
        onView(withId(android.R.id.button1)).perform(click());
    }

    public void errorMessage() {
        Allure.step("Подтверждаем наличие сообщения об ошибке:" + DataHelper.nothingHereError());
        messageNotNews.matches(isDisplayed());
        messageNotNews.matches(withText(DataHelper.nothingHereError()));
    }

    public void dateVisible(String title, String date) {
        Allure.step("Сверяем отображение даты создания (" + DataHelper.currentDate() + ") у новости с титулом: " + title+ " и ее реальным отображением в карточке новости ");
        onView(allOf(newsCard,
                hasDescendant(allOf(newsTitle,
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                        withText(title))),

                hasDescendant(allOf(withId(R.id.news_item_publication_date_text_view),
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                        withText(containsString(date))))))
                .check(matches(isDisplayed()));
        /*
        onView(allOf(newsCard,
                hasDescendant(allOf(newsTitle,
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                        withText(title))),

                hasDescendant(allOf(withId(R.id.news_item_create_date_text_view),
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                        withText(containsString(date1))))))
                .check(matches(isDisplayed()));*/

        String actualDate = Utils.getText(allOf(
                withId(R.id.news_item_create_date_text_view),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                hasSibling(allOf(newsTitle, withText(title)))

        ));
        assertThat(actualDate, containsString(DataHelper.currentDate()));
    }
}
