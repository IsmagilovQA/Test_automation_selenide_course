import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    @BeforeEach
    public void setup() {
        Configuration.browser = "firefox";
        Configuration.baseUrl = "http://todomvc4tasj.herokuapp.com";
    }

    @AfterEach
    public void clearData() {
        Selenide.executeJavaScript("localStorage.clear()");
        // clean local storage (or clean DB before/after each test)
    }
}
