package action.factory;

import org.apache.log4j.Logger;

/**
 * ActionFactory - реализация паттерна Factory. Эта фабрика преобразовывает
 * названия действий в запросы классов action, которые может использовать для
 * работы ControllerServlet
 *
 * @version 1.0
 * @author Дикий Александр Николаевич
 */
public class ActionFactory {

    public static final Logger log = Logger.getLogger(ActionFactory.class);

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
            newClass = Class.forName("action." + actionName + "Action");
        } catch (ClassNotFoundException cnfe) {
            try {
                newClass = Class.forName("action.admin.book." + actionName + "Action");
            } catch (ClassNotFoundException cne) {
                try {
                    newClass = Class.forName("action.admin.user." + actionName + "Action");
                } catch (ClassNotFoundException ce) {
                    try {
                        newClass = Class.forName("action.moderator." + actionName + "Action");
                    } catch (ClassNotFoundException e) {
                        log.error("Класс не найден");
                    }
                }
            }
        }

        Action actionInstance = null;
        try {
            actionInstance = (Action) newClass.newInstance();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return actionInstance;
    }
}
