package om.dykyi.dao.answer;

import om.dykyi.dao.GenericDAO;

import java.sql.Connection;

public class MysqlAnswerDAO extends GenericDAO implements AnswerDAO {
    private final static String ADD_ANSWER = "insert into answers (answer_text, moderator_id, time_answer) VALUES (?, ?, ?) where message_id = ?";

    public MysqlAnswerDAO(Connection connection) {
        super(connection);
    }
}
