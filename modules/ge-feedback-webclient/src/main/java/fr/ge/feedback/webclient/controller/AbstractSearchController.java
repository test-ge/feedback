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
package fr.ge.feedback.webclient.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.ge.feedback.core.bean.datatable.DatatableSearchQuery;
import fr.ge.feedback.core.bean.datatable.DatatableSearchResult;
import fr.ge.feedback.core.bean.search.SearchQuery;
import fr.ge.feedback.core.bean.search.SearchQueryFilter;
import fr.ge.feedback.core.bean.search.SearchQueryOrder;
import fr.ge.feedback.core.bean.search.SearchResult;

/**
 * Abstract controller used for mapping search page and manage jQuery datatables
 * AJAX search.
 *
 * @author Christian Cougourdan
 * @param <T>
 *            the generic type
 */
public abstract class AbstractSearchController<T> {

    /**
     * Search page mapping.
     *
     * @return search page template name (ie "search/main"), prefixed by
     *         {@link #templatePrefix()}
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String search() {
        return this.templatePrefix() + "/search/main";
    }

    /**
     * Get search filters.
     *
     * @param query
     *            the query
     * @return the filters
     */
    protected List<SearchQueryFilter> getDefaultSearchFilters() {
        final List<SearchQueryFilter> filters = new ArrayList<>();

        return filters;
    }

    /**
     * jQuery datatable search mapping.
     *
     * @param criteria
     *            jQuery datatable search criteria
     * @return search result object
     */
    @RequestMapping(value = "/search/data", method = RequestMethod.GET)
    @ResponseBody
    public DatatableSearchResult<T> searchData(final DatatableSearchQuery criteria, final String[] filters) {
        final DatatableSearchResult<T> datatableSearchResult = new DatatableSearchResult<>(criteria.getDraw());

        final SearchQuery query = new SearchQuery(criteria.getStart(), criteria.getLength());

        if (null != criteria.getOrder()) {
            final List<SearchQueryOrder> orders = criteria.getOrder().stream() //
                    .map(src -> new SearchQueryOrder(criteria.getColumns().get(src.getColumn()).getData(), src.getDir())) //
                    .collect(Collectors.toList());

            query.setOrders(orders);
        }

        final List<SearchQueryFilter> defaultFilters = this.getDefaultSearchFilters();
        if (null != defaultFilters) {
            query.setFilters(defaultFilters);
        }

        if (null != filters) {
            Arrays.stream(filters).forEach(query::addFilter);
        }

        final SearchResult<T> searchResult = this.search(query);
        datatableSearchResult.setRecordsFiltered((int) searchResult.getTotalResults());
        datatableSearchResult.setRecordsTotal((int) searchResult.getTotalResults());

        if (searchResult.getContent() == null) {
            datatableSearchResult.setData(Collections.<T>emptyList());
        } else {
            datatableSearchResult.setData(searchResult.getContent());
        }

        return datatableSearchResult;
    }

    /**
     * Specify template prefix for search main page.
     *
     * @return template prefix
     */
    protected abstract String templatePrefix();

    /**
     * Search.
     *
     * @param query
     *            the query
     * @return search result
     */
    protected abstract SearchResult<T> search(SearchQuery query);

}
