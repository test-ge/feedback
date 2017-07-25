/**
 * 
 */
package fr.ge.feedback.ws.v1.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.ge.feedback.core.bean.search.SearchQuery;
import fr.ge.feedback.core.bean.search.SearchQueryFilter;
import fr.ge.feedback.core.bean.search.SearchQueryOrder;
import fr.ge.feedback.core.bean.search.SearchResult;
import fr.ge.feedback.ws.v1.bean.ResponseFeedbackBean;

/**
 * @author bsadil
 *
 */
@Path("/private/v1/feedback")
public interface IFeedbackPrivateRestService {

    /**
     * update a comment for specific page
     *
     * @param comment
     *            message
     * @param page
     *            URI of the page
     * @param rate
     *            u
     * @return
     */
    @DELETE
    @Path("/delete")
    Response deleteFeedBack(@QueryParam("id") Long id);

    /**
     * Search for queue messages.
     *
     * @param startIndex
     *            start index
     * @param maxResults
     *            max results per page
     * @param filters
     *            filters as string
     * @param orders
     *            orders as string
     * @return search result
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    SearchResult<ResponseFeedbackBean> search(@QueryParam("startIndex") @DefaultValue(SearchQuery.DEFAULT_START_INDEX) long startIndex, //
            @QueryParam("maxResults") @DefaultValue(SearchQuery.DEFAULT_MAX_RESULTS) long maxResults, //
            @QueryParam("filters[]") List<SearchQueryFilter> filters, //
            @QueryParam("orders[]") List<SearchQueryOrder> orders //
    );

    /**
     * Retrieves an feedback by `.
     *
     * @param id
     *            the id
     * @return found authority
     */
    @GET
    @Path("/{id : \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    ResponseFeedbackBean get(@PathParam("id") long id);

}
