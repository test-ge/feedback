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
    private String token;

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
    public String getToken() {
        return token;
    }

    /**
     * @param tocken
     *            the tocken to set
     */
    public void setToken(String token) {
        this.token = token;
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