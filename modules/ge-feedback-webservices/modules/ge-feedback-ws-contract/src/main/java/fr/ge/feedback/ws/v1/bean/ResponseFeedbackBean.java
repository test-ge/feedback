/**
 * 
 */
package fr.ge.feedback.ws.v1.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author bsadil
 *
 */

@XmlRootElement(name = "messageQueue", namespace = "http://v1.ws.feedback.ge.fr")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseFeedbackBean extends AbstractResponseDatedBean {

    /**
     * the comment of feedback
     */
    private String comment;

    /**
     * the page of feedback
     */
    private String page;

    /**
     * the score of feedBack
     */
    private String rate;

    /**
     * 
     */
    public ResponseFeedbackBean() {
        super();
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     *            the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the page
     */
    public String getPage() {
        return page;
    }

    /**
     * @param page
     *            the page to set
     */
    public void setPage(String page) {
        this.page = page;
    }

    /**
     * @return the score
     */
    public String getRate() {
        return rate;
    }

    /**
     * @param score
     *            the score to set
     */
    public void setRate(String rate) {
        this.rate = rate;
    }

}
