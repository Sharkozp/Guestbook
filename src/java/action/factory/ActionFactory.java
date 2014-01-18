package action.factory;

import org.apache.log4j.Logger;

/**
 * ActionFactory - ���������� �������� Factory. ��� ������� ���������������
 * �������� �������� � ������� ������� action, ������� ����� ������������ ���
 * ������ ControllerServlet
 *
 * @version 1.0
 * @author ����� ��������� ����������
 */
public class ActionFactory {

    public static final Logger log = Logger.getLogger(ActionFactory.class);

    /**
     * ����� ��������� ������� ������� �� ��������� � ��� �������������.
     *
     * @param actionName ������� � JSP �������� (����� ����� ������ ��� �����
     * Action).
     * @return ��������� ������.
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
                        log.error("����� �� ������");
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
