package ru.itmo.wp.form.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.domain.Tag;
import ru.itmo.wp.form.PostForm;
import ru.itmo.wp.repository.TagRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class PostFormValidator implements Validator {
    private final TagRepository tagRepository;

    @Autowired
    public PostFormValidator(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PostForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            PostForm postForm = (PostForm) target;
            Set<Tag> tags = new HashSet<>();

            if (!postForm.getTagList().isEmpty()) {
                String[] parsedTags = postForm.getTagList().split("\\s+");
                for (String tagName : parsedTags) {
                    if (!tagName.matches("^[A-Za-z]+$")) {
                        errors.rejectValue("tagList", "latin-letters-only",
                                "Tags should contain only latin letters");
                        return;
                    }
                    Tag tag = tagRepository.findByName(tagName);
                    if (tag == null) {
                        tag = new Tag(tagName);
                        tagRepository.save(tag);
                    }
                    tags.add(tag);
                }
            }
            postForm.setTagSet(tags);
        }
    }
}
