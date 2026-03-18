package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;

import org.hamcrest.Matchers;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataHelper;
import ru.iteco.fmhandroid.ui.data.Utils;

public class AuthPage {
    //Элементы страницы
    private final ViewInteraction title = onView(withText("Authorization"));
    private final ViewInteraction login = onView(withId(R.id.login_text_input_layout));
    private final ViewInteraction password = onView(withId(R.id.password_text_input_layout));
    private final ViewInteraction button = onView(withId(R.id.enter_button));
    private View decorView;

    // Подтверждение загрузки страницы
    public void pageLoad() {
        onView(isRoot()).perform(Utils.waitDisplayed(R.id.enter_button, 8000));
    }

    // Проверка видимости элементов
    public void authPageVisible() {
        title.check(matches(isDisplayed()));
        login.check(matches(isDisplayed()));
        password.check(matches(isDisplayed()));
        button.check(matches(isDisplayed()));
    }

    // Ввод данных
    public void inputLogin(String login) {
        onView(withHint("Login"))
                .perform(ViewActions.typeText(login))
                .perform(ViewActions.closeSoftKeyboard());
    }

    public void inputPassword(String password) {
        onView(withHint("Password"))
                .perform(ViewActions.typeText(password))
                .perform(ViewActions.closeSoftKeyboard());
    }

    public void clickButton() {
        button.perform(click());
    }

    // Авторизация
    public MainPage authUser() {
        inputLogin(DataHelper.getValidLogin());
        inputPassword(DataHelper.getValidPassword());
        clickButton();
        return new MainPage();
    }

    public void invalidUser(String wrongLogin, String wrongPassword) {
        inputLogin(wrongLogin);
        inputPassword(wrongPassword);
        clickButton();
    }

    //Проверка текста ошибки
    public void errorText(String text) {
        onView(withText(text))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }
}
