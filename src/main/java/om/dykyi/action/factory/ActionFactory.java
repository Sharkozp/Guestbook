package om.dykyi.action.factory;

import org.apache.log4j.Logger;

/**
 * ActionFactory - Factory pattern. Эта фабрика преобразовывает
 * названия действий в запросы классов om.dykyi.action, которые может использовать для
 * работы ControllerServlet
 *
 * @author Oleksandr Dykyi
 * @version 2.0
 */
public class ActionFactory {
    public static final Logger LOGGER = Logger.getLogger(ActionFactory.class);

    /**
     * Метод выполняет выборку классов по параметру и его инициализацию.
     *
     * @param actionName команда в JSP странице (часть имени класса без слова
     * Action).
     * @return экземпляр класса.
     */
    public Action create(String actionName) {
        Class newClass = null;
        try {
            newClass = Class.forName("om.dykyi.action." + actionName + "Action");
        } catch (ClassNotFoundException cnfe) {
            try {
                newClass = Class.forName("om.dykyi.action.admin.book." + actionName + "Action");
            } catch (ClassNotFoundException cne) {
                try {
                    newClass = Class.forName("om.dykyi.action.admin.user." + actionName + "Action");
                } catch (ClassNotFoundException ce) {
                    try {
                        newClass = Class.forName("om.dykyi.action.moderator." + actionName + "Action");
                    } catch (ClassNotFoundException e) {
                        LOGGER.error("Class not found");
                    }
                }
            }
        }

        Action actionInstance = null;
        try {
            actionInstance = (Action) newClass.newInstance();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return actionInstance;
    }
}
