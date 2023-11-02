package qa.guru;

import com.codeborne.selenide.DragAndDropOptions;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class MoveRectangleTest extends BaseTest {
   @Test
    void moveRectangleByDragAndDropTest() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        String A = "A";
        String B = "B";
        var aPoint = $("#column-a");
        var bPoint = $("#column-b");
        aPoint.shouldHave(text(A));
        bPoint.shouldHave(text(B));
        actions().dragAndDrop($(aPoint), $(bPoint)).perform();
        aPoint.shouldHave(text(B));
        bPoint.shouldHave(text(A));
        bPoint.dragAndDrop(DragAndDropOptions.to(aPoint));
        aPoint.shouldHave(text(A));
        bPoint.shouldHave(text(B));
    }
}
