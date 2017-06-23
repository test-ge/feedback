package fr.ge.feedback.service.bean;

/**
 * Enum StepStatusEnum.
 *
 * @author bsadil
 */
public enum ContextFeedBackEnum {

    /** in progress. */
    WORDPRESS("WORDPRESS"),

    /** done. */
    NASH("NASH"),

    /** to do. */
    DASHBOARD("DASHBOARD");

    /** status. */
    private String context;

    /**
     * Instantie un nouveau step status enum.
     *
     * @param status
     *            status
     */
    ContextFeedBackEnum(final String status) {
        this.context = status;
    }

    /**
     * Getter on attribute {@link #status}.
     *
     * @return the status
     */
    public String getContext() {
        return this.context;
    }

    /**
     * Setter on attribute {@link #status}.
     *
     * @param status
     *            the status to set
     */
    public void setContext(final String status) {
        this.context = status;
    }

}
