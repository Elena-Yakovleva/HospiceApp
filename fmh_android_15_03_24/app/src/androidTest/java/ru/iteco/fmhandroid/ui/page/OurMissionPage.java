package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.data.Utils;

public class OurMissionPage {

    AppBarPage appBarPage = new AppBarPage();
    // Элементы страницы
    private final ViewInteraction ourMissionButton = onView(allOf(withId(R.id.our_mission_image_button), isDisplayed()));
    private final ViewInteraction card = onView(withId(R.id.our_mission_item_list_recycler_view));

    private final int position = DataHelper.getNumberCard();


    // Блок цитат
    private final ViewInteraction title = onView(withText("Love is all"));


    // Проверка загрузки страницы
    public void ourMissionPageLoad() {
        onView(isRoot()).perform(Utils.waitDisplayed(R.id.our_mission_title_text_view, 5000));
    }

    // Проверка видимости элементов
    public void ourMissionPageVisible() {

        title.check(matches(isDisplayed()));
    }

    // Кнопки меню
    public MainPage ourMissionPageMenuMainButton() {
        appBarPage.MenuMainButton();
        return new MainPage();
    }
    public NewsPage ourMissionPageMenuNewsButton() {
        appBarPage.MenuNewsButton();
        return new NewsPage();
    }
    public AboutPage ourMissionPageMenuAboutButton() {
        appBarPage.MenuAboutButton();
        return new AboutPage();
    }

    // Цитаты
    public  void ourMissionPageOurMissionButton() {
        ourMissionButton.check(matches(isDisplayed()));
        ourMissionButton.check(matches(not(isClickable())));
    }

    // Выход из приложения
    public AuthPage ourMissionPageLogOutButton() {
        appBarPage.LogOutButton();
        return new AuthPage();
    }

    // Просмотр цитаты
    public void expandQuoteOurMission() {
        card.perform(actionOnItemAtPosition(position, click()));
        // Проверяем видимость элементов
        onView(allOf(
                withId(R.id.our_mission_item_title_text_view),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                withText(containsString(DataHelper.getTitle(position)))
        )).check(matches(isDisplayed()));
        onView(allOf(
                withId(R.id.our_mission_item_description_text_view),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                withText(containsString(DataHelper.getQuote(position)))
        )).check(matches(isDisplayed()));
    }
    public void collapseQuoteOurMission() {
        card.perform(actionOnItemAtPosition(position, click()));
        // Проверяем невидимость элемента при свернутой цитате
        onView(allOf(
                withId(R.id.our_mission_item_title_text_view),
                withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                withText(containsString(DataHelper.getTitle(position)))
        )).check(matches(isDisplayed()));
        onView(allOf(
                withId(R.id.our_mission_item_description_text_view),
                withEffectiveVisibility(ViewMatchers.Visibility.GONE),
                withText(containsString(DataHelper.getQuote(position)))
        )).check(matches(not(isDisplayed())));
    }
}
