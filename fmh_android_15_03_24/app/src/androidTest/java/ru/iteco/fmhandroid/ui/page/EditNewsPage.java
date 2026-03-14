package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import ru.iteco.fmhandroid.R;

public class EditNewsPage {

    ControlPanelPage controlPanelPage = new ControlPanelPage();

    public void deleteNews(String string) {
        controlPanelPage.controlPanelVisible();
        onView(allOf(withId(R.id.news_item_title_text_view), withText(string)))
                .perform(click());
        onView(withId(R.id.delete_news_item_image_view))
                .check(matches(isDisplayed()))
                .perform(click());
    }
}
