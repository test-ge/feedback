/**
 * 
 */
package fr.ge.feedback.service.bean;

/**
 * @author bsadil
 *
 */
public class TokenBean extends AbstractDatedBean<TokenBean> {

    /**
     * 
     */
    private String tocken;

    /**
     * 
     */
    private String ip;

    /**
     * 
     */
    public TokenBean() {
        super();
        // Nothing to do
    }

    /**
     * @return the tocken
     */
    public String getTocken() {
        return tocken;
    }

    /**
     * @param tocken
     *            the tocken to set
     */
    public void setTocken(String tocken) {
        this.tocken = tocken;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     *            the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

}