package ru.iteco.fmhandroid.ui.test;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.page.AuthPage;
import ru.iteco.fmhandroid.ui.page.ControlPanelPage;
import ru.iteco.fmhandroid.ui.page.EditNewsPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;
@LargeTest
@RunWith(AndroidJUnit4.class)
public class EditNewsTest {
    MainPage mainPage = new MainPage();
    AuthPage authPage = new AuthPage();
    NewsPage newsPage = new NewsPage();
    ControlPanelPage controlPanelPage = new ControlPanelPage();
    EditNewsPage editNews = new EditNewsPage();
    private View decorView;


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
        //newsPage.newsPageVisible();
        newsPage.newsPageControlPanelButton();
        controlPanelPage.controlPanelLoad();
        //controlPanelPage.controlPanelVisible();
    }

    @Test
    //Test Case  "
    public void shouldDeleteNews() {
        int position = DataHelper.getNumberCard();
        controlPanelPage.addNews(DataHelper.getDefaultCategory(position),
                DataHelper.getOwnTitle(),
                DataHelper.currentDate(),
                DataHelper.currentTime(),
                DataHelper.getDescription());
        controlPanelPage.saveAddNews();
        editNews.deleteNews(DataHelper.getOwnTitle());
    }
}
