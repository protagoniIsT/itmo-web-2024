package ru.itmo.wp.model.domain;

import java.util.Date;

public class Event implements Model {
    private long id;
    private long userId;
    private EventType type;
    private Date creationTime;

    public Event() {};

    public Event(long userId, EventType type) {
        setUserId(userId);
        setType(type);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
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
