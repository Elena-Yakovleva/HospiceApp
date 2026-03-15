package ru.iteco.fmhandroid.ui.test;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.page.AboutPage;
import ru.iteco.fmhandroid.ui.page.AuthPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;
import ru.iteco.fmhandroid.ui.page.OurMissionPage;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class OurMissionPageTest {
    AuthPage authPage = new AuthPage();
    MainPage mainPage = new MainPage();
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
        mainPage.mainPageOurMissionButton();
        ourMissionPage.ourMissionPageVisible();
    }

    @Test
    //Test Case - 20 "Переход на страницу Main через кнопку Menu со страницы OurMission"
    public void shouldOpenMainPageFromMenuButtonInOurMissionPage() {
        ourMissionPage.ourMissionPageMenuMainButton();
        mainPage.mainPageVisible();
    }

    @Test
    //Test Case - 21 "Переход на страницу News через кнопку Menu со страницы OurMission"
    public void shouldOpenNewsPageFromMenuButtonInOurMissionPage() {
        ourMissionPage.ourMissionPageMenuNewsButton();
        newsPage.newsPageVisible();
    }

    @Test
    //Test Case - 22 "Переход на страницу About, через кнопку Menu со страницы OurMission"
    public void shouldOpenAboutPageFromMenuButtonInOurMissionPage() {
        ourMissionPage.ourMissionPageMenuAboutButton();
        aboutPage.aboutPageVisible();
    }

    @Test
    //Test Case - 23 "Кнопка OurMission не активна на странице OurMission"
    public void shouldOurMissionButtonNotClickableFromOurMissionPage() {
        ourMissionPage.ourMissionPageOurMissionButton();
    }

    @Test
    //Test Case - 25 "Развернуть и свернуть цитату на странице OurMission"
    public void shouldExpandAndCollapseQuoteOurMission() {
        ourMissionPage.expandQuoteOurMission();
        ourMissionPage.collapseQuoteOurMission();

    }

}
