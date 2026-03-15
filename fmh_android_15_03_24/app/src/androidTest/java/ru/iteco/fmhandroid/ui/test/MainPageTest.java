package ru.iteco.fmhandroid.ui.test;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.page.AboutPage;
import ru.iteco.fmhandroid.ui.page.AuthPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;
import ru.iteco.fmhandroid.ui.page.OurMissionPage;


@LargeTest
@RunWith(AndroidJUnit4.class)

public class MainPageTest {
    MainPage mainPage = new MainPage();
    AuthPage authPage = new AuthPage();
    NewsPage newsPage = new NewsPage();
    AboutPage aboutPage = new AboutPage();
    OurMissionPage ourMissionPage = new OurMissionPage();

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
    //Test Case - 13 "Отсутствие перехода на страницу Main через кнопку Menu с главной страницы"
    public void shouldNotNavigateToMainFromMenuFromMainPage() {
        mainPage.mainPageMenuMainButton();
    }

    @Test
    //Test Case - 14 "Переход на страницу News через кнопку Menu"
    public void shouldOpenNewsPageFromMenuButtonFromMainPage() {
        mainPage.mainPageMenuNewsButon();
        newsPage.newsPageVisible();
    }

    @Test
    //Test Case - 15 "Переход на страницу About, через кнопку Menu"
    public void shouldOpenAboutPageFromMenuButton() {
        mainPage.mainPageMenuAboutButon();
        aboutPage.aboutPageVisible();
    }

    @Test
    //Test Case - 16 "Переход на страницу с цитатами через кнопку OurMission с главной страницы."
    public void shouldOpenOurMissionPageFromMainPage() {
        mainPage.mainPageOurMissionButton();
        ourMissionPage.ourMissionPageVisible();
    }

    @Test
    //Test Case - 17 "Выход из приложения по кнопке LogOut."
    public void shouldMainPageLogOutFromMainPage() {
        mainPage.mainPageLogOutButon();
        authPage.authPageVisible();
    }

    @Test
    //Test Case - 18 "Скрыть и развернуть новости на главной странице"
    public void expandCollapseNewsMainPage() {
        mainPage.collapseNewsBlock();
        mainPage.ExpandNewsBlock();
    }

    @Test
    //Test Case - 19 "Переход на страницу News через кнопку All News"
    public void shouldGoToNewsPage() {
        mainPage.buttonAllNews();
        newsPage.newsPageVisible();
    }

}
