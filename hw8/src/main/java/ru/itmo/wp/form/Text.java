package ru.itmo.wp.form;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Text {
    @Lob
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 1, max = 5000, message = "Content text is too long")
    private String text;

    public @NotNull @NotEmpty String getText() {
        return text;
    }

    public void setText(@NotNull @NotEmpty String text) {
        this.text = text;
    }
}
