package qa.guru.pages.components;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    public void setCalendar(String date) {
        String[] splitDate = date.split(" ");
        $(".react-datepicker__month-select").selectOption(splitDate[1]);
        $(".react-datepicker__year-select").selectOption(splitDate[2]);
        $(".react-datepicker__day--0"+splitDate[0]+":not(.react-datepicker__day--outside-month)").click();
    }
}
