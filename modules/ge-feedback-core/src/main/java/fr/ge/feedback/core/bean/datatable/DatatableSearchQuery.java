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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The Class DatatableSearchQuery.
 *
 * @author Christian Cougourdan
 */
public class DatatableSearchQuery {

    /** Draw counter. */
    private Integer draw;

    /** Paging first record indicator. */
    private Integer start;

    /** Number of records that the table can display. */
    private Integer length;

    /** Column's specific search value. */
    private DatatableSearchQuerySearch search = new DatatableSearchQuerySearch();

    /** Columns to which ordering should be applied. */
    private List<DatatableSearchQueryOrder> order = new ArrayList<>();

    /** Columns definition. */
    private List<DatatableSearchQueryColumn> columns = new ArrayList<>();

    /**
     * Instantiates a new datatable search query.
     */
    public DatatableSearchQuery() {
        // Nothing to do
    }

    /**
     * Gets the draw.
     *
     * @return the draw
     */
    public Integer getDraw() {
        return this.draw;
    }

    /**
     * Sets the draw.
     *
     * @param draw
     *            the draw to set
     * @return datatable search query
     */
    public DatatableSearchQuery setDraw(final Integer draw) {
        this.draw = draw;
        return this;
    }

    /**
     * Gets the start.
     *
     * @return the start
     */
    public Integer getStart() {
        return this.start;
    }

    /**
     * Sets the start.
     *
     * @param start
     *            the start to set
     * @return datatable search query
     */
    public DatatableSearchQuery setStart(final Integer start) {
        this.start = start;
        return this;
    }

    /**
     * Gets the length.
     *
     * @return the length
     */
    public Integer getLength() {
        return this.length;
    }

    /**
     * Sets the length.
     *
     * @param length
     *            the length to set
     * @return datatable search query
     */
    public DatatableSearchQuery setLength(final Integer length) {
        this.length = length;
        return this;
    }

    /**
     * Gets the search.
     *
     * @return the search
     */
    public DatatableSearchQuerySearch getSearch() {
        return this.search;
    }

    /**
     * Sets the search.
     *
     * @param search
     *            the search to set
     * @return datatable search query
     */
    public DatatableSearchQuery setSearch(final DatatableSearchQuerySearch search) {
        this.search = search;
        return this;
    }

    /**
     * Gets the order.
     *
     * @return the order
     */
    public List<DatatableSearchQueryOrder> getOrder() {
        return this.order;
    }

    /**
     * Sets the order.
     *
     * @param order
     *            the order to set
     * @return datatable search query
     */
    public DatatableSearchQuery setOrder(final List<DatatableSearchQueryOrder> order) {
        this.order = new ArrayList<>(order);
        return this;
    }

    /**
     * Gets the columns.
     *
     * @return the columns
     */
    public List<DatatableSearchQueryColumn> getColumns() {
        return this.columns;
    }

    /**
     * Sets the columns.
     *
     * @param columns
     *            the columns to set
     * @return datatable search query
     */
    public DatatableSearchQuery setColumns(final List<DatatableSearchQueryColumn> columns) {
        this.columns = Optional.ofNullable(columns).map(lst -> new ArrayList<>(lst)).orElse(null);
        return this;
    }

}
