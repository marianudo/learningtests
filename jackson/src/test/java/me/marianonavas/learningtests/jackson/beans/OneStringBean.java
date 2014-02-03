package me.marianonavas.learningtests.jackson.beans;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OneStringBean {
    private String string;

    @JsonCreator
    public OneStringBean(@JsonProperty("string") String string) {
        super();
        this.string = string;
    }

    public OneStringBean() {
        super();

    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

}
