package ru.itmo.wp.form;

import ru.itmo.wp.domain.Tag;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class PostForm {
    @NotNull
    @NotBlank(message = "Title should not be empty")
    @Size(max = 1024, message = "Title is too large")
    private String title;

    @NotNull
    @NotBlank(message = "Text should not be empty")
    @Size(max = 65000, message = "Text is too large")
    @Lob
    private String text;

    @Size(max = 1000, message = "Too many tags")
    private String tagList;

    private Set<Tag> tagSet;


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

    public String getTagList() {
        return tagList;
    }

    public void setTagList(String tagList) {
        this.tagList = tagList;
    }

    public Set<Tag> getTagSet() {
        return tagSet;
    }

    public void setTagSet(Set<Tag> tagSet) {
        this.tagSet = tagSet;
    }
}
