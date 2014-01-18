package otherpack;

/**
 * ����� ������ ��������� �������� �����.
 *
 * @version 1.0
 * @author ����� ��������� ����������
 */
public class Guestbook {

    private String name;
    private String description;
    private int displayOrder;

    /**
     * ��������� ������
     *
     * @param name ��� �����
     * @param description �������� �����
     * @param displayOrder ������� �����������
     */
    public Guestbook(String name, String description, int displayOrder) {
        this.name = name;
        this.description = description;
        this.displayOrder = displayOrder;
    }

    /**
     * ����� ���������� ��� �������� �����
     *
     * @return ��� �������� �����
     */
    public String getName() {
        return name;
    }

    /**
     * ����� ���������� �������� �������� �����
     *
     * @return �������� �������� �����
     */
    public String getDescription() {
        return description;
    }

    /**
     * ����� ���������� ������� ����������� �������� �����
     *
     * @return ������� ����������� �������� �����
     */
    public int getDisplayOrder() {
        return displayOrder;
    }
}
