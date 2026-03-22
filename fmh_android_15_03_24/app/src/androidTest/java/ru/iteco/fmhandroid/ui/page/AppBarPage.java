package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.not;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class AppBarPage {
    private final ViewInteraction menuButton = onView(withId(R.id.main_menu_image_button));
    private final ViewInteraction menuMainButton = onView(allOf(withId(android.R.id.title), withText("Main"), isDisplayed()));
    private final ViewInteraction menuNewsButton = onView(allOf(withId(android.R.id.title), withText("News"), isDisplayed()));
    private final ViewInteraction menuAboutButton = onView(allOf(withId(android.R.id.title), withText("About"), isDisplayed()));
    private final ViewInteraction ourMissionButton = onView(allOf(withId(R.id.our_mission_image_button), isDisplayed()));
    private final ViewInteraction authButton = onView(allOf(withId(R.id.authorization_image_button), withContentDescription("Authorization"), isDisplayed()));
    private final ViewInteraction logOutButton = onView(allOf(withId(android.R.id.title), withText("Log out"), isDisplayed()));

    // Кнопки меню
    public void menuMainButton() {
        Allure.step("Подтверждаем видимость кнопки Меню и нажимаем на нее");
        menuButton.check(matches(isDisplayed()));
        menuButton.perform(click());
        Allure.step("Подтверждаем видимость кнопки Main и нажимаем на нее");
        menuMainButton.check(matches(isDisplayed()));
        menuMainButton.perform(click());
    }
    public void notClickableMenuMainButton() {
        Allure.step("Подтверждаем видимость кнопки Меню и нажимаем на нее");
        menuButton.check(matches(isDisplayed()));
        menuButton.perform(click());
        Allure.step("Подтверждаем видимость кнопки Main и отсутствие ее кликабельности");
        menuMainButton.check(matches(isDisplayed()));
        menuMainButton.check(matches(not(isClickable())));
    }
    public void menuNewsButton() {
        Allure.step("Подтверждаем видимость кнопки Меню и нажимаем на нее");
        menuButton.check(matches(isDisplayed()));
        menuButton.perform(click());
        Allure.step("Подтверждаем видимость кнопки News и нажимаем на нее");
        menuNewsButton.check(matches(isDisplayed()));
        menuNewsButton.perform(click());
    }
    public void notClickableMenuNewsButton() {
        Allure.step("Подтверждаем видимость кнопки Меню и нажимаем на нее");
        menuButton.check(matches(isDisplayed()));
        menuButton.perform(click());
        Allure.step("Подтверждаем видимость кнопки News и отсутствие ее кликабельности");
        menuNewsButton.check(matches(isDisplayed()));
        menuNewsButton.check(matches(not(isClickable())));
    }

    public void menuAboutButton() {
        Allure.step("Подтверждаем видимость кнопки Меню и нажимаем на нее");
        menuButton.check(matches(isDisplayed()));
        menuButton.perform(click());
        Allure.step("Подтверждаем видимость кнопки About и нажимаем на нее");
        menuAboutButton.check(matches(isDisplayed()));
        menuAboutButton.perform(click());
    }

    // Цитаты
    public void ourMissionButton() {
        Allure.step("Подтверждаем видимость кнопки Our Mission");
        ourMissionButton.check(matches(isDisplayed()));
        Allure.step("Нажимаем на кнопку Our Mission");
        ourMissionButton.perform(click());
    }
    public void notClickableOurMissionButton() {
        Allure.step("Подтверждаем видимость кнопки Our Mission");
        ourMissionButton.check(matches(isDisplayed()));
        Allure.step("Подтверждаем отсутствия кликабельности кнопки Our Mission");
        ourMissionButton.check(matches(not(isClickable())));
    }

    // Выход из приложения
    public void logOutButton() {
        Allure.step("Подтверждаем видимость кнопки авторизации и нажимаем на нее");
        authButton.check(matches(isDisplayed()));
        authButton.perform(click());
        Allure.step("Подтверждаем видимость кнопки выхода из приложения и нажимаем на нее");
        logOutButton.check(matches(isDisplayed()));
        logOutButton.perform(click());
    }
}
