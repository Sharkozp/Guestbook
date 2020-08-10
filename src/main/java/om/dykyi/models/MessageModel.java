package om.dykyi.models;

import om.dykyi.otherpack.Message;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Паттерн DAO обслуживающий базу данных и сообщения
 *
 * @author Дикий Александр Николаевич
 * @version 1.1
 */
public class MessageModel extends AbstractModel {
    private final static String select = "select * from t_message where GUESTBOOK_NAME=?"
            + " order by time_creation desc";
    private final static String get = "select msg_text from t_message where id=?";
    private final static String insert = "INSERT INTO t_message (guestbook_name,msg_text,"
            + "for_all,time_creation,is_new4admin,author_name,author_ip,phone,"
            + "e_mail,icq) values(?,?,?,?,?,?,?,?,?,?)";
    private final static String delete = "delete from t_message where id=?";
    private final static String update = "update t_message set msg_text=?, for_all=?,"
            + "is_new4admin='0' where id=?";
    private final static String answer = "update t_message set answer_text=?, answer_name=?,"
            + "time_answer=?,is_new4admin='0' where id=?";
    private final static String count = "select count(guestbook_name) as messagecount from t_message WHERE guestbook_name=?";

    /**
     * Constructor
     */
    public MessageModel() {
    }

    /**
     * Метод добавляет в базу данных новое сообщение
     *
     * @param message класс сообщения
     */
    public void addMessage(Message message) {
        try {
            PreparedStatement pst = getPrepareStatement(insert);
            pst.setString(1, message.getGuestbookName());
            pst.setString(2, message.getMessageText());
            if (message.isIsForAll()) {
                pst.setObject(3, 1, java.sql.Types.CHAR);
            } else {
                pst.setObject(3, 0, java.sql.Types.CHAR);
            }
            pst.setObject(4, message.getCreationTime(), java.sql.Types.TIMESTAMP);
            pst.setObject(5, 1, java.sql.Types.CHAR);
            pst.setString(6, message.getCreatorName());
            pst.setString(7, message.getCreatorIP());
            pst.setString(8, message.getPhone());
            pst.setString(9, message.getEmail());
            pst.setString(10, message.getIcq());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
    }

    /**
     * Метод обновления сообщения по параметрам
     *
     * @param text     текст сообщения
     * @param isForAll признак для всех
     * @param msgId    id сообщения
     */
    public void updateMessage(String text, boolean isForAll, int msgId) {
        try {
            PreparedStatement pst = getPrepareStatement(update);
            pst.setString(1, text);
            if (isForAll) {
                pst.setObject(2, 1, java.sql.Types.CHAR);
            } else {
                pst.setObject(2, 0, java.sql.Types.CHAR);
            }
            pst.setInt(3, msgId);
            pst.executeUpdate();
            pst.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
    }

    /**
     * Метод добавляет ответ к сообщению по ID
     *
     * @param answerText текст ответа
     * @param answerName учетное имя ответившего
     * @param date       дата ответа
     * @param msgId      id сообщения
     */
    public void setAnswer(String answerText, String answerName, Date date, int msgId) {
        try {
            PreparedStatement pst = getPrepareStatement(answer);
            pst.setString(1, answerText);
            pst.setString(2, answerName);
            pst.setObject(3, date, java.sql.Types.TIMESTAMP);
            pst.setInt(4, msgId);
            pst.executeUpdate();
            pst.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
    }

    /**
     * Метод возвращает из базы данных тест сообщения по ID
     *
     * @param msgId id сообщения
     * @return
     */
    public String getMessage(int msgId) {
        String msg = null;
        try {
            PreparedStatement pst = getPrepareStatement(get);
            pst.setInt(1, msgId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                msg = rs.getString("MSG_TEXT");
            }
            rs.close();
            pst.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
        return msg;
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
            PreparedStatement pst = getPrepareStatement(count);
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
     * Метод удаляет сообщение по заданому ID
     *
     * @param msgId id сообщения
     */
    public void deleteMessage(int msgId) {
        try {
            PreparedStatement pst = getPrepareStatement(delete);
            pst.setInt(1, msgId);
            pst.executeUpdate();
            pst.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
    }

    /**
     * Возвращает список сообщений из гостевой книги по ее названию
     *
     * @param guestbook имя гостевой книги
     * @return список всех сообщений
     */
    public ArrayList<Message> getMessageList(String guestbook) {
        ArrayList<Message> list = new ArrayList<>();
        try {
            PreparedStatement pst = getPrepareStatement(select);
            pst.setString(1, guestbook);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Message m;
                m = new Message(
                        rs.getInt("ID"),
                        guestbook,
                        rs.getString("MSG_TEXT"),
                        rs.getBoolean("FOR_ALL"),
                        rs.getBoolean("IS_NEW4ADMIN"),
                        rs.getTimestamp("TIME_CREATION"),
                        rs.getString("AUTHOR_NAME"),
                        rs.getString("AUTHOR_IP"),
                        rs.getString("PHONE"),
                        rs.getString("E_MAIL"),
                        rs.getString("ICQ"));

                m.setAnswer(rs.getString("ANSWER_TEXT"),
                        rs.getString("ANSWER_NAME"),
                        rs.getTimestamp("TIME_ANSWER"));
                list.add(m);
            }
            rs.close();
            pst.close();
        } catch (SQLException se) {
            LOGGER.error(se.getMessage());
        }
        return list;
    }
}
