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
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;
import ru.iteco.fmhandroid.ui.page.CreateNewsPage;
import ru.iteco.fmhandroid.ui.page.OurMissionPage;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class NewsPageTest {

    MainPage mainPage = new MainPage();
    AuthPage authPage = new AuthPage();
    NewsPage newsPage = new NewsPage();
    AboutPage aboutPage = new AboutPage();
    OurMissionPage ourMissionPage = new OurMissionPage();
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
            mainPage.mainPageLoad();;
        }
        mainPage.mainPageMenuNewsButon();
        newsPage.newsPageLoad();
        newsPage.newsPageVisible();
    }

    @Test
    // Test Case - 29 "Отсутствие перехода на страницу News через кнопку Menu"

    public void shouldNotNavigateToNewsFromMenuFromNewsPage() {
        newsPage.newsPageMenuNewsButton();
    }

    @Test
    // Test Case - 30 "Переход на страницу Main через кнопку Menu"
    public void shouldOpenMainPageFromMenuButtonFromNewsPage(){
        newsPage.newsPageMenuMainButton();
        mainPage.mainPageVisible();
    }

    @Test
    // Test Case - 31 "Переход на страницу About, через кнопку Menu "
    public void shouldOpenAboutPageFromMenuButtonFromNewsPage(){
        newsPage.newsPageMenuAboutButton();
        aboutPage.aboutPageVisible();
    }
    @Test
    // Test Case - 32 "Переход на страницу OurMission, через кнопку OurMission "
    public void shouldOpenOurMissionPageFromNewsPage(){
        newsPage.newsPageOurMissionButton();
        ourMissionPage.ourMissionPageVisible();
    }

    @Test
    // Test Case - 34 "Просмотр новости на странице"
    public void shouldExpandAndCollapseNewsInNewsPage() {
        int position = DataHelper.getNumberCard();
        String title = DataHelper.getOwnTitle();
        String description = DataHelper.getDescription();

        newsPage.newsPageControlPanelButton();
        controlPanelPage.controlPanelVisible();
        createNewsPage.addNews(DataHelper.getDefaultCategory(position),
                title,
                DataHelper.currentDate(),
                DataHelper.currentTime(),
                description);
        createNewsPage.saveAddNews();
        newsPage.selectNews(title);
        newsPage.expandDescription(title, description);
        newsPage.selectNews(title);
        newsPage.collapseDescription(title,description);
    }
    @Test
    // Test Case - 36 "Фильтрация новостей по выбранной категории с выбранными датами «от» и «до»"
    public void shouldFilterNewsByDefaultCategoryWithSelectedDateRange() {
        int position = DataHelper.getNumberCard();
        String category = DataHelper.getDefaultCategory(position);
        String title = DataHelper.getOwnTitle();
        String startDate = DataHelper.currentDate();
        String endDate = DataHelper.currentDate();

        newsPage.newsPageControlPanelButton();
        controlPanelPage.controlPanelVisible();
        createNewsPage.addNews(category,
                title,
                DataHelper.currentDate(),
                DataHelper.currentTime(),
                DataHelper.getDescription());
        createNewsPage.saveAddNews();
        newsPage.filterNews(category,startDate,endDate);
        newsPage.clickSaveFilterButton();
        newsPage.selectNews(title);
    }
    @Test
    // Test Case - 37 "Фильтрация новостей по выбранной категории без указания дат"
    public void shouldFilterNewsByDefaultCategoryWithNotSelectedDateRange() {
        int position = DataHelper.getNumberCard();
        String category = DataHelper.getDefaultCategory(position);
        String title = DataHelper.getOwnTitle();
        String startDate = DataHelper.getEmptyDate();
        String endDate = DataHelper.getEmptyDate();

        newsPage.newsPageControlPanelButton();
        controlPanelPage.controlPanelVisible();
        createNewsPage.addNews(category,
                title,
                DataHelper.currentDate(),
                DataHelper.currentTime(),
                DataHelper.getDescription());
        createNewsPage.saveAddNews();
        newsPage.filterNews(category,startDate,endDate);
        newsPage.clickSaveFilterButton();
        newsPage.selectNews(title);
    }
    @Test
    // Test Case - 38 "Фильтрация новостей по выбранной категории с только выбранной датой «от»"
    public void shouldFilterNewsByDefaultCategoryWithSelectedStartDate() {
        int position = DataHelper.getNumberCard();
        String category = DataHelper.getDefaultCategory(position);
        String title = DataHelper.getOwnTitle();
        String startDate = DataHelper.currentDate();
        String endDate = DataHelper.getEmptyDate();

        newsPage.newsPageControlPanelButton();
        controlPanelPage.controlPanelVisible();
        createNewsPage.addNews(category,
                title,
                DataHelper.currentDate(),
                DataHelper.currentTime(),
                DataHelper.getDescription());
        createNewsPage.saveAddNews();
        newsPage.filterNews(category,startDate,endDate);
        newsPage.clickSaveFilterButton();
        newsPage.errorTextMessage(DataHelper.wrondPeriodError());

    }
    @Test
    // Test Case - 39 "Фильтрация новостей по выбранной категории с только выбранной датой «до»"
    public void shouldFilterNewsByDefaultCategoryWithSelectedEndDate() {
        int position = DataHelper.getNumberCard();
        String category = DataHelper.getDefaultCategory(position);
        String title = DataHelper.getOwnTitle();
        String startDate = DataHelper.getEmptyDate();
        String endDate = DataHelper.currentDate();

        newsPage.newsPageControlPanelButton();
        controlPanelPage.controlPanelVisible();
        createNewsPage.addNews(category,
                title,
                DataHelper.currentDate(),
                DataHelper.currentTime(),
                DataHelper.getDescription());
        createNewsPage.saveAddNews();
        newsPage.filterNews(category,startDate,endDate);
        newsPage.clickSaveFilterButton();
        newsPage.errorTextMessage(DataHelper.wrondPeriodError());

    }
    @Test
    // Test Case - 40 "Отмена внесенной информации в фильтре по кнопке «Cancel»"
    public void shouldDiscardFilterChangesWhenCancelClicked() {
        int position = DataHelper.getNumberCard();
        String category = DataHelper.getDefaultCategory(position);
        String title = DataHelper.getOwnTitle();
        String startDate = DataHelper.currentDate();
        String endDate = DataHelper.currentDate();

        newsPage.newsPageControlPanelButton();
        controlPanelPage.controlPanelVisible();
        createNewsPage.addNews(category,
                title,
                DataHelper.currentDate(),
                DataHelper.currentTime(),
                DataHelper.getDescription());
        createNewsPage.saveAddNews();
        newsPage.filterNews(category, startDate, endDate);
        newsPage.clickCancelFilterButton();

    }




}
