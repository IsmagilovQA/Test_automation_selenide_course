import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Proof Of Concept - CRUD only")
public class EndToEndTest {

    /*
    End to End test:

    - create several items;
    - edit "b" to "b edited";
    - set task "b edited" as completed;
    - clear "b edited" task;
    - cancel editing by Escape;
    - delete "c" ("a" should be in the list).
     */

    @Test
    public void test() {
        open("http://todomvc4tasj.herokuapp.com/");

        // Add
        $("#new-todo").val("a").pressEnter();
        $("#new-todo").val("b").pressEnter();
        $("#new-todo").val("c").pressEnter();
        $$("#todo-list>li").shouldHave(exactTexts("a", "b", "c"));

        // Edit
        $$("#todo-list>li").findBy(exactText("b")).doubleClick();
        $$("#todo-list>li").findBy(cssClass("editing")).find(".edit")
                .val("b edited").pressEnter();

        // Complete & Clear
        $$("#todo-list>li").findBy(exactText("b edited")).find(".toggle").click();
        $("#clear-completed").click();
        $$("#todo-list>li").shouldHave(exactTexts("a", "c"));

        // Cancel Edit
        $$("#todo-list>li").findBy(exactText("c")).doubleClick();
        $$("#todo-list>li").findBy(cssClass("editing")).find(".edit")
                .val("to be canceled").pressEscape();

        // Delete
        $$("#todo-list>li").findBy(exactText("c")).hover().find(".destroy").click();
        $$("#todo-list>li").shouldHave(exactTexts("a"));
    }
}
