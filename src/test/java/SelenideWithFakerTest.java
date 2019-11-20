import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


class SelenideWithFakerTest {
    public String datePlusFive() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        String dayPlus5 = format.format(LocalDate.now().plusDays(5));
        return dayPlus5;
    }

    public String datePlusSix() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.YYYY");
        String dayPlus6 = format.format(LocalDate.now().plusDays(6));
        return dayPlus6;
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @Test
    void shouldBeSuccessTest() {
        RegistrationByCardInfo info = new RegistrationByCardInfo();
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue(info.getCity());
        $(".menu-item__control").click();
        List<SelenideElement> input = $$(".input__control");
        SelenideElement myData = input.get(1);
        myData.click();
        myData.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        myData.setValue(datePlusFive());
        $("[data-test-id=name] input").setValue(info.getName());
        $("[data-test-id=phone] input").setValue(info.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification]").waitUntil(visible, 12000);
        myData.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        myData.setValue(datePlusSix());
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=replan-notification]").waitUntil(visible, 4000).click();
    }
}


