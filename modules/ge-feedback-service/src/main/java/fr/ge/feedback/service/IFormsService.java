/**
 * 
 */
package fr.ge.feedback.service;

import fr.ge.common.form.engine.provider.IProvider;

/**
 * @author $Author: HZITOUNI $
 * @version $Revision: 0 $
 */
public interface IFormsService {

    /**
     * Markov execution for handling one step execution
     * 
     * @param formsBytes
     *            the form in bytes array with the step to execute
     * @return The forms with the new step to execute for the next Markov
     *         execution.
     */
    // byte[] handleStep(String worker);

    /**
     * @param provider
     * @return true if next step available
     */
    boolean handleStep(IProvider provider);

}
