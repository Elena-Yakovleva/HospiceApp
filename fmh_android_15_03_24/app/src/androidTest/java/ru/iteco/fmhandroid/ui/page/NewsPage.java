package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.Utils;

public class NewsPage {

    public void newsPageLoad() {
        onView(isRoot()).perform(Utils.waitDisplayed(R.id.container_list_news_include, 5000));

    }
}
