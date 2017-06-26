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
package fr.ge.feedback.service.mapper;
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

import fr.ge.feedback.service.bean.FeedbackBean;
import fr.ge.feedback.service.data.AbstractDbTest;

/**
 * Tests {@link IssueMapper}.
 *
 * @author $Author: jpauchet $
 * @version $Revision: 0 $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/test-context.xml" })
public class FeedbackMapperTest extends AbstractDbTest {

    /** The issue mapper. */
    @Autowired
    private IFeedbackMapper mapper;

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
        feedback.setRate("bad");

        this.mapper.create(feedback);
    }

    @Test
    public void testUpdate() {
        FeedbackBean feedback = new FeedbackBean();

        feedback.setId(100L);
        feedback.setComment("very bad");
        feedback.setRate("bad");
        feedback.setUpdated(new Date());

        this.mapper.update(feedback);

        assertThat(this.mapper.findById(100L),
                allOf(Arrays.asList( //
                        hasProperty("id", equalTo(100L)), //
                        hasProperty("comment", equalTo("very bad"))//
                )));
    }

    @Test
    public void testFindById() {
        assertThat(this.mapper.findById(100L),
                allOf(Arrays.asList( //
                        hasProperty("id", equalTo(100L)), //
                        hasProperty("comment", equalTo("the best page"))//
                )));
    }

    @Test
    public void testFindByPage() {
        assertThat(this.mapper.findByPage("auth.dev.guichet-entreprises.fr/form-forge/markov/monitoring").get(0),
                allOf(Arrays.asList( //
                        hasProperty("id", equalTo(100L)), //
                        hasProperty("comment", equalTo("the best page"))//
                )));
    }

    @Test
    public void deleteById() {
        this.mapper.deleteById(100L);
    }

    @Test
    public void deleteByPage() {
        this.mapper.deleteByPage("auth.dev.guichet-entreprises.fr/form-forge/markov/monitoring");
    }

}
