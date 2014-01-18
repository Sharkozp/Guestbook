package action;

import action.factory.Action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AbstractGuestbookAction - ����������� �����. ��������� ���� ����� perfom().
 *
 * @version 1.0
 * @author ����� ��������� ����������
 */
public abstract class AbstractGuestbookAction implements Action {

    /**
     * @param request ������ � ��������
     * @param response ����� ��������
     * @param datasource �������� ������ ��� ���� ������
     * @return URL-�����
     */
    public String perform(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
