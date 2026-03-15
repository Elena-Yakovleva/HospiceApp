package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;


import static ru.iteco.fmhandroid.ui.data.Utils.hasAncestor;

import android.view.View;


import org.hamcrest.Matcher;

import ru.iteco.fmhandroid.R;

public class EditNewsPage {

    ControlPanelPage controlPanelPage = new ControlPanelPage();

    private static final Matcher<View> deleteButton = allOf(withId(R.id.delete_news_item_image_view), withContentDescription("News delete button"));

    public void deleteNews(String string) {
        controlPanelPage.controlPanelVisible();
        //проверяем создание новости
        onView(allOf(withId(R.id.news_item_title_text_view),
                withText(string))).check(matches(isDisplayed()))
                .perform(click());
        //удаляем новость
        onView(allOf(withId(R.id.delete_news_item_image_view), withContentDescription("News delete button"),
                hasSibling(withText(string)))).perform(click());
        onView(allOf(withId(android.R.id.button1), withText("OK"))).perform(click());
        //проверяем удаление
        onView(allOf(withText(string), isDisplayed())).check(doesNotExist());

    }



}
