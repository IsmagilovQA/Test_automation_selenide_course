import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Proof Of Concept - CRUD only")
public class EndToEndTest extends BaseTest {

    private static final ElementsCollection tasks = $$("#todo-list>li");

    /*
    End to End test:

    - create several items;
    - edit "b" to "b edited";
    - set task "b edited" as completed;
    - clear "b edited" task;
    - cancel editing by Escape;
    - delete "c" ("a" should be in the list).
     */

    @BeforeEach
    public void openSite() {
        open("/");
    }


    @Test
    public void crud() {

        add("a", "b", "c");
        assertActiveTasks("a", "b", "c");

        edit("b", "b edited");

        toggle("b edited");
        clearCompleted();
        assertActiveTasks("a", "c");

        cancelEdit("c", "to be canceled");

        delete("c");
        assertActiveTasks("a");
    }


    @Test
    public void filtersTask() {

        add("a", "b", "c");
        assertActiveTasks("a", "b", "c");

        toggle("b");
        assertCompletedTasks("b");

        filterActive();
        assertActiveTasks("a", "c");

        filterCompleted();
        assertCompletedTasks("b");
    }





    // String... it's the same as String[] (the method can take multple Strings as its argument)
    private void add(String... taskName) {
        for (String text : taskName) {
            $("#new-todo").val(text).pressEnter();
        }
    }

    private void delete(String taskName) {
        $$("#todo-list>li").findBy(exactText(taskName)).hover().find(".destroy").click();
    }

    private void edit(String taskName, String editedTask) {
        $$("#todo-list>li").findBy(exactText(taskName)).doubleClick();
        $$("#todo-list>li").findBy(cssClass("editing")).find(".edit")
                .val(editedTask).pressEnter();
    }

    private void cancelEdit(String taskName, String text_canceled) {
        $$("#todo-list>li").findBy(exactText(taskName)).doubleClick();
        $$("#todo-list>li").findBy(cssClass("editing")).find(".edit")
                .val(text_canceled).pressEscape();
    }

    private static void assertActiveTasks(String... texts) {
        tasks.filterBy(cssClass("active")).shouldHave(exactTexts(texts));
    }

    private static void assertCompletedTasks(String... texts) {
        tasks.filterBy(cssClass("completed")).shouldHave(exactTexts(texts));
    }

    private void clearCompleted() {
        $("#clear-completed").click();
    }

    private void toggle(String taskName){
        tasks.findBy(exactText(taskName)).find(".toggle").click();
    }



    private void filterActive(){
        $("[href$='active']").click();
    }

    private void filterCompleted(){
        $("href$='completed']").click();
    }

    private void filterAll() {
        $("[href='\\#\\/']").click();
    }
}
