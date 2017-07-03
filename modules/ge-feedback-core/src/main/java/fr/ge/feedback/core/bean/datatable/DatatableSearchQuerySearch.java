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
package fr.ge.feedback.core.bean.datatable;

/**
 * The Class DatatableSearchQuerySearch.
 *
 * @author Christian Cougourdan
 */
public class DatatableSearchQuerySearch {

    /** Global search value. */
    private String value;

    /** Flag to indicate if this search criteria is a regular expression. */
    private Boolean regex;

    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Sets the value.
     *
     * @param value            the value to set
     * @return datatable search query search
     */
    public DatatableSearchQuerySearch setValue(final String value) {
        this.value = value;
        return this;
    }

    /**
     * Checks if is regex.
     *
     * @return the regex
     */
    public Boolean isRegex() {
        return this.regex;
    }

    /**
     * Sets the regex.
     *
     * @param regex            the regex to set
     * @return datatable search query search
     */
    public DatatableSearchQuerySearch setRegex(final Boolean regex) {
        this.regex = regex;
        return this;
    }

}
