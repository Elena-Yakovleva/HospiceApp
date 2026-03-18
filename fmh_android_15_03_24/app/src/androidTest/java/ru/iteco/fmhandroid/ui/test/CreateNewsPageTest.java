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
import ru.iteco.fmhandroid.ui.page.CreateNewsPage;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class CreateNewsPageTest {
    MainPage mainPage = new MainPage();
    AuthPage authPage = new AuthPage();
    NewsPage newsPage = new NewsPage();
    CreateNewsPage createNewsPage = new CreateNewsPage();
    ControlPanelPage controlPanelPage = new ControlPanelPage();
    EditNewsPage editNews = new EditNewsPage();
    private View decorView;
    int position = DataHelper.getNumberCard();
    String category = DataHelper.getDefaultCategory(position);
    String title = DataHelper.getOwnTitle();
    String date = DataHelper.currentDate();
    String time = DataHelper.currentTime();
    String description = DataHelper.getDescription();

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
        newsPage.newsPageControlPanelButton();
        createNewsPage.controlPanelLoad();
    }

    @Test
    //Test Case 53 "Создание новости с полями заполненными валидными данными"
    public void shouldAddNews() {
        createNewsPage.addNews(category, title, date, time, description);
        createNewsPage.saveAddNews();
        controlPanelPage.selectNews(title);
        editNews.deleteNewsButton(title);
    }

    @Test
    //Test Case 54 "Отказ в создании новости с пустыми полями"
    public void shouldNotCreateNewsWithEmptyFields() {
        createNewsPage.addNews(DataHelper.getEmptyCategory(),
                DataHelper.getEmptyTitle(),
                DataHelper.getEmptyDate(),
                DataHelper.getEmptyTime(),
                DataHelper.getEmptyDescription());
        createNewsPage.saveAddNews();
        createNewsPage.errorTextMessageCreateNews(DataHelper.emptyFieldsAddNewsError());
    }

    @Test
    //Test Case 55 "Отказ в создании новости без выбора категории"
    public void shouldNotCreateNewsWithEmptyCategory() {
        createNewsPage.addNews(DataHelper.getEmptyCategory(), title, date, time, description);
        createNewsPage.saveAddNews();
        createNewsPage.errorTextMessageCreateNews(DataHelper.emptyFieldsAddNewsError());
    }

    @Test
    //Test Case 56 "Отказ в создании новости со своей категорией.."
    public void shouldNotCreateNewsWithOwmCategory() {
        createNewsPage.addNews(DataHelper.getOwnCategory("My category"), title, date, time, description);
        createNewsPage.saveAddNews();
        createNewsPage.errorTextMessageCreateNews(DataHelper.savingFailedError());
    }

    @Test
    //Test Case 57 "Отказ в создание новости без титула"
    public void shouldNotCreateNewsWithEmptyTitle() {
        createNewsPage.addNews(category, DataHelper.getEmptyTitle(), date, time, description);
        createNewsPage.saveAddNews();
        createNewsPage.errorTextMessageCreateNews(DataHelper.emptyFieldsAddNewsError());
    }

    @Test
    //Test Case 58 "Отказ в создании новости без даты публикации"
    public void shouldNotCreateNewsWithEmptyDate() {
        createNewsPage.addNews(category, title, DataHelper.getEmptyDate(), time, description);
        createNewsPage.saveAddNews();
        createNewsPage.errorTextMessageCreateNews(DataHelper.emptyFieldsAddNewsError());
    }

    @Test
    //Test Case 59 "Отказ в создании новости без времени публикации"
    public void shouldNotCreateNewsWithEmptyTime() {
        createNewsPage.addNews(category, title, date, DataHelper.getEmptyTime(), description);
        createNewsPage.saveAddNews();
        createNewsPage.errorTextMessageCreateNews(DataHelper.emptyFieldsAddNewsError());
    }

    @Test
    //Test Case 60 "Отказ в создании новости без времени публикации"
    public void shouldNotCreateNewsWithEmptyDescription() {
        createNewsPage.addNews(category, title, date, time, DataHelper.getEmptyDescription());
        createNewsPage.saveAddNews();
        createNewsPage.errorTextMessageCreateNews(DataHelper.emptyFieldsAddNewsError());
    }

    @Test
    //Test Case 61 "Отмена создания новости по кнопке «Cancel»"
    public void shouldCancelAddNews() {
        createNewsPage.addNews(category, title, date, time, description);
        createNewsPage.cancelAddNews();
        controlPanelPage.selectCancelNews(title);
    }


}
