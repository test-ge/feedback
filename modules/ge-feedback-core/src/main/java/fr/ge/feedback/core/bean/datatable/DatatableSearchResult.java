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
 * The Class DatatableSearchResult.
 *
 * @author Christian Cougourdan
 * @param <T>
 *            the generic type
 */
public class DatatableSearchResult<T> {

    /** Draw counter. */
    private int draw;

    /** Total records, before filtering. */
    private int recordsTotal;

    /** Total records, after filtering. */
    private int recordsFiltered;

    /** Data to be displayed. */
    private List<T> data;

    /**
     * Instantiates a new datatable search result.
     */
    public DatatableSearchResult() {
        // Nothing to do
    }

    /**
     * Instantiates a new datatable search result.
     *
     * @param draw
     *            the draw
     */
    public DatatableSearchResult(final int draw) {
        this.draw = draw;
    }

    /**
     * Gets the draw.
     *
     * @return the draw
     */
    public int getDraw() {
        return this.draw;
    }

    /**
     * Sets the draw.
     *
     * @param draw
     *            the draw to set
     * @return datatable search result
     */
    public DatatableSearchResult<T> setDraw(final int draw) {
        this.draw = draw;
        return this;
    }

    /**
     * Gets the records total.
     *
     * @return the recordsTotal
     */
    public int getRecordsTotal() {
        return this.recordsTotal;
    }

    /**
     * Sets the records total.
     *
     * @param recordsTotal
     *            the recordsTotal to set
     * @return datatable search result
     */
    public DatatableSearchResult<T> setRecordsTotal(final int recordsTotal) {
        this.recordsTotal = recordsTotal;
        return this;
    }

    /**
     * Gets the records filtered.
     *
     * @return the recordsFiltered
     */
    public int getRecordsFiltered() {
        return this.recordsFiltered;
    }

    /**
     * Sets the records filtered.
     *
     * @param recordsFiltered
     *            the recordsFiltered to set
     * @return datatable search result
     */
    public DatatableSearchResult<T> setRecordsFiltered(final int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
        return this;
    }

    /**
     * Gets the data.
     *
     * @return the data
     */
    public List<T> getData() {
        return this.data;
    }

    /**
     * Sets the data.
     *
     * @param data
     *            the data to set
     * @return datatable search result
     */
    public DatatableSearchResult<T> setData(final List<T> data) {
        this.data = Optional.ofNullable(data).map(lst -> new ArrayList<>(data)).orElse(null);
        return this;
    }

}
