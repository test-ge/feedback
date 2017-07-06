/**
 *
 */
package fr.ge.feedback.ws.v1.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
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
import fr.ge.feedback.ws.util.CoreUtil;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackRestServiceImpl.class);

    private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("[$][{]([^}]+)[}]");

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
    public Response createFeedBack(@ApiParam("User feedback  for specific page ") final String comment, @ApiParam("URI of page ") final String page, @ApiParam("User evaluation") final Long rate) {
        final FeedbackBean feedback = new FeedbackBean();
        feedback.setComment(StringUtils.substring(comment, 0, 254));
        feedback.setPage(StringUtils.substring(page, 0, 254));
        feedback.setRate(rate);
        feedback.setCreated(new Date());
        feedback.setUpdated(new Date());

        this.feedbackService.create(feedback);

        return Response.ok(feedback.getId()).build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response updateFeedBack(@ApiParam("User feedback  for specific page ") final String comment, @ApiParam("URI of page ") final String page, @ApiParam("User evaluation") final Long rate,
            @ApiParam("id of feedback") final Long id) {

        final FeedbackBean feedback = new FeedbackBean();
        feedback.setId(id);
        feedback.setComment(StringUtils.substring(comment, 0, 254));
        feedback.setPage(StringUtils.substring(page, 0, 254));
        feedback.setRate(rate);
        feedback.setUpdated(new Date());

        this.feedbackService.update(feedback);

        return Response.ok(feedback.getId()).build();
    }

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

    /**
     * {@inheritDoc}
     */
    @Override
    public Response widget() {

        try (final InputStream in = this.getClass().getClassLoader().getResourceAsStream("public/js/widget.js")) {
            final String script = new String(IOUtils.toByteArray(in), StandardCharsets.UTF_8);
            return Response.ok(CoreUtil.searchAndReplace(script, PLACEHOLDER_PATTERN, m -> {
                final String key = m.group(1);
                final String value = this.appProperties.getProperty(key, m.group());
                return value.replace("$", "\\$");
            }), "text/javascript").build();
        } catch (final IOException ex) {
            LOGGER.warn("Unable to read widget script", ex);
        }

        return Response.noContent().build();
    }

}
