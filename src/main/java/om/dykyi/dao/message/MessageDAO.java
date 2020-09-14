package om.dykyi.dao.message;

import om.dykyi.beans.Message;

import java.util.List;

public interface MessageDAO {
    public int addMessage(Message message);

 //   public boolean addAnswer(int answerId, int messageId);

    //  public Message getGuestbook(int guestbookId);
    //   public Message findGuestbook(Message message);
    public boolean updateMessage(Message message);
    public boolean deleteMessage(int messageId);
    public Message getMessage(int messageId);

    public List<Message> getMessageList(int guestbookId);
}
