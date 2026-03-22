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

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.allOf;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.data.Utils;

public class NewsPage {

    // Элементы страницы
    private final ViewInteraction title = onView(withText("News"));
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
    private static final Matcher<View> notNews = withId(R.id.control_panel_empty_news_list_text_view);
    private View decorView;

    // Проверка загрузки страницы
    public void newsPageLoad() {
        Allure.step("Ожидаем загрузку страницы News");
        onView(isRoot()).perform(Utils.waitDisplayed(R.id.edit_news_material_button, 8000));
    }

    // Видимость элементов
    public void newsPageVisible() {
        Allure.step("Подтверждаем видимость элементов на странице News");
        title.check(matches(isDisplayed()));
        sortButton.check(matches(isDisplayed())).check(matches(isClickable()));
        filterButton.check(matches(isDisplayed())).check(matches(isClickable()));
        controlPanelButton.check(matches(isDisplayed())).check(matches(isClickable()));
    }
    public void clickSortButton() {
        Allure.step("Нажимаем на кнопку сортировки");
        sortButton.perform(click());
    }
    public CreateNewsPage newsPageControlPanelButton() {
        Allure.step("Нажимаем на кнопку перехода к контрольной панели");
        controlPanelButton.perform(click());
        return new CreateNewsPage();
    }
    public void selectNewsInNewsPage(String string) {
        Allure.step("Нажимаем на новость содержащую заголовок: " + string);
        onView(allOf(newsTitle,
                withText(string)))
                .check(matches(isDisplayed()))
                .perform(click());
    }
    public void expandDescriptionNews(String title, String description) {
        Allure.step("Подтверждаем видимость описания " + description + " у новости содержащей заголовок " + title);
        onView(allOf(newsCard,
                hasDescendant(allOf(newsTitle,
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                        withText(title))),

                hasDescendant(allOf(newsDescription,
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                        withText(containsString(description))))))
                .check(matches(isDisplayed()));
    }
    public void collapseDescriptionNews(String title, String description) {
        Allure.step("Подтверждаем скрытие описания у новости содержащей заголовок" + title);
        onView(CoreMatchers.allOf(newsCard,
                hasDescendant(allOf(newsTitle,
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                        withText(title))),

                hasDescendant(allOf(newsDescription,
                        withEffectiveVisibility(ViewMatchers.Visibility.GONE),
                        withText(containsString(description))))))
                .check(matches(isDisplayed()));
    }
    public void filterNewsPage(String category, String startDate, String endDate) {
        Allure.step("Нажимаем на кнопку фильтра новостей");
        filterButton.check(matches(isDisplayed())).perform(click());
        Allure.step("Вводим название категории: " + category);
        filterCategoryField.perform(replaceText(category)).perform(ViewActions.closeSoftKeyboard());
        Allure.step("Указываем дату начала фильтрации: " + startDate);
        filterStartDate.perform(replaceText(startDate)).perform(ViewActions.closeSoftKeyboard());
        Allure.step("Указываем дату окончания фильтрации: " + endDate);
        filterEndDate.perform(replaceText(endDate)).perform(ViewActions.closeSoftKeyboard());
    }
    public void clickSaveFilterButtonNews() {
        Allure.step("Нажимаем на кнопку Save для сохранения выбранных фильтров");
        saveFilterButton.perform(click());
    }
    public void clickCancelFilterButtonNews() {
        Allure.step("Нажимаем на кнопку отмены фильтрации");
        cancelFilterButton.perform(click());
    }
    //Проверка текста ошибки
    public void errorTextMessageNews(String text) {
        Allure.step("Подтверждаем наличие сообщения об ошибке: " + text);
        onView(withText(text))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
        onView(withId(android.R.id.button1)).perform(click());
    }
    public void errorMessageNotNews() {
        Allure.step("Подтверждаем наличие сообщения об отсутствии новостей у выбранной категории: " + DataHelper.nothingHereError());
        notNews.matches(isDisplayed());
        notNews.matches(withText(DataHelper.nothingHereError()));
    }



}



