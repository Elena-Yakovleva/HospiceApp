package ru.iteco.fmhandroid.ui.test;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.page.AuthPage;
import ru.iteco.fmhandroid.ui.page.ControlPanelPage;
import ru.iteco.fmhandroid.ui.page.CreateNewsPage;
import ru.iteco.fmhandroid.ui.page.EditNewsPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ControlPanelPageTest {

    MainPage mainPage = new MainPage();
    NewsPage newsPage = new NewsPage();
    AuthPage authPage = new AuthPage();
    CreateNewsPage createNewsPage = new CreateNewsPage();
    ControlPanelPage controlPanelPage = new ControlPanelPage();
    EditNewsPage editNews = new EditNewsPage();
    int position = DataHelper.getNumberCard();
    String category = DataHelper.getDefaultCategory(position);
    String title = DataHelper.getOwnTitle();
    String date = DataHelper.currentDate();
    String time = DataHelper.currentTime();
    String description = DataHelper.getDescription();
    String startDate = DataHelper.currentDate();
    String endDate = DataHelper.currentDate();

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

    @After
    public void after() {
        editNews.deleteNewsButton(title);
    }

    @Test
    // Test Case - 43 "Просмотр новостей на странице Control panel"
    public void shouldExpandAndCollapseNewsInControlPanel() {
        newsPage.newsPageControlPanelButton();
        controlPanelPage.controlPanelVisible();
        createNewsPage.addNews(category, title, date, time, description);
        createNewsPage.saveAddNews();
        controlPanelPage.selectNews(title);
        controlPanelPage.expandDescription(title, description);
        controlPanelPage.selectNews(title);
        controlPanelPage.collapseDescription(title, description);

    }

    @Test
    // Test Case - 45 "Фильтрация новостей по выбранной категории с выбранными датами «от» и «до»"
    public void shouldFilterNewsControlPanelByDefaultCategoryWithSelectedDateRange() {
        newsPage.newsPageControlPanelButton();
        controlPanelPage.controlPanelVisible();
        createNewsPage.addNews(category, title, date, time, description);
        createNewsPage.saveAddNews();
        controlPanelPage.filterNews(category, startDate, endDate);
        controlPanelPage.clickSaveFilterButton();
        controlPanelPage.selectNews(title);
    }

    @Test
    // Test Case - 46 "Фильтрация новостей по выбранной категории без указания дат"
    public void shouldFilterNewsControlPanelByDefaultCategoryWithNotSelectedDateRange() {
        newsPage.newsPageControlPanelButton();
        controlPanelPage.controlPanelVisible();
        createNewsPage.addNews(category, title, date, time, description);
        createNewsPage.saveAddNews();
        controlPanelPage.filterNews(category, DataHelper.getEmptyDate(), DataHelper.getEmptyDate());
        controlPanelPage.clickSaveFilterButton();
        controlPanelPage.selectNews(title);
    }

    @Test
    // Test Case - 47 "Фильтрация новостей по выбранной категории с только выбранной датой «от»"
    public void shouldFilterNewsControlPanelByDefaultCategoryWithSelectedStartDate() {
        newsPage.newsPageControlPanelButton();
        controlPanelPage.controlPanelVisible();
        createNewsPage.addNews(category, title, date, time, description);
        createNewsPage.saveAddNews();
        controlPanelPage.filterNews(category, startDate, DataHelper.getEmptyDate());
        controlPanelPage.clickSaveFilterButton();
        controlPanelPage.errorTextMessage(DataHelper.wrondPeriodError());
        controlPanelPage.clickCancelFilterButton();
    }

    @Test
    // Test Case - 48 "Фильтрация новостей по выбранной категории с только выбранной датой «до»"
    public void shouldFilterNewsControlPanelByDefaultCategoryWithSelectedEndDate() {
        newsPage.newsPageControlPanelButton();
        controlPanelPage.controlPanelVisible();
        createNewsPage.addNews(category, title, date, time, description);
        createNewsPage.saveAddNews();
        controlPanelPage.filterNews(category, DataHelper.getEmptyDate(), endDate);
        controlPanelPage.clickSaveFilterButton();
        controlPanelPage.errorTextMessage(DataHelper.wrondPeriodError());
        controlPanelPage.clickCancelFilterButton();
    }

    @Test
    // Test Case - 49 "Фильтрация новостей по выбранной категории с заполненным чек-боксом «Active»"
    public void shouldFilterNewsControlPanelByDefaultCategoryWithActiveStatus() {
        newsPage.newsPageControlPanelButton();
        controlPanelPage.controlPanelVisible();
        createNewsPage.addNews(category, title, date, time, description);
        createNewsPage.saveAddNews();
        controlPanelPage.filterNews(category, startDate, endDate);
        controlPanelPage.clickCheckBoxInActive();
        controlPanelPage.clickSaveFilterButton();
        controlPanelPage.checkStatus(title, "ACTIVE");
    }

    @Test
    // Test Case - 50 "Фильтрация новостей по выбранной категории с заполненным чек-боксом «Not_Active»"
    public void shouldFilterNewsControlPanelByDefaultCategoryWithNotActiveStatus() {
        newsPage.newsPageControlPanelButton();
        controlPanelPage.controlPanelVisible();
        createNewsPage.addNews(category, title, date, time, description);
        createNewsPage.saveAddNews();
        editNews.editNewsButton(title);
        editNews.changeStatus();
        controlPanelPage.filterNews(category, startDate, endDate);
        controlPanelPage.clickCheckBoxActive();
        controlPanelPage.clickSaveFilterButton();
        controlPanelPage.checkStatus(title, "NOT ACTIVE");
    }

    @Test
    // Test Case - 51 "Фильтрация новостей без заполненных чек-боксов"
    public void shouldFilterNewsControlPanelByDefaultCategoryWithNotCheckBox() {
        newsPage.newsPageControlPanelButton();
        controlPanelPage.controlPanelVisible();
        createNewsPage.addNews(category, title, date, time, description);
        createNewsPage.saveAddNews();
        controlPanelPage.filterNews(category, startDate, endDate);
        controlPanelPage.clickCheckBoxInActive();
        controlPanelPage.clickCheckBoxInActive();
        controlPanelPage.clickSaveFilterButton();
        controlPanelPage.message();
    }


    @Test
    // Test Case - 52 "Отмена внесенной информации в фильтре по кнопке «Cancel»"
    public void shouldDiscardFilterChangesWhenCancelClicked() {
        newsPage.newsPageControlPanelButton();
        controlPanelPage.controlPanelVisible();
        createNewsPage.addNews(category, title, date, time, description);
        createNewsPage.saveAddNews();
        controlPanelPage.filterNews(category, startDate, endDate);
        controlPanelPage.clickCancelFilterButton();
    }


}
