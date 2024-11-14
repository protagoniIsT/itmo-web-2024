package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Event;

import java.lang.reflect.InvocationTargetException;

public interface EventRepository {
    void save(Event event) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;
}
