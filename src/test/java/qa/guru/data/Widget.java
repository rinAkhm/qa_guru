package qa.guru.data;

public enum Widget {
    FORMS("Forms"),
    ELEMENTS("Elements"),
    WIDGETS("WIDGETS");

    private final String widget;

    Widget(String widget){
        this.widget = widget;
    }

    public String getWidget() {
        return widget;
    }
}
