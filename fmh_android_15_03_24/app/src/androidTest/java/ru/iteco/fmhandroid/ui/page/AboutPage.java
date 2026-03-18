package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

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


    // Проверка видимости элементов

    public void aboutPageVisible() {
        Utils.waitDisplayed(R.id.about_version_title_text_view, 500);
        title.check(matches(isDisplayed()));
        title.check(matches(withText("Version:")));
    }

    public void backPageButton() {
        aboutPageBackButton.check(matches(isDisplayed()))
                .check(matches(isClickable()));
        aboutPageBackButton.perform(click());
    }

    public void aboutPagePrivacyPolicy() {
        privacyTitle.check(matches(isDisplayed()))
                .check(matches(withText("Privacy Policy:")));
        privacyLink.check(matches(isDisplayed())).
                check(matches(withText("https://vhospice.org/#/privacy-policy/")))
                .check(matches(isClickable()));
        privacyLink.perform(click());
    }

    public void aboutPageTermsOfUse() {
        termsTitle.check(matches(isDisplayed()))
                .check(matches(withText("Terms of use:")));
        termsLink.check(matches(isDisplayed()))
                .check(matches(withText("https://vhospice.org/#/privacy-policy/")))
                .check(matches(isClickable()));
        termsLink.perform(click());
    }
}
