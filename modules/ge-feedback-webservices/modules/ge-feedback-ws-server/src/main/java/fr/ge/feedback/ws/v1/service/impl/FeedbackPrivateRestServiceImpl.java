/**
 * 
 */
package fr.ge.feedback.ws.v1.service.impl;

import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import fr.ge.feedback.core.bean.search.SearchQuery;
import fr.ge.feedback.core.bean.search.SearchQueryFilter;
import fr.ge.feedback.core.bean.search.SearchQueryOrder;
import fr.ge.feedback.core.bean.search.SearchResult;
import fr.ge.feedback.service.IFeedbackService;
import fr.ge.feedback.service.bean.FeedbackBean;
import fr.ge.feedback.ws.v1.bean.ResponseFeedbackBean;
import fr.ge.feedback.ws.v1.service.IFeedbackPrivateRestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author bsadil
 *
 */
@Api("Feedback - Private Rest Services")
@Path("/private/v1/feedback")
public class FeedbackPrivateRestServiceImpl implements IFeedbackPrivateRestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackRestServiceImpl.class);

    private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("[$][{]([^}]+)[}]");

    private String widgetScriptResource;

    @Autowired
    private Properties appProperties;

    @Autowired
    private IFeedbackService feedbackService;

    @Autowired
    private DozerBeanMapper dozer;

    /**
     * {@inheritDoc}
     */
    @Override
    public Response deleteFeedBack(@ApiParam("id of feedback") final Long id) {

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

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseFeedbackBean get(final long id) {
        ResponseFeedbackBean responseFeedback;
        final FeedbackBean feedback = this.feedbackService.findById(id);
        if (feedback == null) {
            responseFeedback = new ResponseFeedbackBean();
        } else {
            responseFeedback = this.dozer.map(feedback, ResponseFeedbackBean.class);
        }
        return responseFeedback;
    }
}
