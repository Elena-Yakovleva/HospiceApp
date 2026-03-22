package ru.iteco.fmhandroid.ui.test;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.page.AuthPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;

@Epic(value = "Главная страница - Main")
@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class MainPageTest {
    MainPage mainPage = new MainPage();
    AuthPage authPage = new AuthPage();
    NewsPage newsPage = new NewsPage();


    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setup() {
        try {
            mainPage.mainPageLoad();
        } catch (Exception e) {
            authPage.authUser();
            mainPage.mainPageLoad();
        }
        mainPage.mainPageVisible();
    }


    @Test
    @DisplayName("Test Case - 18: Скрыть и развернуть новости на главной странице")
    public void expandCollapseNewsMainPage() {
        mainPage.collapseNewsBlock();
        mainPage.ExpandNewsBlock();
    }

    @Test
    @DisplayName("Test Case - 19: Переход на страницу News через кнопку All News")
    public void shouldGoToNewsPage() {
        mainPage.buttonAllNews();
        newsPage.newsPageVisible();
    }

}
