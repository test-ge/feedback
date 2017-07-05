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

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fr.ge.feedback.core.bean.datatable.DatatableSearchQuery;
import fr.ge.feedback.core.bean.datatable.DatatableSearchQueryColumn;
import fr.ge.feedback.core.bean.datatable.DatatableSearchQueryOrder;
import fr.ge.feedback.core.bean.datatable.DatatableSearchResult;
import fr.ge.feedback.core.bean.search.SearchQueryOrder;
import fr.ge.feedback.core.bean.search.SearchResult;
import fr.ge.feedback.ws.v1.bean.ResponseFeedbackBean;

/**
 * The Class AbstractSearchControllerTest.
 *
 * @author Christian Cougourdan
 */
@RunWith(MockitoJUnitRunner.class)
public class AbstractSearchControllerTest {

    /** The controller. */
    @Mock
    private AbstractSearchController<ResponseFeedbackBean> controller;

    /** The order captor. */
    @Captor
    private ArgumentCaptor<List<String>> orderCaptor;

    /** search query captor. */
    @Captor
    private ArgumentCaptor<fr.ge.feedback.core.bean.search.SearchQuery> searchQueryCaptor;

    /**
     * Sets the up.
     *
     * @throws Exception
     *             the exception
     */
    @Before
    @SuppressWarnings("unchecked")
    public void setUp() throws Exception {
        reset(this.controller);
        when(this.controller.search()).thenCallRealMethod();
        when(this.controller.searchData(any(), any())).thenCallRealMethod();
    }

    /**
     * Test search init.
     */
    @Test
    public void testSearchInit() {
        when(this.controller.templatePrefix()).thenReturn("feedback");
        final String actual = this.controller.search();

        assertEquals("feedback/search/main", actual);
    }

    /**
     * Test search data empty no order.
     */
    @Test
    public void testSearchDataEmptyNoOrder() {
        final SearchResult<ResponseFeedbackBean> searchResult = new SearchResult.Builder<ResponseFeedbackBean>().startIndex(0).maxResults(10).totalResults(0).build();

        when(this.controller.search(any())).thenReturn(searchResult);

        final DatatableSearchResult<ResponseFeedbackBean> actual = this.controller.searchData(new DatatableSearchQuery().setDraw(1).setStart(0).setLength(10), null);

        assertEquals(1, actual.getDraw());
        assertEquals(0, actual.getData().size());
        assertEquals(0, actual.getRecordsTotal());
        assertEquals(0, actual.getRecordsFiltered());

        verify(this.controller).search(this.searchQueryCaptor.capture());

        assertTrue(this.searchQueryCaptor.getValue().getOrders().isEmpty());
    }

    /**
     * Test search data no order.
     */
    @Test
    public void testSearchDataNoOrder() {
        final SearchResult<ResponseFeedbackBean> searchResult = new SearchResult.Builder<ResponseFeedbackBean>().startIndex(0).maxResults(10).totalResults(2)
                .content(new ResponseFeedbackBean(), new ResponseFeedbackBean()).build();

        when(this.controller.search(any())).thenReturn(searchResult);

        final DatatableSearchResult<ResponseFeedbackBean> actual = this.controller.searchData(new DatatableSearchQuery().setDraw(1).setStart(0).setLength(10), null);

        assertEquals(1, actual.getDraw());
        assertEquals(2, actual.getData().size());
        assertEquals(2, actual.getRecordsTotal());
        assertEquals(2, actual.getRecordsFiltered());

        verify(this.controller).search(this.searchQueryCaptor.capture());

        assertTrue(this.searchQueryCaptor.getValue().getOrders().isEmpty());
    }

    /**
     * Test search data empty.
     */
    @Test
    public void testSearchDataEmpty() {
        final SearchResult<ResponseFeedbackBean> searchResult = new SearchResult.Builder<ResponseFeedbackBean>().startIndex(0).maxResults(10).totalResults(0).build();

        when(this.controller.search(any())).thenReturn(searchResult);

        this.controller.searchData( //
                new DatatableSearchQuery().setDraw(1).setStart(0).setLength(10) //
                        .setColumns(Arrays.asList(new DatatableSearchQueryColumn().setData("col1"))) //
                        .setOrder(Arrays.asList(new DatatableSearchQueryOrder().setColumn(0).setDir("desc"))),
                null //
        );

        verify(this.controller).search(this.searchQueryCaptor.capture());

        final List<SearchQueryOrder> orders = this.searchQueryCaptor.getValue().getOrders();

        assertThat(orders.get(0), allOf(hasProperty("column", equalTo("col1")), hasProperty("order", equalTo("desc"))));
    }

}
