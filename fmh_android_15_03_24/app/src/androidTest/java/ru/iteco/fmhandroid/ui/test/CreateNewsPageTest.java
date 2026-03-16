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
    EditNewsPage editNewsPage = new EditNewsPage();

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
        newsPage.newsPageControlPanelButton();
        createNewsPage.controlPanelLoad();

    }


    @Test
    //Test Case 53 "Создание новости с полями заполненными валидными данными"
    public void shouldAddNews() {
        int position = DataHelper.getNumberCard();
        createNewsPage.addNews(DataHelper.getDefaultCategory(position),
                DataHelper.getDefaultTitle(position),
                DataHelper.currentDate(),
                DataHelper.currentTime(),
                DataHelper.getDescription());
        createNewsPage.saveAddNews();
        editNewsPage.selectNews(DataHelper.getDefaultTitle(position));
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
        createNewsPage.errorTextMessage(DataHelper.emptyFieldsAddNewsError());
    }
    @Test
    //Test Case 55 "Отказ в создании новости без выбора категории"
    public void shouldNotCreateNewsWithEmptyCategory() {
        int position = DataHelper.getNumberCard();
        createNewsPage.addNews(DataHelper.getEmptyCategory(),
                DataHelper.getDefaultTitle(position),
                DataHelper.currentDate(),
                DataHelper.currentTime(),
                DataHelper.getDescription());
        createNewsPage.saveAddNews();
        createNewsPage.errorTextMessage(DataHelper.emptyFieldsAddNewsError());
    }

    @Test
    //Test Case 56 "Отказ в создании новости со своей категорией.."
    public void shouldNotCreateNewsWithOwmCategory() {
        int position = DataHelper.getNumberCard();
        createNewsPage.addNews(DataHelper.getOwnCategory("My category"),
                DataHelper.getDefaultTitle(position),
                DataHelper.currentDate(),
                DataHelper.currentTime(),
                DataHelper.getDescription());
        createNewsPage.saveAddNews();
        createNewsPage.errorTextMessage(DataHelper.savingFailedError());
    }
    @Test
    //Test Case 57 "Отказ в создание новости без титула"
    public void shouldNotCreateNewsWithEmptyTitle() {
        int position = DataHelper.getNumberCard();
        createNewsPage.addNews(DataHelper.getDefaultCategory(position),
                DataHelper.getEmptyTitle(),
                DataHelper.currentDate(),
                DataHelper.currentTime(),
                DataHelper.getDescription());
        createNewsPage.saveAddNews();
        createNewsPage.errorTextMessage(DataHelper.emptyFieldsAddNewsError());
    }
    @Test
    //Test Case 58 "Отказ в создании новости без даты публикации"
    public void shouldNotCreateNewsWithEmptyDate() {
        int position = DataHelper.getNumberCard();
        createNewsPage.addNews(DataHelper.getDefaultCategory(position),
                DataHelper.getDefaultTitle(position),
                DataHelper.getEmptyDate(),
                DataHelper.currentTime(),
                DataHelper.getDescription());
        createNewsPage.saveAddNews();
        createNewsPage.errorTextMessage(DataHelper.emptyFieldsAddNewsError());
    }

    @Test
    //Test Case 59 "Отказ в создании новости без времени публикации"
    public void shouldNotCreateNewsWithEmptyTime() {
        int position = DataHelper.getNumberCard();
        createNewsPage.addNews(DataHelper.getDefaultCategory(position),
                DataHelper.getDefaultTitle(position),
                DataHelper.currentDate(),
                DataHelper.getEmptyTime(),
                DataHelper.getDescription());
        createNewsPage.saveAddNews();
        createNewsPage.errorTextMessage(DataHelper.emptyFieldsAddNewsError());
    }
    @Test
    //Test Case 60 "Отказ в создании новости без времени публикации"
    public void shouldNotCreateNewsWithEmptyDescription() {
        int position = DataHelper.getNumberCard();
        createNewsPage.addNews(DataHelper.getDefaultCategory(position),
                DataHelper.getDefaultTitle(position),
                DataHelper.currentDate(),
                DataHelper.currentTime(),
                DataHelper.getEmptyDescription());
        createNewsPage.saveAddNews();
        createNewsPage.errorTextMessage(DataHelper.emptyFieldsAddNewsError());
    }

    @Test
    //Test Case 61 "Отмена создания новости по кнопке «Cancel»"
    public void shouldCancelAddNews() {
        int position = DataHelper.getNumberCard();
        createNewsPage.addNews(DataHelper.getDefaultCategory(position),
                DataHelper.getDefaultCategory(position),
                DataHelper.currentDate(),
                DataHelper.currentTime(),
                DataHelper.getDescription());
        createNewsPage.cancelAddNews();

    }





}
