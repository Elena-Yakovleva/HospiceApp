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
import ru.iteco.fmhandroid.ui.page.AboutPage;
import ru.iteco.fmhandroid.ui.page.AppBarPage;
import ru.iteco.fmhandroid.ui.page.AuthPage;
import ru.iteco.fmhandroid.ui.page.ControlPanelPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;
import ru.iteco.fmhandroid.ui.page.OurMissionPage;

@Epic(value = "Навигационная панель")
@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AppBarPageTest {
    MainPage mainPage = new MainPage();
    AuthPage authPage = new AuthPage();
    NewsPage newsPage = new NewsPage();
    AboutPage aboutPage = new AboutPage();
    OurMissionPage ourMissionPage = new OurMissionPage();
    ControlPanelPage controlPanelPage = new ControlPanelPage();
    AppBarPage appBarPage = new AppBarPage();

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
    @DisplayName("Test Case - 13: Отсутствие перехода на страницу Main через кнопку Menu с главной страницы")
    public void shouldNotNavigateToMainFromMenuFromMainPage() {
        appBarPage.notClickableMenuMainButton();
    }

    @Test
    @DisplayName("Test Case - 14: Переход на страницу News через кнопку Menu")
    public void shouldOpenNewsPageFromMenuButtonFromMainPage() {
        appBarPage.menuNewsButton();
        newsPage.newsPageVisible();
    }

    @Test
    @DisplayName("Test Case - 15: Переход на страницу About, через кнопку Menu")
    public void shouldOpenAboutPageFromMenuButton() {
        appBarPage.menuAboutButton();
        aboutPage.aboutPageVisible();
    }

    @Test
    @DisplayName("Test Case - 16: Переход на страницу с цитатами через кнопку OurMission с главной страницы.")
    public void shouldOpenOurMissionPageFromMainPage() {
        appBarPage.ourMissionButton();
        ourMissionPage.ourMissionPageVisible();
    }

    @Test
    @DisplayName("Test Case - 17: Выход из приложения по кнопке LogOut.")
    public void shouldMainPageLogOutFromMainPage() {
        appBarPage.logOutButton();
        authPage.authPageVisible();
    }

    @Test
    @DisplayName("Test Case - 20: Переход на страницу Main через кнопку Menu со страницы OurMission")
    public void shouldOpenMainPageFromMenuButtonInOurMissionPage() {
        appBarPage.ourMissionButton();
        ourMissionPage.ourMissionPageVisible();
        appBarPage.menuMainButton();
        mainPage.mainPageVisible();
    }

    @Test
    @DisplayName("Test Case - 21: Переход на страницу News через кнопку Menu со страницы OurMission")
    public void shouldOpenNewsPageFromMenuButtonInOurMissionPage() {
        appBarPage.ourMissionButton();
        ourMissionPage.ourMissionPageVisible();
        appBarPage.menuNewsButton();
        newsPage.newsPageVisible();
    }

    @Test
    @DisplayName("Test Case - 22: Переход на страницу About, через кнопку Menu со страницы OurMission")
    public void shouldOpenAboutPageFromMenuButtonInOurMissionPage() {
        appBarPage.ourMissionButton();
        ourMissionPage.ourMissionPageVisible();
        appBarPage.menuAboutButton();
        aboutPage.aboutPageVisible();
    }

    @Test
    @DisplayName("Test Case - 23: Кнопка OurMission не активна на странице OurMission")
    public void shouldOurMissionButtonNotClickableFromOurMissionPage() {
        appBarPage.ourMissionButton();
        ourMissionPage.ourMissionPageVisible();
        appBarPage.notClickableOurMissionButton();
    }

    @Test
    @DisplayName("Test Case - 24: Выход из приложения по кнопке LogOut со страницы OurMission")
    public void shouldOurMissionPageLogOutFromOurMissionPage() {
        appBarPage.ourMissionButton();
        ourMissionPage.ourMissionPageVisible();
        appBarPage.logOutButton();
        authPage.authPageVisible();
    }

    @Test
    @DisplayName("Test Case - 29: Отсутствие кликабельности кнопки News в Menu на странице новостей")

    public void shouldNotNavigateToNewsFromMenuFromNewsPage() {
        appBarPage.menuNewsButton();
        newsPage.newsPageVisible();
        appBarPage.notClickableMenuNewsButton();
    }

    @Test
    @DisplayName("Test Case - 30: Переход на страницу Main через кнопку Menu со страницы новостей")
    public void shouldOpenMainPageFromMenuButtonFromNewsPage() {
        appBarPage.menuNewsButton();
        newsPage.newsPageVisible();
        appBarPage.menuMainButton();
        mainPage.mainPageVisible();
    }

    @Test
    @DisplayName("Test Case - 31: Переход на страницу About, через кнопку Menu со страницы новостей")
    public void shouldOpenAboutPageFromMenuButtonFromNewsPage() {
        appBarPage.menuNewsButton();
        newsPage.newsPageVisible();
        appBarPage.menuAboutButton();
        aboutPage.aboutPageVisible();
    }

    @Test
    @DisplayName("Test Case - 32: Переход на страницу OurMission, через кнопку OurMission со страницы новостей")
    public void shouldOpenOurMissionPageFromNewsPage() {
        appBarPage.menuNewsButton();
        newsPage.newsPageVisible();
        appBarPage.ourMissionButton();
        ourMissionPage.ourMissionPageVisible();
    }

    @Test
    @DisplayName("Test Case - 33: Выход из личного кабинета со страницы News по кнопке LogOut")
    public void shouldLogOutFromNewsPage() {
        appBarPage.menuNewsButton();
        newsPage.newsPageLoad();
        newsPage.newsPageVisible();
        appBarPage.logOutButton();
        authPage.authPageVisible();
    }

    @Test
    @DisplayName("Test Case - 42: Переход в контрольную панель по кнопке Control panel")
    public void shouldOpenControlPanelNewsPage() {
        appBarPage.menuNewsButton();
        newsPage.newsPageControlPanelButton();
        controlPanelPage.controlPanelVisible();
    }

}
