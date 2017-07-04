/**
 * 
 */
package fr.ge.feedback.webclient.controller.search;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import fr.ge.feedback.ws.v1.bean.ResponseFeedbackBean;
import fr.ge.feedback.ws.v1.service.IFeedbackRestService;

/**
 * @author bsadil
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/test-context.xml" })
@WebAppConfiguration
public class FeedbackSearchControllerTest {

    /** mvc. */
    private MockMvc mvc;

    /** web app context. */
    @Autowired
    private WebApplicationContext webAppContext;

    @Autowired
    private IFeedbackRestService service;

    /**
     * Set up.
     *
     * @throws Exception
     *             exception
     */
    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.webAppContext).build();

        reset(this.service);
    }

    @Test
    public void testSearchMain() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/feedback")) //
                .andExpect(MockMvcResultMatchers.status().isOk()) //
                .andExpect(MockMvcResultMatchers.view().name("feedback/search/main"));
    }

    @Test
    public void testEdit() throws Exception {
        when(this.service.get(any(long.class))).thenReturn(new ResponseFeedbackBean());
        this.mvc.perform(MockMvcRequestBuilders.get("/feedback/{id}/display", 1L)) //
                .andExpect(MockMvcResultMatchers.status().isOk()) //
                .andExpect(MockMvcResultMatchers.view().name("feedback/display/main"));
    }

}
