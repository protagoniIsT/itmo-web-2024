package ru.itmo.wp.model.service;

import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.repository.impl.TalkRepositoryImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class TalkService {
    TalkRepositoryImpl talkRepository = new TalkRepositoryImpl();

    public void sendMessage(Talk talk) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        talkRepository.save(talk);
    }

    public List<Talk> findMessagesByUserId(long id) {
        return talkRepository.findMessagesByUserId(id);
    }
}
