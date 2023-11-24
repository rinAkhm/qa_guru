package qa.guru.pages.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.NoSuchElementException;
import java.util.Optional;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class TableBookComponent {
    private SelenideElement row;
    private static final ElementsCollection rowsTable = $$(".rt-tr-group");


    public TableBookComponent getRow(int rowNumber) {
        row = rowsTable.get(rowNumber-1);
        return this;
    }

    public TableBookComponent checkTitle(String title) {
        Optional.ofNullable(row).orElseThrow(()-> new NoSuchElementException())
                .find(String.format("[id='see-book-%s']", title)).shouldHave(text(title));
        return this;
    }
}
