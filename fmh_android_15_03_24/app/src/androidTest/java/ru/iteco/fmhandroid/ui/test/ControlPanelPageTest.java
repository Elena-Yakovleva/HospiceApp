package ru.iteco.fmhandroid.ui.test;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.page.AppBarPage;
import ru.iteco.fmhandroid.ui.page.AuthPage;
import ru.iteco.fmhandroid.ui.page.ControlPanelPage;
import ru.iteco.fmhandroid.ui.page.CreateNewsPage;
import ru.iteco.fmhandroid.ui.page.EditNewsPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;
@Epic(value = "Страница Control Panel")
@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class ControlPanelPageTest {

    MainPage mainPage = new MainPage();
    NewsPage newsPage = new NewsPage();
    AuthPage authPage = new AuthPage();
    AppBarPage appBarPage = new AppBarPage();
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

        appBarPage.menuNewsButton();
        newsPage.newsPageLoad();
        newsPage.newsPageControlPanelButton();
        controlPanelPage.controlPanelVisible();
        createNewsPage.addNews(category, title, date, time, description);
        createNewsPage.saveAddNews();
    }

    @After
    public void after() {
        editNews.deleteNewsButton(title);
    }

    @Test
    @DisplayName("Test Case - 43: Просмотр новостей на странице Control panel")
    public void shouldExpandAndCollapseNewsInControlPanel() {
        controlPanelPage.selectNews(title);
        controlPanelPage.expandDescription(title, description);
        controlPanelPage.selectNews(title);
        controlPanelPage.collapseDescription(title, description);

    }

    @Test
    @DisplayName("Test Case - 45: Фильтрация новостей по выбранной категории с выбранными датами «от» и «до»")
    public void shouldFilterNewsControlPanelByDefaultCategoryWithSelectedDateRange() {
        controlPanelPage.filterNews(category, startDate, endDate);
        controlPanelPage.clickSaveFilterButton();
        controlPanelPage.selectNews(title);
    }

    @Test
    @DisplayName("Test Case - 46: Фильтрация новостей по выбранной категории без указания дат")
    public void shouldFilterNewsControlPanelByDefaultCategoryWithNotSelectedDateRange() {
        controlPanelPage.filterNews(category, DataHelper.getEmptyDate(), DataHelper.getEmptyDate());
        controlPanelPage.clickSaveFilterButton();
        controlPanelPage.selectNews(title);
    }

    @Test
    @DisplayName("Test Case - 47: Фильтрация новостей по выбранной категории с только выбранной датой «от»")
    public void shouldFilterNewsControlPanelByDefaultCategoryWithSelectedStartDate() {
        controlPanelPage.filterNews(category, startDate, DataHelper.getEmptyDate());
        controlPanelPage.clickSaveFilterButton();
        controlPanelPage.errorTextMessage(DataHelper.wrongPeriodError());
        controlPanelPage.clickCancelFilterButton();
    }

    @Test
    @DisplayName("Test Case - 48: Фильтрация новостей по выбранной категории с только выбранной датой «до»")
    public void shouldFilterNewsControlPanelByDefaultCategoryWithSelectedEndDate() {
        controlPanelPage.filterNews(category, DataHelper.getEmptyDate(), endDate);
        controlPanelPage.clickSaveFilterButton();
        controlPanelPage.errorTextMessage(DataHelper.wrongPeriodError());
        controlPanelPage.clickCancelFilterButton();
    }

    @Test
    @DisplayName("Test Case - 49: Фильтрация новостей по выбранной категории с заполненным чек-боксом «Active»")
    public void shouldFilterNewsControlPanelByDefaultCategoryWithActiveStatus() {
        controlPanelPage.filterNews(category, startDate, endDate);
        controlPanelPage.clickCheckBoxInActive();
        controlPanelPage.clickSaveFilterButton();
        controlPanelPage.checkStatus(title, "ACTIVE");
    }

    @Test
    @DisplayName("Test Case - 50: Фильтрация новостей по выбранной категории с заполненным чек-боксом «Not_Active»")
    public void shouldFilterNewsControlPanelByDefaultCategoryWithNotActiveStatus() {
        editNews.editNewsButton(title);
        editNews.changeStatus();
        controlPanelPage.filterNews(category, startDate, endDate);
        controlPanelPage.clickCheckBoxActive();
        controlPanelPage.clickSaveFilterButton();
        controlPanelPage.checkStatus(title, "NOT ACTIVE");
    }

    @Test
    @DisplayName("Test Case - 51 :Фильтрация новостей без заполненных чек-боксов")
    public void shouldFilterNewsControlPanelByDefaultCategoryWithNotCheckBox() {
        controlPanelPage.filterNews(category, startDate, endDate);
        controlPanelPage.clickCheckBoxInActive();
        controlPanelPage.clickCheckBoxInActive();
        controlPanelPage.clickSaveFilterButton();
        controlPanelPage.errorMessage();
    }


    @Test
    @DisplayName("Test Case - 52: Отмена внесенной информации в фильтре по кнопке «Cancel»")
    public void shouldDiscardFilterChangesWhenCancelClicked() {
        controlPanelPage.filterNews(category, startDate, endDate);
        controlPanelPage.clickCancelFilterButton();
    }


}
