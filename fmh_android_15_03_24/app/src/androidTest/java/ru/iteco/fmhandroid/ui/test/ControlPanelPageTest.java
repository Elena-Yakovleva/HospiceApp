package ru.iteco.fmhandroid.ui.test;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.page.AboutPage;
import ru.iteco.fmhandroid.ui.page.AuthPage;
import ru.iteco.fmhandroid.ui.page.ControlPanelPage;
import ru.iteco.fmhandroid.ui.page.CreateNewsPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;
import ru.iteco.fmhandroid.ui.page.OurMissionPage;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ControlPanelPageTest {

    MainPage mainPage = new MainPage();
    NewsPage newsPage = new NewsPage();
    AuthPage authPage = new AuthPage();
    CreateNewsPage createNewsPage = new CreateNewsPage();
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

        mainPage.mainPageMenuNewsButon();
        newsPage.newsPageLoad();

    }

    @Test
    // Test Case - 42 "Переход в контрольную панель по кнопке Control panel"
    public void shouldOpenControlPanelNewsPage() {
        newsPage.newsPageControlPanelButton();
        controlPanelPage.controlPanelVisible();
    }


}
