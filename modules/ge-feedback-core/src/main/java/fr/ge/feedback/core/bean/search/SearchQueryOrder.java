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

import java.util.Locale;
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
@XmlRootElement(name = "order", namespace = "http://v1.ws.markov.ge.fr")
@XmlAccessorType(XmlAccessType.FIELD)
public final class SearchQueryOrder {

    /** The Constant ASC. */
    public static final String ASC = "asc";

    /** The Constant DESC. */
    public static final String DESC = "desc";

    /** The Constant SEPARATOR. */
    private static final String SEPARATOR = ":";

    /** The Constant PATTERN. */
    private static final Pattern PATTERN = Pattern.compile("([a-z0-9_]+)(?:[" + SEPARATOR + "](.*))?", Pattern.CASE_INSENSITIVE);

    /** The column. */
    private String column;

    /** The order. */
    private String order = ASC;

    /**
     * Instantiates a new search query order.
     */
    public SearchQueryOrder() {
        // Nothing to do
    }

    /**
     * Instantiates a new search query order.
     *
     * @param order
     *            the complete order term
     */
    public SearchQueryOrder(final String order) {
        if (null != order) {
            final Matcher m = PATTERN.matcher(order);
            if (m.matches()) {
                this.setColumn(m.group(1));
                this.setOrder(m.group(2));
            }
        }
    }

    /**
     * Instantiates a new search query order.
     *
     * @param column
     *            the column
     * @param order
     *            the order
     */
    public SearchQueryOrder(final String column, final String order) {
        this.setColumn(column);
        this.setOrder(order);
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
     * @return the search query order
     */
    public SearchQueryOrder setColumn(final String column) {
        this.column = column;
        return this;
    }

    /**
     * Gets the order.
     *
     * @return the order
     */
    public String getOrder() {
        return this.order;
    }

    /**
     * Sets the order.
     *
     * @param order
     *            the new order
     * @return the search query order
     */
    public SearchQueryOrder setOrder(final String order) {
        if (ASC.equalsIgnoreCase(order) || DESC.equalsIgnoreCase(order)) {
            this.order = order.toLowerCase(Locale.US);
        } else {
            this.order = ASC;
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.column + SEPARATOR + this.order;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        } else if (null == obj || !(obj instanceof SearchQueryOrder)) {
            return false;
        }

        final SearchQueryOrder other = (SearchQueryOrder) obj;

        return new EqualsBuilder() //
                .append(this.column, other.column) //
                .append(this.order, other.order) //
                .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.column).append(this.order).toHashCode();
    }

}
