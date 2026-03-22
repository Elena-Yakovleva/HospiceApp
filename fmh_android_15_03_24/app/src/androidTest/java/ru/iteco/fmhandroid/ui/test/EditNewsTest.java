package ru.iteco.fmhandroid.ui.test;

import static ru.iteco.fmhandroid.ui.data.DataHelper.currentDate;
import static ru.iteco.fmhandroid.ui.data.DataHelper.savingFailedError;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

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

@Epic(value = "Редактирование публикации")
@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class EditNewsTest {
    MainPage mainPage = new MainPage();
    AuthPage authPage = new AuthPage();
    NewsPage newsPage = new NewsPage();
    AppBarPage appBarPage = new AppBarPage();
    CreateNewsPage createNewsPage = new CreateNewsPage();
    EditNewsPage editNews = new EditNewsPage();
    ControlPanelPage controlPanelPage = new ControlPanelPage();
    private View decorView;
    int position = DataHelper.getNumberCard();
    String category = DataHelper.getDefaultCategory(position);
    String title = DataHelper.getOwnTitle();
    String date = DataHelper.getFutureDate(5);
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
        appBarPage.menuNewsButton();
        newsPage.newsPageLoad();
        newsPage.newsPageControlPanelButton();
        createNewsPage.addNews(category, title, date, time, description);
        createNewsPage.saveAddNews();

    }


    @Test
    @DisplayName("Test Case - 62: Изменить категорию публикации на доступную из списка по умолчанию")
    public void shouldEditingNewsCategory() {
        int position1 = DataHelper.getNumberCard();
        editNews.editNewsButton(title);
        editNews.changeIfFieldInNewsForm("Category", editNews.fieldCategory(), DataHelper.getDefaultCategory(position1));
        editNews.deleteNewsButton(title);
    }

    @Test
    @DisplayName("Test Case - 63: Изменить категорию публикации на свою")
    public void shouldNotEditingNewsCategoryToOurOwnText() {
        String category1 = "aaaaa";
        editNews.editNewsButton(title);
        editNews.changeIfFieldInNewsForm("Category", editNews.fieldCategory(), category1);
        editNews.errorTextMessage(savingFailedError());
        editNews.deleteNewsButton(title);
    }

    @Test
    @DisplayName("Test Case - 64: Изменить титул публикации")
    public void shouldEditingNewsTitle() {
        String title2 = "Титул изменен на: " + title;
        editNews.editNewsButton(title);
        editNews.changeIfFieldInNewsForm("Title", editNews.fieldTitle(), title2);
        editNews.deleteNewsButton(title2);
    }

    @Test
    @DisplayName("Test Case - 65: Изменить дату публикации на более позднюю от указанной")
    public void shouldEditingNewsDateAnFutureDate() {
        editNews.editNewsButton(title);
        editNews.changeIfFieldInNewsForm("Date", editNews.fieldDate(), DataHelper.getFutureDate(5));
        editNews.deleteNewsButton(title);
    }

    @Test
    @DisplayName("Test Case - 66: Изменить дату публикации на более раннюю от указанной")
    public void shouldEditingNewsDateAnLastDate() {
        String newDate = DataHelper.getOwnDate(5, 3);
        editNews.editNewsButton(title);
        editNews.changeIfFieldInNewsForm("Date", editNews.fieldDate(), newDate);
        editNews.deleteNewsButton(title);
    }


    @Test
    @DisplayName("Test Case - 68: Изменить время публикации на будущее")
    public void settingFutureTimeForNews() {
        String newTime = DataHelper.futureTime(5);
        editNews.editNewsButton(title);
        editNews.changeIfFieldInNewsForm("Time", editNews.fieldTime(), newTime);
        editNews.deleteNewsButton(title);
    }

    @Test
    @DisplayName("Test Case - 69: Изменить текст публикации")
    public void shouldEditingNewsDescription() {
        editNews.editNewsButton(title);
        editNews.changeIfFieldInNewsForm("Description", editNews.fieldDescription(), "текст изменен " + DataHelper.currentTime());
        editNews.deleteNewsButton(title);
    }

    @Test
    @DisplayName("Test Case - 70: Изменить статус публикации на «Not active»")
    public void changePublicationStatusFromActiveToNotActive() {
        editNews.editNewsButton(title);
        editNews.changeStatus();
        controlPanelPage.checkStatus(title, "NOT ACTIVE");
        editNews.deleteNewsButton(title);
    }

    @Test
    @DisplayName("Test Case - 71: Изменить статус публикации на «Аctive»")
    public void changePublicationStatusFromNotActiveToActive() {
        editNews.editNewsButton(title);
        editNews.changeStatus();
        controlPanelPage.checkStatus(title, "NOT ACTIVE");
        editNews.editNewsButton(title);
        editNews.changeStatus();
        controlPanelPage.checkStatus(title, "ACTIVE");
        editNews.deleteNewsButton(title);
    }

    @Test
    @DisplayName("Test Case - 72: Не сохранять внесенные изменения по кнопке «Cancel»")
    public void cancelButtonShouldNotSaveChanges() {
        String title2 = DataHelper.getOwnTitle() + " " + DataHelper.currentDate();
        editNews.editNewsButton(title);
        editNews.cancelChangeIfFieldInNewsForm("Title", editNews.fieldTitle(), title2);
        editNews.deleteNewsButton(title);
    }

    @Test
    @DisplayName("Test Case - 73: Удалить новость")
    public void shouldDeleteNews() {
        editNews.deleteNewsButton(title);
    }
}
