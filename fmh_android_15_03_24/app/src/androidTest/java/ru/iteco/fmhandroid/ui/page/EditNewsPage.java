package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;


import ru.iteco.fmhandroid.R;

public class EditNewsPage {

    CreateNewsPage createNewsPage = new CreateNewsPage();

    //Элементы страницы
    private static final Matcher<View> deleteButton = withId(R.id.delete_news_item_image_view);
    private static final Matcher<View> controlPanelNewsTitle = withId(R.id.news_item_title_text_view);
    private static final Matcher<View> editButton = withId(R.id.edit_news_item_image_view);

    // Элементы полей формы новостей
    private final Matcher<View> inputCategory = withId(R.id.news_item_category_text_auto_complete_text_view);
    private final Matcher<View> inputTitle = withId(R.id.news_item_title_text_input_edit_text);
    private final Matcher<View> inputDate = withId(R.id.news_item_publish_date_text_input_edit_text);
    private final Matcher<View> inputTime = withId(R.id.news_item_publish_time_text_input_edit_text);
    private final Matcher<View> inputDescription = withId(R.id.news_item_description_text_input_edit_text);
    private final ViewInteraction switcher = onView(withId(R.id.switcher));
    private final ViewInteraction saveButton = onView(withId(R.id.save_button));
    private final ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    private final ViewInteraction cancelButtonCancel = onView(allOf(withId(android.R.id.button2), withText("CANCEL")));
    private final ViewInteraction cancelButtonOk = onView(allOf(withId(android.R.id.button1), withText("OK")));

    private final String statusNotActive = "NOT ACTIVE";

    private final String statusActive = "ACTIVE";


    private View decorView;


    public void selectNews(String string) {
        onView(allOf(controlPanelNewsTitle,
                withText(string)))
                .check(matches(isDisplayed()))
                .perform(click());
    }
    public void deleteNewsButton(String string) {
        //удаляем новость
        onView(allOf(deleteButton, withContentDescription("News delete button"),
                hasSibling(withText(string)))).perform(click());
        onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(click());
        //проверяем удаление
        onView(allOf(withText(string), isDisplayed())).check(doesNotExist());
    }

    public void editNewsButton(String string) {
        //нажимаем редактировать
        onView(allOf(editButton, withContentDescription("News editing button"),
                hasSibling(withText(string)))).perform(click());

    }

    public void changeIfFieldInNewsForm(Matcher<View> field, String text) {
        onView(field).perform(replaceText(text));
        onView(field).check(matches(withText(text)));
        saveButton.perform(click());
    }
    public void cancelChangeIfFieldInNewsForm(Matcher<View> field, String text) {
        onView(field).perform(replaceText(text));
        onView(field).check(matches(withText(text)));
        cancelButton.perform(click());
        cancelButtonOk.perform(click());
        onView(allOf(withText(text), isDisplayed())).check(doesNotExist());
    }

    //Элементы форм ввода
    public Matcher<View> fieldCategory() {
        return inputCategory;
    }
    public Matcher<View> fieldTitle() {
        return inputTitle;
    }
    public Matcher<View> fieldDate() {
        return inputDate;
    }
    public Matcher<View> fieldTime() {
        return inputTime;
    }
    public Matcher<View> fieldDescription() {
        return inputDescription;
    }

    public void changeStatus() {
        switcher.perform(click());
        saveButton.perform(scrollTo(), click());
    }

    public void checkStatus(String string, String status) {
        onView(allOf(controlPanelNewsTitle,
                withText(string),
                hasSibling(withText(status)))).check(matches(isDisplayed()));
    }



    //Проверка текста ошибки
    public void errorTextMessage(String text) {
        onView(withText(text))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }





}
