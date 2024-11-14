package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.database.DatabaseUtils;
import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.EventType;
import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.repository.TalkRepository;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TalkRepositoryImpl extends BasicRepositoryImpl<Talk> implements TalkRepository {
    private final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();

    @Override
    public void save(Talk talk) {
        saveBasic(talk,
                  new Pair("sourceUserId", talk.getSourceUserId()),
                  new Pair("targetUserId", talk.getTargetUserId()),
                  new Pair("text", talk.getText()));
    }

    @Override
    public List<Talk> findMessagesByUserId(long userId) {
        List<Talk> talks = findAllBasic();
        List<Talk> res = new ArrayList<>();
        for (Talk talk : talks) {
            if (talk.getSourceUserId() == userId || talk.getTargetUserId() == userId) {
                res.add(talk);
            }
        }
        return res;
    }

    protected Talk toModel(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        Talk talk = new Talk();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    talk.setId(resultSet.getLong(i));
                    break;
                case "sourceUserId":
                    talk.setSourceUserId(resultSet.getLong(i));
                    break;
                case "targetUserId":
                    talk.setTargetUserId(resultSet.getLong(i));
                    break;
                case "text":
                    talk.setText(resultSet.getString(i));
                    break;
                case "creationTime":
                    talk.setCreationTime(resultSet.getTimestamp(i));
                    break;
                default:
                    // No operations.
            }
        }

        return talk;
    }

    public String getName() {
        return "Talk";
    }
}
