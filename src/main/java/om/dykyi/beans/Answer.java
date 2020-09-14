package om.dykyi.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Answer implements Serializable {
    private int id;
    private String answerText;
    private String moderatorName;
    private Date answerTime;

    public Answer(int id, String answerText, String moderatorName, Date answerTime) {
        this.id = id;
        this.answerText = answerText;
        this.moderatorName = moderatorName;
        this.answerTime = answerTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Метод возращает ответ на сообщение
     *
     * @return ответ на сообщение
     */
    public String getAnswerText() {
        return answerText;
    }

    /**
     * Метод принимает ответ на сообщение
     *
     * @param answerText ответ на сообщение
     */
    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    /**
     * Метод возращает имя модератора ответившего на сообщение
     *
     * @return имя модератора ответившего на сообщение
     */
    public String getModeratorName() {
        return moderatorName;
    }

    /**
     * Метод принимает имя модератора ответившего на сообщение
     *
     * @param moderatorName имя модератора ответившего на сообщение
     */
    public void setModeratorName(String moderatorName) {
        this.moderatorName = moderatorName;
    }

    /**
     * Метод возращает время ответа
     *
     * @return время ответа
     */
    public Date getAnswerTime() {
        return answerTime;
    }

    /**
     * Метод принимает время ответа
     *
     * @param answerTime время ответа
     */
    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return id == answer.id &&
                moderatorName.equals(answer.moderatorName) &&
                answerText.equals(answer.answerText) &&
                answerTime.equals(answer.answerTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, answerText, moderatorName, answerTime);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answerText='" + answerText + '\'' +
                ", moderatorName=" + moderatorName +
                ", answerTime=" + answerTime +
                '}';
    }
}
