import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    @BeforeEach
    public void setup() {

        Configuration.baseUrl = "http://todomvc4tasj.herokuapp.com";
        Configuration.browser = "firefox";
    }
}
