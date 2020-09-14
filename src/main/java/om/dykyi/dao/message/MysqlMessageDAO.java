package om.dykyi.dao.message;

import om.dykyi.beans.Answer;
import om.dykyi.beans.Message;
import om.dykyi.dao.GenericDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Паттерн DAO обслуживающий базу данных и сообщения
 *
 * @author Oleksandr Dykyi
 * @version 2.0
 */
public class MysqlMessageDAO extends GenericDAO implements MessageDAO {
    private final static String INSERT_MESSAGE = "INSERT INTO messages (message_text, is_for_all,message_time,author_name,author_ip,phone,email,guestbook_id) values(?,?,?,?,?,?,?,?)";
    private final static String GET_MESSAGES_BY_GUESTBOOK_ID = "SELECT * FROM messages LEFT JOIN answers ON messages.message_id = answers.message_id LEFT JOIN moderators ON answers.moderator_id = moderators.moderator_id LEFT JOIN users ON moderators.user_id = users.user_id WHERE messages.guestbook_id = ? ORDER BY message_time DESC";
    private final static String GET_MESSAGE_BY_ID = "SELECT * FROM messages WHERE message_id = ?";
   // private final static String ADD_ANSWER = "INSERT INTO messages (answer_id) VALUES(?) WHERE message_id = ?";

    private final static String DELETE_MESSAGE_BY_ID = "DELETE FROM messages WHERE message_id = ?";
    private final static String UPDATE_MESSAGE = "UPDATE messages SET message_text = ?, is_for_all = ? WHERE message_id = ?";

    private final static String count = "select count(message_id) as messagecount from messages WHERE guestbook_id=?";

    public MysqlMessageDAO(Connection connection) {
        super(connection);
    }

    /**
     * Add new message
     *
     * @param message Message
     */
    public int addMessage(Message message) {
        int result = 0;
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(INSERT_MESSAGE);
            pst.setString(1, message.getMessage());
            int forAll =  message.isForAll() ? 1: 0;
            pst.setInt(2, forAll);
            pst.setObject(3, message.getTimeCreation(), java.sql.Types.TIMESTAMP);
            pst.setString(4, message.getAuthorName());
            pst.setString(5, message.getAuthorIP());
            pst.setString(6, message.getPhone());
            pst.setString(7, message.getEmail());
            pst.setInt(8, message.getGuestbookId());
            result = pst.executeUpdate();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        } finally {
            if(pst != null) {
                try {
                    pst.close();
                } catch (SQLException throwables) {
                    LOGGER.error(throwables.getMessage());
                }
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                LOGGER.error(throwables.getMessage());
            }
        }

        return result;
    }

    /**
     * Update message
     *
     * @param message Message
     */
    public boolean updateMessage(Message message) {
        boolean result = false;
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(UPDATE_MESSAGE);
            pst.setString(1, message.getMessage());
            int forAll = message.isForAll() ? 1: 0;
            pst.setInt(2, forAll);
            pst.setInt(3, message.getId());
            int lines = pst.executeUpdate();
            result = lines > 0;
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        } finally {
            if(pst != null) {
                try {
                    pst.close();
                } catch (SQLException throwables) {
                    LOGGER.error(throwables.getMessage());
                }
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                LOGGER.error(throwables.getMessage());
            }
        }

        return result;
    }

    /**
     * Add new answer by message ID
     *
     * @param answerId answer ID
     * @param messageId message ID
     */
 /*   public boolean addAnswer(int answerId, int messageId) {
        boolean result = false;
        try {
            PreparedStatement pst = connection.prepareStatement(ADD_ANSWER);
            pst.setInt(1, answerId);
            pst.setInt(2, messageId);
            int lines = pst.executeUpdate();
            result = lines > 0;
            pst.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }

        return result;
    }*/

    /**
     * Get message by ID
     *
     * @param messageId message ID
     * @return Message
     */
    public Message getMessage(int messageId) {
        Message message = null;
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(GET_MESSAGE_BY_ID);
            pst.setInt(1, messageId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                message = new Message(
                        rs.getString("message_text"),
                        rs.getBoolean("is_for_all"),
                        rs.getTimestamp("message_time"),
                        rs.getString("AUTHOR_NAME"),
                        rs.getString("AUTHOR_IP"),
                        rs.getString("PHONE"),
                        rs.getString("EMAIL"),
                        rs.getInt("guestbook_id")
                );
            }
            rs.close();
            pst.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        } finally {
            if(pst != null) {
                try {
                    pst.close();
                } catch (SQLException throwables) {
                    LOGGER.error(throwables.getMessage());
                }
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                LOGGER.error(throwables.getMessage());
            }
        }

        return message;
    }

    /**
     * Метод возвращает количетсва сообщений в книге
     *
     * @param guestbook имя книги
     * @return
     */
    public int getMessageCount(String guestbook) {
        int countMsg = 0;
        try {
            PreparedStatement pst = connection.prepareStatement(count);
            pst.setString(1, guestbook);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                countMsg = rs.getInt("messagecount");
            }
            rs.close();
            pst.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
        return countMsg;
    }

    /**
     * Delete message by ID
     *
     * @param messageId message ID
     */
    public boolean deleteMessage(int messageId) {
        boolean result = false;
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(DELETE_MESSAGE_BY_ID);
            pst.setInt(1, messageId);
            int lines = pst.executeUpdate();
            result = lines > 0;
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        } finally {
            if(pst != null) {
                try {
                    pst.close();
                } catch (SQLException throwables) {
                    LOGGER.error(throwables.getMessage());
                }
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                LOGGER.error(throwables.getMessage());
            }
        }

        return result;
    }

    /**
     * Get Message list by guestbook ID
     *
     * @param guestbookId guestbook ID
     * @return Message list
     */
    public List<Message> getMessageList(int guestbookId) {
        List<Message> list = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = connection.prepareStatement(GET_MESSAGES_BY_GUESTBOOK_ID);
            pst.setInt(1, guestbookId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Answer answer = new Answer(
                        rs.getInt("answer_id"),
                        rs.getString("answer_text"),
                        rs.getString("username"),
                        rs.getTimestamp("answer_time")
                );
                Message message = new Message(
                        rs.getInt("message_id"),
                        rs.getString("message_text"),
                        rs.getBoolean("is_for_all"),
                        rs.getTimestamp("message_time"),
                        rs.getBoolean("is_new_for_admin"),
                        rs.getString("AUTHOR_NAME"),
                        rs.getString("AUTHOR_IP"),
                        rs.getString("PHONE"),
                        rs.getString("EMAIL"),
                        guestbookId,
                        answer
                );
                list.add(message);
            }
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    LOGGER.error(throwables.getMessage());
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException throwables) {
                    LOGGER.error(throwables.getMessage());
                }
            }
            try {
                connection.close();
            } catch (SQLException throwables) {
                LOGGER.error(throwables.getMessage());
            }
        }
        return list;
    }
}
