package ru.itmo.wp.form;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostForm {
    @NotNull
    @NotEmpty
    @NotBlank(message = "Title can't be empty")
    @Size(min = 1, max = 60, message = "Title size should be between 1 and 60")
    private String title;

    @NotNull
    @NotEmpty
    @NotBlank(message = "Text can't be empty")
    @Size(min = 1, max = 65000, message = "Text size should be between 1 and 65000")
    @Lob
    private String text;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
