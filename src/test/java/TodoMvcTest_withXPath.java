import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class TodoMvcTest_withXPath {

    @Test
    @DisplayName("Using only XPath selectors without Selenide methods")
    public void completesTask_Lesson3() {
        Configuration.holdBrowserOpen = false;

        open("http://todomvc.com/examples/emberjs/");

        // tasks should be a, b, c
        $x("//input[@id='new-todo']").val("a").pressEnter();
        $x("//input[@id='new-todo']").val("b").pressEnter();
        $x("//input[@id='new-todo']").val("c").pressEnter();

        // toggle b
        $x("//*[@id='todo-list']/li[2]//input[@class='toggle']").click(); // можно заменить input на *
        // $x("//*[@id='todo-list']/li[2]//*[@class='toggle']").click();
        // or
        // $x("//*[@id='todo-list']/li[.//text()='b']//*[@class='toggle']").click(); // менее избыточное за счет * и избегаем индекс (ищем по тексту)

        // completed tasks should be b
        $x("//li[@class='completed ember-view']").shouldHave(exactText("b"));

        // active tasks should be a, c
        $$x("//li[@class='ember-view']").shouldHave(exactTexts("a", "c"));


    }
}
