/**
 * 
 */
package fr.ge.feedback.service.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;

import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.ge.feedback.core.bean.search.SearchQuery;
import fr.ge.feedback.core.bean.search.SearchResult;
import fr.ge.feedback.service.IFeedbackService;
import fr.ge.feedback.service.bean.FeedbackBean;
import fr.ge.feedback.service.data.AbstractDbTest;

/**
 * @author bsadil
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/test-context.xml" })
public class FeedbackServiceImplTest extends AbstractDbTest {

    /** The issue service. */
    @Autowired
    private IFeedbackService service;

    /**
     * Setup.
     *
     * @throws Exception
     *             exception
     */
    @Before
    public void setUp() throws Exception {
        this.initDb(this.resourceName("dataset.xml"));
    }

    @Test
    public void testCreate() {
        final FeedbackBean feedback = new FeedbackBean();
        feedback.setComment("AWAIT");
        feedback.setPage("page1");
        feedback.setCreated(new Date());
        feedback.setUpdated(new Date());
        feedback.setRate(1L);

        this.service.create(feedback);
    }

    @Test
    public void testUpdate() {
        FeedbackBean feedback = new FeedbackBean();

        feedback.setId(100L);
        feedback.setComment("very bad");
        feedback.setRate(1L);
        feedback.setUpdated(new Date());

        this.service.update(feedback);

        assertThat(this.service.findById(100L),
                allOf(Arrays.asList( //
                        hasProperty("id", equalTo(100L)), //
                        hasProperty("comment", equalTo("very bad"))//
                )));
    }

    @Test
    public void testFindById() {
        assertThat(this.service.findById(100L),
                allOf(Arrays.asList( //
                        hasProperty("id", equalTo(100L)), //
                        hasProperty("comment", equalTo("the best page"))//
                )));
    }

    @Test
    public void testFindByPage() {
        assertThat(this.service.findByPage("auth.dev.guichet-entreprises.fr/form-forge/markov/monitoring").get(0),
                allOf(Arrays.asList( //
                        hasProperty("id", equalTo(100L)), //
                        hasProperty("comment", equalTo("the best page"))//
                )));
    }

    @Test
    public void deleteById() {
        this.service.deleteById(100L);
    }

    @Test
    public void deleteByPage() {
        this.service.deleteByPage("auth.dev.guichet-entreprises.fr/form-forge/markov/monitoring");
    }

    @Test
    public void testSearch() {
        final SearchResult<FeedbackBean> actual = this.service.search(new SearchQuery(0L, 5L), FeedbackBean.class);

        assertThat(actual, //
                allOf( //
                        hasProperty("startIndex", equalTo(0L)), //
                        hasProperty("maxResults", equalTo(5L)), //
                        hasProperty("totalResults", equalTo(2L))) //
        );
    }

}
