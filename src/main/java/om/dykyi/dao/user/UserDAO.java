package om.dykyi.dao.user;

import om.dykyi.beans.User;

public interface UserDAO {
    public User getUser(String username);
    public String getUserDigest(String username);
}
