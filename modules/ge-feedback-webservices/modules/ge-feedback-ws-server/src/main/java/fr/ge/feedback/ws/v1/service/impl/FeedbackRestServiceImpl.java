/**
 * 
 */
package fr.ge.feedback.ws.v1.service.impl;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import fr.ge.feedback.core.bean.search.SearchQuery;
import fr.ge.feedback.core.bean.search.SearchQueryFilter;
import fr.ge.feedback.core.bean.search.SearchQueryOrder;
import fr.ge.feedback.core.bean.search.SearchResult;
import fr.ge.feedback.service.IFeedbackService;
import fr.ge.feedback.service.bean.FeedbackBean;
import fr.ge.feedback.ws.v1.bean.ResponseFeedbackBean;
import fr.ge.feedback.ws.v1.service.IFeedbackRestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author bsadil
 *
 */
@Api("Feedback Rest Services")
@Path("/v1/feedback")
public class FeedbackRestServiceImpl implements IFeedbackRestService {

    @Autowired
    IFeedbackService feedbackService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Response createFeedBack(@ApiParam("User feedback  for specific page ") String comment, @ApiParam("URI of page ") String page, @ApiParam("User evaluation") Long rate) {
        final FeedbackBean feedback = new FeedbackBean();
        feedback.setComment(comment);
        feedback.setPage(page);
        feedback.setRate(rate);
        feedback.setCreated(new Date());
        feedback.setUpdated(new Date());

        this.feedbackService.create(feedback);

        return Response.ok().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response updateFeedBack(@ApiParam("User feedback  for specific page ") String comment, @ApiParam("URI of page ") String page, @ApiParam("User evaluation") Long rate,
            @ApiParam("id of feedback") Long id) {

        final FeedbackBean feedback = new FeedbackBean();
        feedback.setId(id);
        feedback.setComment(comment);
        feedback.setPage(page);
        feedback.setRate(rate);
        feedback.setUpdated(new Date());

        this.feedbackService.update(feedback);

        return Response.ok().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response deleteFeedBack(@ApiParam("id of feedback") Long id) {

        this.feedbackService.deleteById(id);

        return Response.ok().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @ApiOperation(value = "Search for queue messages", notes = "Multiple filters can be specified using pattern\n" //
            + "&lt;fieldName&gt;&lt;operator&gt;&lt;value&gt, where \"operator\" can be :\n" //
            + "- \":\" = equals\n" //
            + "- \"&gt;\" = greater than\n" //
            + "- \"&gt;=\" = greater than or equals\n" //
            + "- \"&lt;\" = less than\n" //
            + "- \"&lt;=\" = less than or equals\n")
    public SearchResult<ResponseFeedbackBean> search(@ApiParam("page's first element index") final long startIndex, //
            @ApiParam("max element per page") final long maxResults, //
            @ApiParam("filters as \"&lt;fieldName&gt;&lt;operator&gt;&lt;value&gt;\"") final List<SearchQueryFilter> filters, //
            @ApiParam("orders as \"&lt;fieldName&gt;:&lt;asc|desc&gt;\"") final List<SearchQueryOrder> orders) {
        final SearchQuery searchQuery = new SearchQuery(startIndex, maxResults).setFilters(filters).setOrders(orders);
        return this.feedbackService.search(searchQuery, ResponseFeedbackBean.class);
    }

}
