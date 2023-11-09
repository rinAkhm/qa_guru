package qa.guru.dto;

import java.util.Random;

public enum State {
    NCR ("NCR"),
    UTTAR_PRADESH ("Uttar Pradesh"),
    HARYANA ("Haryana"),
    RAJASTHAN ("Rajasthan");

    private String[] state;
    State(String... state) {
        this.state = state;
    }

    public String getState(){
        return state[new Random().nextInt(state.length)];
    }
}
