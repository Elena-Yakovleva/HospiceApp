package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.Utils;

public class MainPage {
    // Блок новости
    private final ViewInteraction mainPageBlockNews = onView(withText("News"));
    private final ViewInteraction expandNewsBlockButton = onView(withId(R.id.expand_material_button));
    private final ViewInteraction allNewsButton = onView(withId(R.id.all_news_text_view));

    public void mainPageLoad() {
        Allure.step("Ожидаем загрузку главной страницы Main");
        onView(isRoot()).perform(Utils.waitDisplayed(R.id.main_menu_image_button, 5000));
    }

    public void mainPageVisible() {
        Allure.step("Подтверждаем видимость блока новостей на главной странице");
        mainPageBlockNews.check(matches(isDisplayed()));
    }

    public void ExpandNewsBlock() {
        Allure.step("Нажимаем на кнопку Развернуть для блока новостей");
        expandNewsBlockButton.perform(click());
        Allure.step("Блок новостей развернут и доступна кнопка All News");
        allNewsButton.check(matches(isDisplayed()));
    }

    public void collapseNewsBlock() {
        Allure.step("Нажимаем на кнопку Свернуть для блока новостей");
        expandNewsBlockButton.perform(click());
        Allure.step("Блок новостей свернут");
        allNewsButton.check(matches(not(isDisplayed())));
    }

    public NewsPage buttonAllNews() {
        Allure.step("Подтверждаем видимость кнопки All News и нажимаем на нее");
        Utils.waitDisplayed(R.id.all_news_text_view, 500);
        allNewsButton.check(matches(isDisplayed()));
        allNewsButton.perform(click());
        return new NewsPage();
    }
}
