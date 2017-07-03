/**
 * 
 */
package fr.ge.feedback.ws.v1.service.impl;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.ge.feedback.core.bean.search.SearchQuery;
import fr.ge.feedback.core.bean.search.SearchQueryFilter;
import fr.ge.feedback.core.bean.search.SearchResult;
import fr.ge.feedback.service.IFeedbackService;
import fr.ge.feedback.service.bean.FeedbackBean;
import fr.ge.feedback.ws.v1.bean.ResponseFeedbackBean;
import fr.ge.feedback.ws.v1.service.IFeedbackRestService;

/**
 * @author bsadil
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/test-context.xml", "classpath:spring/service-context.xml", "classpath:spring/ws-server-cxf-context.xml" })
public class FeedbackRestServiceTest {

    @Autowired
    IFeedbackRestService feedbackRestService;

    @Autowired
    IFeedbackService feedbackService;

    @Before
    public void setUp() throws Exception {
        reset(this.feedbackService);
    }

    @Test
    public void testCreate() {
        when(this.feedbackService.create(Mockito.any(FeedbackBean.class))).thenReturn(1L);
        Response response = this.feedbackRestService.createFeedBack("comment", "/test/test1", 1L);

        assertThat(response.getStatus(), equalTo(Status.OK.getStatusCode()));

    }

    @Test
    public void testUpdate() {
        when(this.feedbackService.update(Mockito.any(FeedbackBean.class))).thenReturn(1L);
        Response response = this.feedbackRestService.updateFeedBack("comment", "/test/test1", 5L, 1L);

        assertThat(response.getStatus(), equalTo(Status.OK.getStatusCode()));

    }

    @Test
    public void testDelete() {
        when(this.feedbackService.deleteById(Mockito.any(Long.class))).thenReturn(1L);
        Response response = this.feedbackRestService.deleteFeedBack(1L);

        assertThat(response.getStatus(), equalTo(Status.OK.getStatusCode()));

    }

    @Test
    public void testSearch() {
        final Date now = new Date();

        final ResponseFeedbackBean feedbackBean = new ResponseFeedbackBean();
        feedbackBean.setId(1L);
        feedbackBean.setCreated(now);
        feedbackBean.setRate(5L);
        feedbackBean.setUpdated(now);
        feedbackBean.setPage("/test/tes1");

        final SearchResult<ResponseFeedbackBean> dataSearchResult = new SearchResult<>(Long.parseLong(SearchQuery.DEFAULT_START_INDEX), Long.parseLong(SearchQuery.DEFAULT_MAX_RESULTS));
        dataSearchResult.setTotalResults(3L);
        dataSearchResult.setContent(Arrays.asList(feedbackBean, feedbackBean, feedbackBean));

        when(this.feedbackService.search(any(), eq(ResponseFeedbackBean.class))).thenReturn(dataSearchResult);

        final SearchResult<ResponseFeedbackBean> response = this.feedbackRestService.search(Long.parseLong(SearchQuery.DEFAULT_START_INDEX), Long.parseLong(SearchQuery.DEFAULT_MAX_RESULTS),
                Arrays.asList(new SearchQueryFilter("updated:ASC")), null);

        assertThat(response, //
                hasProperty("totalResults", equalTo(3L)) //
        );
    }

}
