/**
 * 
 */
package fr.ge.feedback.service.bean;

/**
 * @author bsadil
 *
 */
public class FeedbackBean extends AbstractDatedBean<FeedbackBean> {

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
    private Long rate;

    /**
     * 
     */
    public FeedbackBean() {
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
    public Long getRate() {
        return rate;
    }

    /**
     * @param score
     *            the score to set
     */
    public void setRate(Long rate) {
        this.rate = rate;
    }

}
