package ru.iteco.fmhandroid.ui.test;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.page.AppBarPage;
import ru.iteco.fmhandroid.ui.page.AuthPage;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.OurMissionPage;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.runner.RunWith;
@Epic(value = "Страница Our Mission")
@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class OurMissionPageTest {
    AuthPage authPage = new AuthPage();
    MainPage mainPage = new MainPage();
    AppBarPage appBarPage = new AppBarPage();
    OurMissionPage ourMissionPage = new OurMissionPage();

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
        appBarPage.ourMissionButton();
        ourMissionPage.ourMissionPageVisible();
    }


    @Test
    @DisplayName("Test Case - 25 Развернуть и свернуть цитату на странице OurMission")
    public void shouldExpandAndCollapseQuoteOurMission() {
        ourMissionPage.expandQuoteOurMission();
        ourMissionPage.collapseQuoteOurMission();

    }

}
