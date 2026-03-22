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

@Epic(value = "Страница News")
@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsPageTest {

    MainPage mainPage = new MainPage();
    AuthPage authPage = new AuthPage();
    NewsPage newsPage = new NewsPage();
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
            ;
        }
        appBarPage.menuNewsButton();
        newsPage.newsPageLoad();
        newsPage.newsPageVisible();
        newsPage.newsPageControlPanelButton();
        controlPanelPage.controlPanelVisible();
        createNewsPage.addNews(category, title, date, time, description);
        createNewsPage.saveAddNews();
        appBarPage.menuNewsButton();
    }

    @After
    public void after() {
        newsPage.newsPageControlPanelButton();
        editNews.deleteNewsButton(title);
    }


    @Test
    @DisplayName("Test Case - 34: Просмотр новостей на странице News")
    public void shouldExpandAndCollapseNews() {
        newsPage.selectNewsInNewsPage(title);
        newsPage.expandDescriptionNews(title, description);
        newsPage.selectNewsInNewsPage(title);
        newsPage.collapseDescriptionNews(title, description);
    }

    @Test
    @DisplayName("Test Case - 36: Фильтрация новостей на странице News по выбранной категории из доступных по умолчанию с выбранными датами «от» и «до»")
    public void shouldFilterNewsByDefaultCategoryWithSelectedDateRange() {
        newsPage.filterNewsPage(category, startDate, endDate);
        newsPage.clickSaveFilterButtonNews();
        newsPage.selectNewsInNewsPage(title);
    }

    @Test
    @DisplayName("Test Case - 37: Фильтрация новостей на странице News по выбранной категории без указания дат")
    public void shouldFilterNewsByDefaultCategoryWithNotSelectedDateRange() {
        newsPage.filterNewsPage(category, DataHelper.getEmptyDate(), DataHelper.getEmptyDate());
        newsPage.clickSaveFilterButtonNews();
        newsPage.selectNewsInNewsPage(title);
    }

    @Test
    @DisplayName("Test Case - 38: Фильтрация новостей по выбранной категории с только выбранной датой «от»")
    public void shouldFilterNewsByDefaultCategoryWithSelectedStartDate() {
        newsPage.filterNewsPage(category, startDate, DataHelper.getEmptyDate());
        newsPage.clickSaveFilterButtonNews();
        newsPage.errorTextMessageNews(DataHelper.wrongPeriodError());
        newsPage.clickCancelFilterButtonNews();
    }

    @Test
    @DisplayName("Test Case - 39: Фильтрация новостей по выбранной категории с только выбранной датой «до»")
    public void shouldFilterNewsByDefaultCategoryWithSelectedEndDate() {
        newsPage.filterNewsPage(category, DataHelper.getEmptyDate(), endDate);
        newsPage.clickSaveFilterButtonNews();
        newsPage.errorTextMessageNews(DataHelper.wrongPeriodError());
        newsPage.clickCancelFilterButtonNews();
    }

    @Test
    @DisplayName("Test Case - 40: Отмена внесенной информации в фильтре новостей по кнопке «Cancel»")
    public void shouldDiscardFilterChangesWhenCancelClicked() {
        newsPage.filterNewsPage(category, startDate, endDate);
        newsPage.clickCancelFilterButtonNews();
        newsPage.selectNewsInNewsPage(title);
    }

    @Test
    @DisplayName("Test Case - 41: Наличие уведомление об отсутствии новостей из выбранной категории")
    public void shouldEmptyCategoryNotificationExists() {
        appBarPage.menuNewsButton();
        newsPage.filterNewsPage("Благодарность", DataHelper.currentDate(), DataHelper.currentDate());
        newsPage.clickSaveFilterButtonNews();
        newsPage.errorMessageNotNews();

    }


}
