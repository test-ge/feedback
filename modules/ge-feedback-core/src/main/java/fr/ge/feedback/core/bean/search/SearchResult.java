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
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class SearchResult.
 *
 * @author Christian Cougourdan
 * @param <T>
 *            the generic type
 */
@XmlRootElement(name = "result", namespace = "http://v1.ws.markov.ge.fr")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchResult<T> {

    /** The Constant DEFAULT_MAXRESULTS. */
    public static final long DEFAULT_MAXRESULTS = 10L;

    /** The start index. */
    private long startIndex;

    /** The max results. */
    private long maxResults = DEFAULT_MAXRESULTS;

    /** The total results. */
    private long totalResults;

    /** The content. */
    private List<T> content;

    /**
     * Instantiates a new search result.
     */
    public SearchResult() {
        // Nothing to do
    }

    /**
     * Instantiates a new search result.
     *
     * @param startIndex
     *            the start index
     * @param maxResults
     *            the max results
     */
    public SearchResult(final long startIndex, final long maxResults) {
        this.startIndex = startIndex;
        this.maxResults = maxResults;
    }

    /**
     * Gets the start index.
     *
     * @return the startIndex
     */
    public long getStartIndex() {
        return this.startIndex;
    }

    /**
     * Sets the start index.
     *
     * @param startIndex
     *            the startIndex to set
     */
    public void setStartIndex(final long startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * Gets the max results.
     *
     * @return the maxResults
     */
    public long getMaxResults() {
        return this.maxResults;
    }

    /**
     * Sets the max results.
     *
     * @param maxResults
     *            the maxResults to set
     */
    public void setMaxResults(final long maxResults) {
        this.maxResults = maxResults;
    }

    /**
     * Gets the total results.
     *
     * @return the totalResults
     */
    public long getTotalResults() {
        return this.totalResults;
    }

    /**
     * Sets the total results.
     *
     * @param totalResults
     *            the totalResults to set
     */
    public void setTotalResults(final long totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * Gets the content.
     *
     * @return the content
     */
    public List<T> getContent() {
        return this.content;
    }

    /**
     * Sets the content.
     *
     * @param content
     *            the content to set
     */
    public void setContent(final List<T> content) {
        if (null == content) {
            this.content = null;
        } else {
            this.content = new ArrayList<>(content);
        }
    }

    public static class Builder<T> {

        /** The start index. */
        private long _startIndex;

        /** The max results. */
        private long _maxResults = DEFAULT_MAXRESULTS;

        /** The total results. */
        private long _totalResults;

        /** The content. */
        private List<T> _content;

        /**
         * Start index.
         *
         * @param value
         *            the value
         * @return the builder
         */
        public Builder<T> startIndex(final long value) {
            this._startIndex = value;
            return this;
        }

        /**
         * Max results.
         *
         * @param value
         *            the value
         * @return the builder
         */
        public Builder<T> maxResults(final long value) {
            this._maxResults = value;
            return this;
        }

        /**
         * Total results.
         *
         * @param value
         *            the value
         * @return the builder
         */
        public Builder<T> totalResults(final long value) {
            this._totalResults = value;
            return this;
        }

        /**
         * Content.
         *
         * @param value
         *            the value
         * @return the builder
         */
        public Builder<T> content(@SuppressWarnings("unchecked") final T... value) {
            this._content = Arrays.asList(value);
            return this;
        }

        /**
         * Builds the.
         *
         * @return the search result
         */
        public SearchResult<T> build() {
            final SearchResult<T> obj = new SearchResult<>();
            obj.setStartIndex(this._startIndex);
            obj.setMaxResults(this._maxResults);
            obj.setTotalResults(this._totalResults);
            obj.setContent(this._content);
            return obj;
        }

    }

}
