/**
 *
 */
package fr.ge.feedback.ws.v1.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * @author bsadil
 *
 */
@Path("/public/v1/feedback")
public interface IFeedbackRestService {

    /**
     * create a comment for specific page
     *
     * @param comment
     *            message
     * @param page
     *            URI of the page
     * @param rate
     *            u
     * @return
     */
    @POST
    @Path("/create")
    Response createFeedBack(@QueryParam("comment") String comment, @QueryParam("page") String page, @QueryParam("rate") Long rate);

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
    @PUT
    @Path("/update")
    Response updateFeedBack(@QueryParam("comment") String comment, @QueryParam("page") String page, @QueryParam("rate") Long rate, @QueryParam("id") Long id);

    @GET
    @Path("/widget.js")
    Response widget();

}
