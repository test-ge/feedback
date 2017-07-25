/**
 *
 */
package fr.ge.feedback.ws.v1.service.impl;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.util.Properties;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.ge.feedback.service.IFeedbackService;
import fr.ge.feedback.service.bean.FeedbackBean;
import fr.ge.feedback.ws.v1.service.IFeedbackRestService;

/**
 * @author bsadil
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/test-context.xml", "classpath:spring/service-context.xml", "classpath:spring/ws-server-cxf-context.xml" })
public class FeedbackRestServiceTest {

    @Autowired
    private Properties appProperties;

    @Autowired
    private IFeedbackRestService feedbackRestService;

    @Autowired
    private IFeedbackService feedbackService;

    @Before
    public void setUp() throws Exception {
        reset(this.feedbackService);
    }

    @Test
    public void testCreate() {
        when(this.feedbackService.create(Mockito.any(FeedbackBean.class))).thenReturn(1L);
        final Response response = this.feedbackRestService.createFeedBack("comment", "/test/test1", 1L);

        assertThat(response.getStatus(), equalTo(Status.OK.getStatusCode()));

    }

    @Test
    public void testUpdate() {
        when(this.feedbackService.update(Mockito.any(FeedbackBean.class))).thenReturn(1L);
        final Response response = this.feedbackRestService.updateFeedBack("comment", "/test/test1", 5L, 1L);

        assertThat(response.getStatus(), equalTo(Status.OK.getStatusCode()));

    }

    @Test
    public void testWidget() {
        final Response response = this.feedbackRestService.widget();

        assertThat(response.getStatus(), equalTo(Status.OK.getStatusCode()));

        final String actual = response.readEntity(String.class);

        assertThat(actual, notNullValue());
        assertThat(actual.indexOf(this.appProperties.getProperty("ws.feedback.public.url")), greaterThanOrEqualTo(0));
    }

}
