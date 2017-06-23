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
package fr.ge.feedback.core.bean.search;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class SearchQueryOrder.
 *
 * @author Christian Cougourdan
 */
@XmlRootElement(name = "filter", namespace = "http://v1.ws.markov.ge.fr")
@XmlAccessorType(XmlAccessType.FIELD)
public final class SearchQueryFilter {

    /** The Constant DEFAULT_OPERATOR. */
    private static final String DEFAULT_OPERATOR = ":";

    /** The Constant PATTERN. */
    private static final Pattern PATTERN = Pattern.compile("([a-z0-9_]+)([:<>=~!]+)(.*)", Pattern.CASE_INSENSITIVE);

    /** Column group index. */
    private static final int COLUMN_GROUP_INDEX = 1;

    /** Operator group index. */
    private static final int OPERATOR_GROUP_INDEX = 2;

    /** Value group index. */
    private static final int VALUE_GROUP_INDEX = 3;

    /** The column. */
    private String column;

    /** The operator. */
    private String operator;

    /** The order. */
    private String value;

    /**
     * Instantiates a new search query filter.
     */
    public SearchQueryFilter() {
        // Nothing to do
    }

    /**
     * Instantiates a new search query filter.
     *
     * @param filter
     *            the filter
     */
    public SearchQueryFilter(final String filter) {
        if (null != filter) {
            final Matcher m = PATTERN.matcher(filter);
            if (m.matches()) {
                this.setColumn(m.group(COLUMN_GROUP_INDEX));
                this.setOperator(m.group(OPERATOR_GROUP_INDEX));
                this.setValue(m.group(VALUE_GROUP_INDEX));
            }
        }
    }

    /**
     * Instantiates a new search query order.
     *
     * @param column
     *            the column
     * @param value
     *            the value
     */
    public SearchQueryFilter(final String column, final String value) {
        this(column, null, value);
    }

    /**
     * Instantiates a new search query filter.
     *
     * @param column
     *            the column
     * @param operator
     *            the operator
     * @param value
     *            the value
     */
    public SearchQueryFilter(final String column, final String operator, final String value) {
        this.column = column;
        this.operator = null == operator ? DEFAULT_OPERATOR : operator;
        this.value = value;
    }

    /**
     * Gets the column.
     *
     * @return the column
     */
    public String getColumn() {
        return this.column;
    }

    /**
     * Sets the column.
     *
     * @param column
     *            the new column
     * @return the search query filter
     */
    public SearchQueryFilter setColumn(final String column) {
        this.column = column;
        return this;
    }

    /**
     * Gets the operator.
     *
     * @return the operator
     */
    public String getOperator() {
        return this.operator;
    }

    /**
     * Sets the operator.
     *
     * @param operator
     *            the new operator
     * @return the search query filter
     */
    public SearchQueryFilter setOperator(final String operator) {
        this.operator = operator;
        return this;
    }

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
     * @param value
     *            the new value
     * @return the search query filter
     */
    public SearchQueryFilter setValue(final String value) {
        this.value = value;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%s%s%s", this.column, this.operator, this.value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        } else if (null == obj || !(obj instanceof SearchQueryFilter)) {
            return false;
        }

        final SearchQueryFilter other = (SearchQueryFilter) obj;

        return new EqualsBuilder() //
                .append(this.column, other.column) //
                .append(this.operator, other.operator) //
                .append(this.value, other.value) //
                .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.column).append(this.operator).append(this.value).toHashCode();
    }

}
