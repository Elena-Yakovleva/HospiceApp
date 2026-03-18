package ru.iteco.fmhandroid.ui.test;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.page.AboutPage;
import ru.iteco.fmhandroid.ui.page.AuthPage;
import ru.iteco.fmhandroid.ui.page.MainPage;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class AboutPageTest {

    AuthPage authPage = new AuthPage();
    MainPage mainPage = new MainPage();
    AboutPage aboutPage = new AboutPage();

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
        mainPage.mainPageMenuAboutButon();
        aboutPage.aboutPageVisible();
    }

    @Test
    //Test Case - 26 "Переход по ссылке  на соглашение о конфиденциальности (Privacy Policy)"
    public void shouldGoPrivacyPolicy() {
        aboutPage.aboutPagePrivacyPolicy();
        intended(hasData("https://vhospice.org/#/privacy-policy"));
        Intents.release();
    }

    @Test
    //Test Case - 27 "Переход по ссылке  на пользовательское соглашение (Terms of use)"
    public void shouldGoTermsOfUse() {
        aboutPage.aboutPageTermsOfUse();
        intended(hasData("https://vhospice.org/#/terms-of-use"));
        Intents.release();
    }

    @Test
    //Test Case - 28 "Возврат на страницу Main через кнопку Назад"
    public void shouldGoBack() {
        aboutPage.backPageButton();
        mainPage.mainPageVisible();
    }
}
