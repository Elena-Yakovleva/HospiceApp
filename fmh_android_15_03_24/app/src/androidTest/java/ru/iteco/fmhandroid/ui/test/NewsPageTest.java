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


@LargeTest
@RunWith(AndroidJUnit4.class)
public class NewsPageTest {

    MainPage mainPage = new MainPage();
    AuthPage authPage = new AuthPage();
    NewsPage newsPage = new NewsPage();
    AboutPage aboutPage = new AboutPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setup() {
        try {
            mainPage.mainPageLoad();
            mainPage.mainPageMenuNewsButon();
            newsPage.newsPageLoad();
            newsPage.newsPageVisible();
        } catch (Exception e) {
            authPage.authUser();
            mainPage.mainPageLoad();
            mainPage.mainPageMenuNewsButon();
            newsPage.newsPageLoad();
            newsPage.newsPageVisible();
        }
    }

    @Test
    // Test Case - 29 "Отсутствие перехода на страницу News через кнопку Menu"

    public void shouldNotNavigateToNewsFromMenu() {
        newsPage.newsPageMenuNewsButton();
    }

    @Test
    // Test Case - 30 "Переход на страницу Main через кнопку Menu"
    public void shouldOpenMainPageFromMenuButton(){
        newsPage.newsPageMenuMainButton();
        mainPage.mainPageVisible();
    }

    @Test
    // Test Case - 31 "Переход на страницу About, через кнопку Menu "
    public void shouldOpenAboutPageFromMenuButton(){
        newsPage.newsPageMenuAboutButton();
        aboutPage.aboutPageVisible();
    }


}
