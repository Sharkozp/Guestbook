package om.dykyi.dao.factory;

import om.dykyi.dao.guestbook.MysqlGuestbookDAO;
import org.apache.log4j.Logger;

public abstract class DAOFactory {
    /** Logging */
    public static final Logger LOGGER = Logger.getLogger(DAOFactory.class);
    public static final int MYSQL = 1;
    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL:
                return new MysqlDAOFactory();
            default           :
                return null;
        }
    }
}
