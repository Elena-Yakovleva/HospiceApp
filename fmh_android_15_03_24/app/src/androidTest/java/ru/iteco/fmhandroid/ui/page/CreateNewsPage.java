package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import static ru.iteco.fmhandroid.ui.data.Utils.waitDisplayed;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matchers;

import ru.iteco.fmhandroid.R;


public class CreateNewsPage {
    // Элементы страницы
    private final ViewInteraction addNewsButton = onView(allOf(withId(R.id.add_news_image_view), isDisplayed()));

    // Элементы формы добавления новости
    private final ViewInteraction fieldCategory = onView(withId(R.id.news_item_category_text_input_layout));
    private final ViewInteraction inputCategory = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    private final ViewInteraction fieldTitle = onView(withId(R.id.news_item_title_text_input_layout));
    private final ViewInteraction inputTitle = onView(withId(R.id.news_item_title_text_input_edit_text));
    private final ViewInteraction fieldDate = onView(withId(R.id.news_item_create_date_text_input_layout));
    private final ViewInteraction inputDate = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    private final ViewInteraction fieldTime = onView(withId(R.id.news_item_publish_time_text_input_layout));
    private final ViewInteraction inputTime = onView(withId((R.id.news_item_publish_time_text_input_edit_text)));
    private final ViewInteraction fieldDescription = onView(withId(R.id.news_item_description_text_input_layout));
    private final ViewInteraction inputDescription = onView(withId(R.id.news_item_description_text_input_edit_text));
    private final ViewInteraction switcher = onView(withId(R.id.switcher));
    private final ViewInteraction saveButton = onView(withId(R.id.save_button));
    private final ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    private final ViewInteraction cancelButtonCancel = onView(allOf(withId(android.R.id.button2), withText("CANCEL")));
    private final ViewInteraction cancelButtonOk = onView(allOf(withId(android.R.id.button1), withText("OK")));
    private View decorView;

    //Загрузка страницы
    public void controlPanelLoad() {
        onView(isRoot()).perform(waitDisplayed(R.id.add_news_image_view, 8000));
    }

    // Видимость элементов

    public void creatingNewsVisible() {
        fieldCategory.check(matches(isDisplayed()));
        fieldTitle.check(matches(isDisplayed()));
        fieldDate.check(matches(isDisplayed()));
        fieldTime.check(matches(isDisplayed()));
        fieldDescription.check(matches(isDisplayed()));
        switcher.check(matches(isDisplayed()));
        saveButton.check(matches(isDisplayed()));
        cancelButton.check(matches(isDisplayed()));
    }

    // Добавить новость
    public void addNews(String category, String title, String data, String time, String text) {
        addNewsButton.perform(click());
        creatingNewsVisible();
        // ввод категории
        inputCategory.perform(replaceText(category));
        // ввод титула;
        inputTitle.perform(replaceText(title));
        // ввод даты
        inputDate.perform(replaceText(data));
        // ввод времени
        inputTime.perform(replaceText(time));
        // ввод текста
        inputDescription.perform(replaceText(text));
    }

    public void saveAddNews() {
        saveButton.perform(click());
    }

    public void cancelAddNews() {
        cancelButton.perform(click());
        cancelButtonOk.perform(click());
    }

    //Проверка текста ошибки
    public void errorTextMessageCreateNews(String text) {
        onView(withText(text))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }
}
