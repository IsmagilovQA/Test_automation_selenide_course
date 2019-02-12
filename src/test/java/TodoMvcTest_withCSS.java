import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.selected;
import static com.codeborne.selenide.Selenide.*;

public class TodoMvcTest_withCSS {

    @Test
    @DisplayName("Using only CSS selectors without findBy, filterBy, excludeWith and so on")
    public void completesTask_Lesson2() {
        Configuration.holdBrowserOpen = false;
        Configuration.browser = "chrome";

        open("http://todomvc.com/examples/emberjs/");

        // add tasks: a, b, c
        $("#header>input").val("a").pressEnter();
        $("#header>input").val("b").pressEnter();
        $("#header>input").val("c").pressEnter();

        // tasks should be a, b, c
        $$("#todo-list>li").shouldHave(size(3));
        //$$("#todo-list li:not(.completed)").shouldHave(exactTexts("a", "b", "c"));
        // or
        $$("#todo-list>li").shouldHave(exactTexts("a", "b", "c")); // list>li - searching on first level depth
        // $$("#todo-list li").shouldHave(exactTexts("a", "b", "c")); // list li - searching on all levels depth

        // toggle b
        $("#todo-list>li:nth-of-type(2) .toggle").setSelected(true);

        // completed tasks should be b
        // $("#todo-list>li:nth-of-type(2) .toggle").shouldBe(selected);
        // or
        $("#todo-list li.completed").shouldHave(exactText("b"));

        // active tasks should be a, c
        $("#todo-list>li:nth-of-type(1) .toggle").shouldNotBe(selected);
        $("#todo-list>li:nth-of-type(3) .toggle").shouldNotBe(selected);
        // or
        // $$("#todo-list li:not(.completed)").shouldHave(exactTexts("a", "c"));
    }
}
