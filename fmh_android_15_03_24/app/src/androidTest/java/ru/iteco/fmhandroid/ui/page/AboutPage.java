package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.Utils;

public class AboutPage {

    // Элементы страницы
    public final ViewInteraction title = onView(withId(R.id.about_version_title_text_view));
    public final ViewInteraction privacyTitle = onView(withId(R.id.about_privacy_policy_label_text_view));
    public final ViewInteraction privacyLink = onView(withId(R.id.about_privacy_policy_value_text_view));
    public final ViewInteraction termsTitle = onView(withId(R.id.about_terms_of_use_label_text_view));
    public final ViewInteraction termsLink = onView(withId(R.id.about_terms_of_use_value_text_view));
    public final ViewInteraction aboutPageBackButton = onView(withId(R.id.about_back_image_button));


    public void aboutPageVisible() {
        Allure.step("Ожидание загрузки страницы About");
        Utils.waitDisplayed(R.id.about_version_title_text_view, 500);
        Allure.step("Подтверждение видимости элемента номера версии приложения");
        title.check(matches(isDisplayed()));
        title.check(matches(withText("Version:")));
    }

    // Кнопка назад
    public void backPageButton() {
        Allure.step("Отображение и кликабельность кнопки Назад");
        aboutPageBackButton.check(matches(isDisplayed()))
                .check(matches(isClickable()));
        Allure.step("Клик по кнопке Назад");
        aboutPageBackButton.perform(click());
    }

    // Ссылка на политику конфиденциальности
    public void aboutPagePrivacyPolicy() {
        Allure.step("Отображение элемента Privacy Policy");
        privacyTitle.check(matches(isDisplayed()))
                .check(matches(withText("Privacy Policy:")));
        Allure.step("Отображение и кликабельность ссылки на страницу с конфиденциальной информацией");
        privacyLink.check(matches(isDisplayed())).
                check(matches(withText("https://vhospice.org/#/privacy-policy/")))
                .check(matches(isClickable()));
        Allure.step("Клик на ссылку");
        privacyLink.perform(click());
    }

    // Ссылка на пользовательское соглашение
    public void aboutPageTermsOfUse() {
        Allure.step("Отображение элемента Terms of use");
        termsTitle.check(matches(isDisplayed()))
                .check(matches(withText("Terms of use:")));
        Allure.step("Отображение и кликабельность ссылки на страницу с пользовательскими соглашениями");
        termsLink.check(matches(isDisplayed()))
                .check(matches(withText("https://vhospice.org/#/terms-of-use")))
                .check(matches(isClickable()));
        Allure.step("Клик на ссылку");
        termsLink.perform(click());
    }
}
