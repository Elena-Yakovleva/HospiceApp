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
import ru.iteco.fmhandroid.ui.page.ControlPanelPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;
import ru.iteco.fmhandroid.ui.page.OurMissionPage;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AppBarPageTest {
    MainPage mainPage = new MainPage();
    AuthPage authPage = new AuthPage();
    NewsPage newsPage = new NewsPage();
    OurMissionPage ourMissionPage = new OurMissionPage();
    ControlPanelPage controlPanelPage = new ControlPanelPage();

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
    //Test Case - 17 "Выход из приложения по кнопке LogOut."
    public void shouldMainPageLogOutFromMainPage() {
        mainPage.mainPageLogOutButon();
        authPage.authPageVisible();
    }

    @Test
    //Test Case - 24 "Выход из приложения по кнопке LogOut со страницы OurMission"
    public void shouldOurMissionPageLogOutFromOurMissionPage() {
        mainPage.mainPageOurMissionButton();
        ourMissionPage.ourMissionPageVisible();
        ourMissionPage.ourMissionPageLogOutButton();
        authPage.authPageVisible();
    }

    @Test
    // Test Case - 33 "Выход из личного кабинета со страницы News по кнопке LogOut"
    public void shouldLogOutFromNewsPage(){
        mainPage.mainPageMenuNewsButon();
        newsPage.newsPageLoad();
        newsPage.newsPageVisible();
        newsPage.newsPageLogOutButton();
        authPage.authPageVisible();
    }

    @Test
    // Test Case - 42 "Переход в контрольную панель по кнопке Control panel"
    public void shouldOpenControlPanelNewsPage() {
        mainPage.mainPageMenuNewsButon();
        newsPage.newsPageControlPanelButton();
        controlPanelPage.controlPanelVisible();
    }

}
