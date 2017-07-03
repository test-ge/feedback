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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class SearchQuery.
 *
 * @author Christian Cougourdan
 */
public final class SearchQuery {

    /** The Constant DEFAULT_START_INDEX. */
    public static final String DEFAULT_START_INDEX = "0";

    /** The Constant DEFAULT_MAX_RESULTS. */
    public static final String DEFAULT_MAX_RESULTS = "20";

    /** The start index. */
    private long startIndex = Long.parseLong(DEFAULT_START_INDEX);

    /** The max results. */
    private long maxResults = Long.parseLong(DEFAULT_MAX_RESULTS);

    /** The filters. */
    private List<SearchQueryFilter> filters;

    /** The orders. */
    private List<SearchQueryOrder> orders;

    /**
     * Instantiates a new search query.
     */
    public SearchQuery() {
        // Nothing to do
    }

    /**
     * Instantiates a new search query.
     *
     * @param startIndex
     *            the start index
     * @param maxResults
     *            the max results
     */
    public SearchQuery(final long startIndex, final long maxResults) {
        this.startIndex = startIndex;
        this.maxResults = maxResults;
    }

    /**
     * Gets the start index.
     *
     * @return the start index
     */
    public long getStartIndex() {
        return this.startIndex;
    }

    /**
     * Sets the start index.
     *
     * @param startIndex
     *            the new start index
     * @return the search query
     */
    public SearchQuery setStartIndex(final long startIndex) {
        this.startIndex = startIndex;
        return this;
    }

    /**
     * Gets the max results.
     *
     * @return the max results
     */
    public long getMaxResults() {
        return this.maxResults;
    }

    /**
     * Sets the max results.
     *
     * @param maxResults
     *            the new max results
     * @return the search query
     */
    public SearchQuery setMaxResults(final long maxResults) {
        this.maxResults = maxResults;
        return this;
    }

    /**
     * Gets the filters.
     *
     * @return the filters
     */
    public List<SearchQueryFilter> getFilters() {
        return null == this.filters ? null : Collections.unmodifiableList(this.filters);
    }

    /**
     * Sets the filters.
     *
     * @param filters
     *            the filters
     * @return the search query
     */
    public SearchQuery setFilters(final List<SearchQueryFilter> filters) {
        this.filters = null == filters ? null : new ArrayList<>(filters);
        return this;
    }

    /**
     * Gets the orders.
     *
     * @return the orders
     */
    public List<SearchQueryOrder> getOrders() {
        return null == this.orders ? null : Collections.unmodifiableList(this.orders);
    }

    /**
     * Sets the orders.
     *
     * @param orders
     *            the orders
     * @return the search query
     */
    public SearchQuery setOrders(final List<SearchQueryOrder> orders) {
        this.orders = null == orders ? null : new ArrayList<>(orders);
        return this;
    }

    /**
     * Adds the filter.
     *
     * @param filter
     *            the filter
     * @return the search query
     */
    public SearchQuery addFilter(final String filter) {
        return this.addFilter(new SearchQueryFilter(filter));
    }

    /**
     * Adds the filter.
     *
     * @param filter
     *            the filter
     * @return the search query
     */
    public SearchQuery addFilter(final SearchQueryFilter filter) {
        if (null == this.filters) {
            this.filters = new ArrayList<>();
        }

        this.filters.add(filter);

        return this;
    }

    /**
     * Adds the filter.
     *
     * @param column
     *            the column
     * @param value
     *            the value
     * @return the search query
     */
    public SearchQuery addFilter(final String column, final String value) {
        return this.addFilter(column, null, value);
    }

    /**
     * Adds the filter.
     *
     * @param column
     *            the column
     * @param operator
     *            the operator
     * @param value
     *            the value
     * @return the search query
     */
    public SearchQuery addFilter(final String column, final String operator, final String value) {
        final SearchQueryFilter queryFilter = new SearchQueryFilter(column, operator, value);
        if (null == this.filters) {
            this.filters = new ArrayList<>();
        }

        this.filters.add(queryFilter);

        return this;
    }

    /**
     * Adds the orders.
     *
     * @param column
     *            the column
     * @param order
     *            the order
     * @return the search query
     */
    public SearchQuery addOrders(final String column, final String order) {
        final SearchQueryOrder queryOrder = new SearchQueryOrder(column, order);
        if (null == this.orders) {
            this.orders = new ArrayList<>();
        }

        this.orders.add(queryOrder);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("{ startIndex: %d, maxResults: %d, filters: [ %s ], orders: [ %s ] }", //
                this.startIndex, //
                this.maxResults, //
                null == this.filters ? "" : this.filters.stream().map(SearchQueryFilter::toString).collect(Collectors.joining(", ")), //
                null == this.orders ? "" : this.orders.stream().map(SearchQueryOrder::toString).collect(Collectors.joining(", ")) //
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        } else if (null == obj || !(obj instanceof SearchQuery)) {
            return false;
        }

        final SearchQuery other = (SearchQuery) obj;

        return new EqualsBuilder() //
                .append(this.startIndex, other.startIndex) //
                .append(this.maxResults, other.maxResults) //
                .append(this.filters, other.filters) //
                .append(this.orders, other.orders) //
                .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.startIndex).append(this.maxResults).append(this.filters).append(this.orders).toHashCode();
    }

}
