/*
 * Copyright SCN Guichet Entreprises, Capgemini and contributors (2016-2017)
 *
 * This software is a computer program whose purpose is to maintain and
 * administrate standalone forms.
 *
 * This software is governed by the CeCILL  license under French law and
 * abiding by the rules of distribution of free software.  You can  use,
 * modify and/ or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info".
 *
 * As a counterpart to the access to the source code and  rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty  and the software's author,  the holder of the
 * economic rights,  and the successive licensors  have only  limited
 * liability.
 *
 * In this respect, the user's attention is drawn to the risks associated
 * with loading,  using,  modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean  that it is complicated to manipulate,  and  that  also
 * therefore means  that it is reserved for developers  and  experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or
 * data to be ensured and,  more generally, to use and operate it in the
 * same conditions as regards security.
 *
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 */

package fr.ge.feedback.ws.v1.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class IssueBean.
 *
 * @author Christian Cougourdan
 */
@XmlRootElement(name = "message", namespace = "http://v1.ws.markov.ge.fr")
@XmlAccessorType(XmlAccessType.FIELD)
public class IssueBean {

    /**
     * The Enum LEVEL.
     */
    public enum LEVEL {

        /** The info. */
        INFO,
        /** The warn. */
        WARN,
        /** The error. */
        ERROR
    }

    /** The id. */
    private int id;

    /** The level. */
    private LEVEL level;

    /** The category. */
    private String category;

    /** The content. */
    private String content;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the id.
     *
     * @param id
     *            the new id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Gets the level.
     *
     * @return the level
     */
    public LEVEL getLevel() {
        return this.level;
    }

    /**
     * Sets the level.
     *
     * @param level
     *            the new level
     */
    public void setLevel(final LEVEL level) {
        this.level = level;
    }

    /**
     * Gets the category.
     *
     * @return the category
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Sets the category.
     *
     * @param category
     *            the new category
     */
    public void setCategory(final String category) {
        this.category = category;
    }

    /**
     * Gets the content.
     *
     * @return the content
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Sets the content.
     *
     * @param content
     *            the new content
     */
    public void setContent(final String content) {
        this.content = content;
    }

}
