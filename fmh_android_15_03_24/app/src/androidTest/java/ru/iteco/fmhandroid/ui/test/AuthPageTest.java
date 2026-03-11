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
import ru.iteco.fmhandroid.ui.page.MainPage;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class AuthPageTest {

    private final AuthPage authPage = new AuthPage();
    private final MainPage mainPage = new MainPage();
    private View decorView;

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setup() {
        try {
            authPage.pageLoad();
        } catch (Exception e) {
            mainPage.mainPageVisible();
            mainPage.mainPageLogOutButon();
            authPage.authPageVisible();
        }

    }

    @Test
    //Test Case - 5 "Авторизация с валидным логином и паролем"
    public void successfulAuthorizationTest() {
        authPage.authUser();

    }

    @Test
    //Test Case - 6 "Отказ в авторизации с пустым полем «Login» и валидным паролем"
    public void denialAuthorisationWithEmptyLoginForm() {
        authPage.invalidUser(DataHelper.emptyLogin(), DataHelper.getValidLogin());
        authPage.authPageVisible();
        authPage.errorText(DataHelper.emptyFieldsError());
    }

    @Test
    //Test Case - 7 "Отказ в авторизации с пустым полем «Password» и валидным логином"
    public void denialAuthorisationWithEmptyPasswordForm() {
        authPage.invalidUser(DataHelper.getValidLogin(), DataHelper.emptyPassword());
        authPage.authPageVisible();
        authPage.errorText(DataHelper.emptyFieldsError());
    }

    @Test
    //Test Case - 8 "Отказ в авторизации незарегистрированному пользователю"
    public void denialAuthorisationUnregisteredUser() {
        authPage.invalidUser(DataHelper.getWrongLogin(), DataHelper.getWrongPassword());
        authPage.authPageVisible();
        authPage.errorText(DataHelper.retryLaterError());
    }

    @Test
    //Test Case - 9 "Отказ в авторизации с неверным логином"
    public void denialAuthorisationWithWrongLogin() {
        authPage.invalidUser(DataHelper.getWrongLogin(), DataHelper.getValidPassword());
        authPage.authPageVisible();
        authPage.errorText(DataHelper.retryLaterError());
    }

    @Test
    //Test Case - 10 "Отказ в авторизации с неверным паролем"
    public void denialAuthorisationWithWrongPassword() {
        authPage.invalidUser(DataHelper.getValidLogin(), DataHelper.getWrongPassword());
        authPage.authPageVisible();
        authPage.errorText(DataHelper.retryLaterError());
    }

    @Test
    //Test Case - 11 "Отказ в авторизации с пробелами перед введенными валидными логином и паролем"
    public void denialAuthorisationWithSpacesInFront() {
        authPage.invalidUser(DataHelper.getSpacesInFrontLogin(), DataHelper.getSpacesInFrontPassword());
        authPage.authPageVisible();
        authPage.errorText(DataHelper.retryLaterError());
    }

    @Test
    //Test Case - 12 "Отказ в авторизации с пробелами после введенных валидных логина и пароля"
    public void denialAuthorisationWithSpacesAfter() {
        authPage.invalidUser(DataHelper.getSpacesAfterLogin(), DataHelper.getSpacesAfterPassword());
        authPage.authPageVisible();
        authPage.errorText(DataHelper.retryLaterError());
    }
}
