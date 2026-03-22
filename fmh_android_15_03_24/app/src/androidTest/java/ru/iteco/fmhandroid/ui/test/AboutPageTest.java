package ru.iteco.fmhandroid.ui.test;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.page.AboutPage;
import ru.iteco.fmhandroid.ui.page.AppBarPage;
import ru.iteco.fmhandroid.ui.page.AuthPage;
import ru.iteco.fmhandroid.ui.page.MainPage;

@Epic(value = "Страница о компании")
@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AboutPageTest {

    AuthPage authPage = new AuthPage();
    MainPage mainPage = new MainPage();
    AboutPage aboutPage = new AboutPage();
    AppBarPage appBarPage = new AppBarPage();

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
        appBarPage.menuAboutButton();
        aboutPage.aboutPageVisible();

    }


    @Test
    @DisplayName("Test Case - 26: Переход по ссылке  на соглашение о конфиденциальности (Privacy Policy)")
    public void shouldGoPrivacyPolicy() {
        aboutPage.aboutPagePrivacyPolicy();
        intended(hasData("https://vhospice.org/#/privacy-policy"));
        Intents.release();
    }


    @Test
    @DisplayName("Test Case - 27: Переход по ссылке  на пользовательское соглашение (Terms of use)")
    public void shouldGoTermsOfUse() {
        aboutPage.aboutPageTermsOfUse();
        intended(hasData("https://vhospice.org/#/terms-of-use"));
        // Очистка ресурса"
        Intents.release();
    }


    @Test
    @DisplayName("Test Case - 28: Возврат на страницу Main через кнопку Назад")
    public void shouldGoBack() {
        aboutPage.backPageButton();
        mainPage.mainPageVisible();
    }
}
