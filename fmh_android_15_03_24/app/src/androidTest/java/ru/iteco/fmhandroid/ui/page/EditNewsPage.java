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
import androidx.test.espresso.action.ViewActions;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class EditNewsPage {
    //Элементы страницы
    private static final Matcher<View> deleteButton = withId(R.id.delete_news_item_image_view);
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

    private View decorView;

    public void deleteNewsButton(String string) {
        Allure.step("Удаляем новость с заголовком: " + string);
        onView(allOf(deleteButton, withContentDescription("News delete button"),
                hasSibling(withText(string)))).perform(click());
        cancelButtonOk.perform(click());
        Allure.step("Убеждаемся, что новость с заголовком: " + string + " отсутствует в списке");
        onView(allOf(withText(string), isDisplayed())).check(doesNotExist());
    }

    public void editNewsButton(String string) {
        Allure.step("Нажимаем на кнопку Редактировать у новости с заголовком: " + string);
        onView(allOf(editButton, withContentDescription("News editing button"),
                hasSibling(withText(string)))).perform(click());
    }

    public void changeIfFieldInNewsForm(String fieldName, Matcher<View> field, String text) {
        Allure.step("Вносим в поле: " + fieldName + " следующие данные: " + text);
        onView(field).perform(replaceText(text)).perform(ViewActions.closeSoftKeyboard());;
        onView(field).check(matches(withText(text)));
        Allure.step("Сохраняем изменения кнопкой Save");
        saveButton.perform(click());
    }

    public void cancelChangeIfFieldInNewsForm(String fieldName, Matcher<View> field, String text) {
        Allure.step("Вносим в поле: " + fieldName + " следующие данные: " + text);
        onView(field).perform(replaceText(text)).perform(ViewActions.closeSoftKeyboard());
        onView(field).check(matches(withText(text)));
        Allure.step("Отменяем изменение кнопкой Cancel");
        cancelButton.perform(click());
        cancelButtonOk.perform(click());
        Allure.step("Подтверждаем отсутствие изменений у поля: " + field);
        onView(allOf(withText(text), isDisplayed())).check(doesNotExist());
    }

    //Элементы форм ввода
    public Matcher<View> fieldCategory() {
        Allure.step("Категория");
        return inputCategory;
    }


    public Matcher<View> fieldTitle() {
        Allure.step("Заголовок");
        return inputTitle;
    }

    public Matcher<View> fieldDate() {
        Allure.step("Дата публикации");
        return inputDate;
    }

    public Matcher<View> fieldTime() {
        Allure.step("Время публикации");
        return inputTime;
    }

    public Matcher<View> fieldDescription() {
        Allure.step("Описание");
        return inputDescription;
    }

    public void changeStatus() {
        Allure.step("Нажимаем на переключатель для смены статуса публикации");
        switcher.perform(click());
        saveButton.perform(scrollTo(), click());
    }

    //Проверка текста ошибки
    public void errorTextMessage(String text) {
        Allure.step("Подтверждаем наличие сообщения об ошибке с текстом: " + text);
        onView(withText(text))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
        Allure.step("Отменяем изменения");
        cancelButton.perform(click());
        cancelButtonOk.perform(click());
    }



}
