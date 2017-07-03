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
 * The Class DatatableSearchQueryColumn.
 *
 * @author Christian Cougourdan
 */
public class DatatableSearchQueryColumn {

    /** Column's data source. */
    private String data;

    /** Column's name. */
    private String name;

    /** Flag to indicate if this column is searchable. */
    private Boolean searchable;

    /** Flag to indicate if this column is orderable. */
    private Boolean orderable;

    /** Column's specific search value. */
    private DatatableSearchQuerySearch search;

    /**
     * Instantiates a new datatable search query column.
     */
    public DatatableSearchQueryColumn() {
        this.search = new DatatableSearchQuerySearch();
    }

    /**
     * Gets the data.
     *
     * @return the data
     */
    public String getData() {
        return this.data;
    }

    /**
     * Sets the data.
     *
     * @param data            the data to set
     * @return datatable search query column
     */
    public DatatableSearchQueryColumn setData(final String data) {
        this.data = data;
        return this;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name.
     *
     * @param name            the name to set
     * @return datatable search query column
     */
    public DatatableSearchQueryColumn setName(final String name) {
        this.name = name;
        return this;
    }

    /**
     * Checks if is searchable.
     *
     * @return the searchable
     */
    public Boolean isSearchable() {
        return this.searchable;
    }

    /**
     * Sets the searchable.
     *
     * @param searchable            the searchable to set
     * @return datatable search query column
     */
    public DatatableSearchQueryColumn setSearchable(final Boolean searchable) {
        this.searchable = searchable;
        return this;
    }

    /**
     * Checks if is orderable.
     *
     * @return the orderable
     */
    public Boolean isOrderable() {
        return this.orderable;
    }

    /**
     * Sets the orderable.
     *
     * @param orderable            the orderable to set
     * @return datatable search query column
     */
    public DatatableSearchQueryColumn setOrderable(final Boolean orderable) {
        this.orderable = orderable;
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
     * @param search            the search to set
     * @return datatable search query column
     */
    public DatatableSearchQueryColumn setSearch(final DatatableSearchQuerySearch search) {
        this.search = search;
        return this;
    }

}
