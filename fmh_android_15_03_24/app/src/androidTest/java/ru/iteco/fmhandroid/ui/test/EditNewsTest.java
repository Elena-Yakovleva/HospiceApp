package ru.iteco.fmhandroid.ui.test;

import static ru.iteco.fmhandroid.ui.data.DataHelper.savingFailedError;

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
import ru.iteco.fmhandroid.ui.page.CreateNewsPage;
import ru.iteco.fmhandroid.ui.page.EditNewsPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EditNewsTest {
    MainPage mainPage = new MainPage();
    AuthPage authPage = new AuthPage();
    NewsPage newsPage = new NewsPage();
    CreateNewsPage createNewsPage = new CreateNewsPage();
    EditNewsPage editNews = new EditNewsPage();
    ControlPanelPage controlPanelPage = new ControlPanelPage();
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
    //Test Case 62 "Изменить категорию публикации на доступную из списка по умолчанию"
    public void shouldEditingNewsCategory() {
        int position1 = DataHelper.getNumberCard();
        createNewsPage.addNews(category, title, date, time, description);
        createNewsPage.saveAddNews();
        createNewsPage.controlPanelLoad();
        editNews.editNewsButton(title);
        editNews.changeIfFieldInNewsForm(editNews.fieldCategory(), DataHelper.getDefaultCategory(position1));
        controlPanelPage.selectNews(title);
        editNews.deleteNewsButton(title);
    }

    @Test
    //Test Case 63 "Изменить категорию публикации на свою"
    public void shouldNotEditingNewsCategoryToOurOwnText() {
        String category1 = "aaaaa";
        createNewsPage.addNews(category, title, date, time, description);
        createNewsPage.saveAddNews();
        createNewsPage.controlPanelLoad();
        editNews.editNewsButton(title);
        editNews.changeIfFieldInNewsForm(editNews.fieldCategory(), category1);
        editNews.errorTextMessage(savingFailedError());
        editNews.deleteNewsButton(title);
    }

    @Test
    //Test Case 64 "Изменить титул публикации"
    public void shouldEditingNewsTitle() {
        String title2 = "титул изменен на: " + title;
        createNewsPage.addNews(category, title, date, time, description);
        createNewsPage.saveAddNews();
        createNewsPage.controlPanelLoad();
        editNews.editNewsButton(title);
        editNews.changeIfFieldInNewsForm(editNews.fieldTitle(), title2);
        controlPanelPage.selectNews(title2);
        editNews.deleteNewsButton(title2);
    }

    @Test
    //Test Case 65 "Изменить дату публикации на более позднюю от указанной"
    public void shouldEditingNewsDateAnFutureDate() {
        String title = "Дата создания новости: " + DataHelper.currentDate();
        createNewsPage.addNews(category, title, date, time, description);
        createNewsPage.saveAddNews();
        createNewsPage.controlPanelLoad();
        editNews.editNewsButton(title);
        editNews.changeIfFieldInNewsForm(editNews.fieldDate(), DataHelper.getFutureDate(5));
        controlPanelPage.selectNews(title);
        editNews.deleteNewsButton(title);
    }

    @Test
    //Test Case 66 "Изменить дату публикации на более раннюю от указанной "
    public void shouldEditingNewsDateAnLastDate() {
        String title = "Дата создания новости: " + DataHelper.currentDate();
        String newDate = DataHelper.getOwnDate(5, 3);
        createNewsPage.addNews(category, title, DataHelper.getFutureDate(5), time, description);
        createNewsPage.saveAddNews();
        createNewsPage.controlPanelLoad();
        editNews.editNewsButton(title);
        editNews.changeIfFieldInNewsForm(editNews.fieldDate(), newDate);
        controlPanelPage.selectNews(title);
        editNews.deleteNewsButton(title);
    }

    @Test
    //Test Case 68 "Изменить время публикации на будущее"
    public void settingFutureTimeForNews() {
        String title = "Время создания новости: " + DataHelper.currentTime();
        String newTime = DataHelper.futureTime(5);
        createNewsPage.addNews(category, title, date, time, description);
        createNewsPage.saveAddNews();
        createNewsPage.controlPanelLoad();
        editNews.editNewsButton(title);
        editNews.changeIfFieldInNewsForm(editNews.fieldTime(), newTime);
        controlPanelPage.selectNews(title);
        editNews.deleteNewsButton(title);
    }

    @Test
    //Test Case 69 "Изменить текст публикации"
    public void shouldEditingNewsDescription() {
        createNewsPage.addNews(category, title, date, time, description);
        createNewsPage.saveAddNews();
        createNewsPage.controlPanelLoad();
        editNews.editNewsButton(title);
        editNews.changeIfFieldInNewsForm(editNews.fieldDescription(), "текст изменен " + DataHelper.currentTime());
        controlPanelPage.selectNews(title);
        editNews.deleteNewsButton(title);
    }

    @Test
    //Test Case 70 "Изменить статус публикации на «Not active"
    public void changePublicationStatusFromActiveToNotActive() {
        createNewsPage.addNews(category, title, date, time, description);
        createNewsPage.saveAddNews();
        createNewsPage.controlPanelLoad();
        editNews.editNewsButton(title);
        editNews.changeStatus();
        controlPanelPage.checkStatus(title, "NOT ACTIVE");
        controlPanelPage.selectNews(title);
        editNews.deleteNewsButton(title);
    }

    @Test
    //Test Case 71 "Изменить статус публикации на «Аctive»"
    public void changePublicationStatusFromNotActiveToActive() {
        createNewsPage.addNews(category, title, date, time, description);
        createNewsPage.saveAddNews();
        createNewsPage.controlPanelLoad();
        editNews.editNewsButton(title);
        editNews.changeStatus();
        controlPanelPage.checkStatus(title, "NOT ACTIVE");
        editNews.editNewsButton(title);
        editNews.changeStatus();
        controlPanelPage.checkStatus(title, "ACTIVE");
        controlPanelPage.selectNews(title);
        editNews.deleteNewsButton(title);
    }

    @Test
    //Test Case 72 "Не сохранять внесенные изменения по кнопке «Cancel»»"
    public void cancelButtonShouldNotSaveChanges() {
        String title2 = DataHelper.getOwnTitle() + " " + DataHelper.currentDate();
        createNewsPage.addNews(category, title, date, time, description);
        createNewsPage.saveAddNews();
        createNewsPage.controlPanelLoad();
        editNews.editNewsButton(title);
        editNews.cancelChangeIfFieldInNewsForm(editNews.fieldTitle(), title2);
        controlPanelPage.selectNews(title);
        editNews.deleteNewsButton(title);
    }

    @Test
    //Test Case 73 "Удалить новость"
    public void shouldDeleteNews() {
        createNewsPage.addNews(category, title, date, time, description);
        createNewsPage.saveAddNews();
        createNewsPage.controlPanelLoad();
        controlPanelPage.selectNews(title);
        editNews.deleteNewsButton(title);
    }
}
