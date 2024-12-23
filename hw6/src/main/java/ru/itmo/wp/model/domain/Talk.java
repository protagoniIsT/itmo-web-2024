package ru.itmo.wp.model.domain;

import java.util.Date;

public class Talk implements Model {
    private long id;
    private long sourceUserId;
    private long targetUserId;
    private String text;
    private Date creationTime;

    public Talk() {}

    public Talk(long sourceUserId, long targetUserId, String text) {
        setSourceUserId(sourceUserId);
        setTargetUserId(targetUserId);
        setText(text);
    }

    public long getSourceUserId() {
        return sourceUserId;
    }

    public void setSourceUserId(long sourceUserId) {
        this.sourceUserId = sourceUserId;
    }

    public long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(long targetUserId) {
        this.targetUserId = targetUserId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
