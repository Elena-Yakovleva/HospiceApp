package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.data.Utils;

public class OurMissionPage {
    private final ViewInteraction card = onView(withId(R.id.our_mission_item_list_recycler_view));

    private final int position = DataHelper.getNumberCard();

    // Блок цитат
    private final ViewInteraction title = onView(withText("Love is all"));

    // Проверка загрузки страницы
    public void ourMissionPageLoad() {
        Allure.step("Ожидаем загрузку страницы Our Mission");
        onView(isRoot()).perform(Utils.waitDisplayed(R.id.our_mission_title_text_view, 5000));
    }

    // Проверка видимости элементов
    public void ourMissionPageVisible() {
        Allure.step("Подтверждаем видимость титула страницы");
        title.check(matches(isDisplayed()));
    }

    // Просмотр цитаты
    public void expandQuoteOurMission() {
        Allure.step("Нажимаем на цитату");
        card.perform(actionOnItemAtPosition(position, click()));
        Allure.step("Подтверждаем видимость цитаты на странице: " + DataHelper.getTitle(position) + "...");
        onView(allOf(
                withId(R.id.our_mission_item_title_text_view),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                withText(containsString(DataHelper.getTitle(position)))
        )).check(matches(isDisplayed()));
        Allure.step("Подтверждаем видимость комментария к цитате: " + DataHelper.getQuote(position) + "...");
        onView(allOf(
                withId(R.id.our_mission_item_description_text_view),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                withText(containsString(DataHelper.getQuote(position)))
        )).check(matches(isDisplayed()));
    }

    public void collapseQuoteOurMission() {
        Allure.step("Повторно нажимаем на цитату");
        card.perform(actionOnItemAtPosition(position, click()));
        Allure.step("Подтверждаем видимости цитаты на странице: " + DataHelper.getTitle(position) + "...");
        onView(allOf(
                withId(R.id.our_mission_item_title_text_view),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                withText(containsString(DataHelper.getTitle(position)))
        )).check(matches(isDisplayed()));
        Allure.step("Подтверждаем отсутствие комментария у цитаты: " + DataHelper.getQuote(position) + "...");
        onView(allOf(
                withId(R.id.our_mission_item_description_text_view),
                withEffectiveVisibility(ViewMatchers.Visibility.GONE),
                withText(containsString(DataHelper.getQuote(position)))
        )).check(matches(not(isDisplayed())));
    }
}
