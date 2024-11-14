package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Talk;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface TalkRepository {
    void save(Talk talk) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;

    List<Talk> findMessagesByUserId(long id);
}
