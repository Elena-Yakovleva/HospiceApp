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
    //Test Case 62 "Изменить категорию публикации на доступную из списка по умолчанию"
    public void shouldEditingNewsCategory() {
        int position = DataHelper.getNumberCard();
        String title = DataHelper.getOwnTitle();
        String category = DataHelper.getDefaultCategory(position);

        createNewsPage.addNews(DataHelper.getDefaultCategory(position),
                title,
                DataHelper.currentDate(),
                DataHelper.currentTime(),
                DataHelper.getDescription());
        createNewsPage.saveAddNews();
        createNewsPage.controlPanelLoad();
        editNews.editNewsButton(title);
        editNews.changeIfFieldInNewsForm(editNews.fieldCategory(), category);
    }
    @Test
    //Test Case 63 "Изменить категорию публикации на доступную из списка по умолчанию"
    public void shouldNotEditingNewsCategoryToOurOwnText() {
        int position = DataHelper.getNumberCard();
        String title = DataHelper.getOwnTitle();
        String category = "aaaaa";

        createNewsPage.addNews(DataHelper.getDefaultCategory(position),
                title,
                DataHelper.currentDate(),
                DataHelper.currentTime(),
                DataHelper.getDescription());
        createNewsPage.saveAddNews();
        createNewsPage.controlPanelLoad();
        editNews.editNewsButton(title);
        editNews.changeIfFieldInNewsForm(editNews.fieldCategory(), category);
        editNews.errorTextMessage(savingFailedError());
    }

    @Test
    //Test Case 64 "Изменить титул публикации"
    public void shouldEditingNewsTitle() {
        int position = DataHelper.getNumberCard();
        String title = DataHelper.getOwnTitle();
        String title2 = DataHelper.getDefaultCategory(position) + " - " + DataHelper.getOwnTitle();

        createNewsPage.addNews(DataHelper.getDefaultCategory(position),
                title,
                DataHelper.currentDate(),
                DataHelper.currentTime(),
                DataHelper.getDescription());
        createNewsPage.saveAddNews();
        createNewsPage.controlPanelLoad();
        editNews.editNewsButton(title);
        editNews.changeIfFieldInNewsForm(editNews.fieldTitle(), title2);
    }
    @Test
    //Test Case 65 "Изменить дату публикации на более позднюю от указанной"
    public void shouldEditingNewsDateAnFutureDate() {
        int position = DataHelper.getNumberCard();
        String title = DataHelper.getDefaultCategory(position) + " " + DataHelper.currentDate();
        createNewsPage.addNews(DataHelper.getDefaultCategory(position),
                title,
                DataHelper.currentDate(),
                DataHelper.currentTime(),
                DataHelper.getDescription());
        createNewsPage.saveAddNews();
        createNewsPage.controlPanelLoad();
        editNews.editNewsButton(title);
        editNews.changeIfFieldInNewsForm(editNews.fieldDate(), DataHelper.getFutureDate(5));
    }

    @Test
    //Test Case 66 "Изменить дату публикации на более раннюю от указанной "
    public void shouldEditingNewsDateAnLastDate() {
        int position = DataHelper.getNumberCard();
        String title = DataHelper.getDefaultCategory(position) + " " + DataHelper.getFutureDate(5);

        createNewsPage.addNews(DataHelper.getDefaultCategory(position),
                title,
                DataHelper.getFutureDate(5),
                DataHelper.currentTime(),
                DataHelper.getDescription());
        createNewsPage.saveAddNews();
        createNewsPage.controlPanelLoad();
        editNews.editNewsButton(title);
        editNews.changeIfFieldInNewsForm(editNews.fieldDate(), DataHelper.getOwnDate(5,3));
    }

    @Test
    //Test Case 68 "Изменить время публикации на будущее"
    public void settingFutureTimeForNews() {
        int position = DataHelper.getNumberCard();
        String title = DataHelper.getDefaultCategory(position) + " " + DataHelper.currentTime();

        createNewsPage.addNews(DataHelper.getDefaultCategory(position),
                title,
                DataHelper.currentDate(),
                DataHelper.currentTime(),
                DataHelper.getDescription());
        createNewsPage.saveAddNews();
        createNewsPage.controlPanelLoad();
        editNews.editNewsButton(title);
        editNews.changeIfFieldInNewsForm(editNews.fieldTime(), DataHelper.futureTime(5));
    }
    @Test
    //Test Case 69 "Изменить текст публикации"
    public void shouldEditingNewsDescription() {
        int position = DataHelper.getNumberCard();
        String title = DataHelper.getDefaultCategory(position) + " " + DataHelper.currentDate();

        createNewsPage.addNews(DataHelper.getDefaultCategory(position),
                title,
                DataHelper.currentDate(),
                DataHelper.currentTime(),
                DataHelper.getDescription());
        createNewsPage.saveAddNews();
        createNewsPage.controlPanelLoad();
        editNews.editNewsButton(title);
        editNews.changeIfFieldInNewsForm(editNews.fieldDescription(),  "текст изменен " + DataHelper.currentTime());
    }
    @Test
    //Test Case 70 "Изменить статус публикации на «Not active"
    public void changePublicationStatusFromActiveToNotActive() {
        int position = DataHelper.getNumberCard();
        String title = DataHelper.getOwnTitle();

        createNewsPage.addNews(DataHelper.getDefaultCategory(position),
                title,
                DataHelper.currentDate(),
                DataHelper.currentTime(),
                DataHelper.getDescription());
        createNewsPage.saveAddNews();
        createNewsPage.controlPanelLoad();
        editNews.editNewsButton(title);
        editNews.changeStatus();
        editNews.checkStatus(title,"NOT ACTIVE");
    }
    @Test
    //Test Case 71 "Изменить статус публикации на «Аctive»"
    public void changePublicationStatusFromNotActiveToActive() {
        int position = DataHelper.getNumberCard();
        String title = DataHelper.getOwnTitle();

        createNewsPage.addNews(DataHelper.getDefaultCategory(position),
                title,
                DataHelper.currentDate(),
                DataHelper.currentTime(),
                DataHelper.getDescription());
        createNewsPage.saveAddNews();
        createNewsPage.controlPanelLoad();
        editNews.editNewsButton(title);
        editNews.changeStatus();
        editNews.checkStatus(title,"NOT ACTIVE");
        editNews.editNewsButton(title);
        editNews.changeStatus();
        editNews.checkStatus(title,"ACTIVE");
    }

    @Test
    //Test Case 72 "Не сохранять внесенные изменения по кнопке «Cancel»»"
    public void cancelButtonShouldNotSaveChanges() {
        int position = DataHelper.getNumberCard();
        String title = DataHelper.getOwnTitle();
        String title2 = DataHelper.getDefaultCategory(position) + " - " + DataHelper.getOwnTitle();

        createNewsPage.addNews(DataHelper.getDefaultCategory(position),
                title,
                DataHelper.currentDate(),
                DataHelper.currentTime(),
                DataHelper.getDescription());
        createNewsPage.saveAddNews();
        createNewsPage.controlPanelLoad();
        editNews.editNewsButton(title);
        editNews.cancelChangeIfFieldInNewsForm(editNews.fieldTitle(),title2);

    }

    @Test
    //Test Case 73 "Удалить новость"
    public void shouldDeleteNews() {
        int position = DataHelper.getNumberCard();
        String title = DataHelper.getOwnTitle();
        createNewsPage.addNews(DataHelper.getDefaultCategory(position),
                title,
                DataHelper.currentDate(),
                DataHelper.currentTime(),
                DataHelper.getDescription());
        createNewsPage.saveAddNews();
        createNewsPage.controlPanelLoad();
        editNews.selectNews(title);
        editNews.deleteNewsButton(title);
    }
}
