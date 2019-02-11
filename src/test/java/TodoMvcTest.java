import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.*;
import  com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class TodoMvcTest {


    @Test
    public void completesTask() {
        Configuration.timeout = 6000;
        Configuration.browser = "firefox";
        Configuration.holdBrowserOpen = false;

        open("http://todomvc.com/examples/emberjs/");

        // add tasks: a, b, c
        $("#new-todo").val("a").pressEnter();
        $("#new-todo").val("b").pressEnter();
        $("#new-todo").val("c").pressEnter();

        // tasks should be a, b, c
        $$("#todo-list>li").shouldHave(size(3));
        $$("#todo-list>li").shouldHave(exactTexts("a", "b", "c"));

        // toggle b version 1
        //$x("//*[@id='ember11']/div/input").setSelected(true);
        // toggle b version 2
        //$(byId("ember11")).find("input.toggle").setSelected(true);
        // toggle b version 3
        // Among all tasks -> find the one with "b" text -> find it's "toggle" checkbox ->click it
        $$("#todo-list>li").findBy(exactText("b")).find(".toggle").click();

        // completed tasks should be b version 1
        $$("#todo-list>li").findBy(exactText("b")).find(".toggle").shouldBe(selected);
        // Among all tasks -> filter only completed ones -> check their texts version 2
        $$("#todo-list>li").filterBy(cssClass("completed")).shouldHave(exactTexts("b"));

        // active tasks should be a, c
        $$("#todo-list>li").excludeWith(cssClass("completed")).shouldHave(exactTexts("a", "c"));


    }


}
