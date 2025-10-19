import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SimpleJUnitTest {

    public static String firstName = "Shadow" ;
    public static String secondName = "Fiend" ;
    public static String userMail = "sf@ya.ru" ;
    public static String phoneNumber = "4815162342" ;
    public static String imagePath = "dota-shadow-fiend.gif" ;
    public static String myFavouriteSubject = "Chemistry" ;
    public static String myAddress = "only Middle st." ;

    @BeforeAll
    static void setup() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false ;

    }

    @Test
    @DisplayName ("SimpleJUnitTest")
    void fillFormTest() {
        open("/automation-practice-form");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(secondName);
        $("#userEmail").setValue(userMail);
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue(phoneNumber);


        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").selectOption("1991");
        $(".react-datepicker__day--008").click();


        $("#subjectsInput").setValue(myFavouriteSubject).pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();

        // Загрузка файла
        $("#uploadPicture").uploadFromClasspath(imagePath);

        // Адрес
        $("#currentAddress").setValue(myAddress);

        // Штат и город
        $("#state").click();
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#city").click();
        $("#react-select-4-input").setValue("Delhi").pressEnter();

        // Отправка формы
        $("#submit").click();

        // Проверка результатов
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(firstName + " " + secondName));
        $(".table-responsive").shouldHave(text(userMail));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text(phoneNumber));
        $(".table-responsive").shouldHave(text("08 May,1991"));
        $(".table-responsive").shouldHave(text(myFavouriteSubject));
        $(".table-responsive").shouldHave(text("Sports, Music"));
        $(".table-responsive").shouldHave(text(imagePath));
        $(".table-responsive").shouldHave(text(myAddress));
        $(".table-responsive").shouldHave(text("NCR Delhi"));

    }
}