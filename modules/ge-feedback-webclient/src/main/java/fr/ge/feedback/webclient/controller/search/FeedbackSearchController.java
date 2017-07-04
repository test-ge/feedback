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
package fr.ge.feedback.webclient.controller.search;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.ge.feedback.core.bean.search.SearchQuery;
import fr.ge.feedback.core.bean.search.SearchQueryFilter;
import fr.ge.feedback.core.bean.search.SearchResult;
import fr.ge.feedback.webclient.controller.AbstractSearchController;
import fr.ge.feedback.ws.v1.bean.ResponseFeedbackBean;
import fr.ge.feedback.ws.v1.service.IFeedbackRestService;

/**
 * The Class RecordSearchController.
 *
 * @author Christian Cougourdan
 */
@Controller
@RequestMapping("/feedback")
public class FeedbackSearchController extends AbstractSearchController<ResponseFeedbackBean> {

    /** The service. */
    @Autowired
    private IFeedbackRestService feedbackRestService;

    /**
     * {@inheritDoc}
     */
    @Override
    protected String templatePrefix() {
        return "feedback";
    }

    /**
     * Get search filters.
     *
     * @param query
     *            the query
     * @return the filters
     */
    @Override
    protected List<SearchQueryFilter> getDefaultSearchFilters() {
        return Collections.emptyList();
    }

    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public String edit(final Model model, @QueryParam("id") final long id) {
        final ResponseFeedbackBean bean = this.feedbackRestService.get(id);

        model.addAttribute("bean", bean);

        return "feedback/display/main";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected SearchResult<ResponseFeedbackBean> search(final SearchQuery query) {
        return this.feedbackRestService.search(query.getStartIndex(), query.getMaxResults(), query.getFilters(), query.getOrders());
    }

}
