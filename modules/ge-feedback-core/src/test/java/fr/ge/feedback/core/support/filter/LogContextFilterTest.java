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

package fr.ge.feedback.core.support.filter;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.MDC;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import fr.ge.feedback.core.support.filter.LogContextFilter;

/**
 * Class LogContextFilterTest.
 *
 * @author Christian Cougourdan
 */
@RunWith(MockitoJUnitRunner.class)
public class LogContextFilterTest {

    /**
     * Test simple.
     *
     * @throws Exception
     *             exception
     */
    @Test
    public void testSimple() throws Exception {
        final LogContextFilter filter = new LogContextFilter();
        final MockHttpServletRequest request = new MockHttpServletRequest();
        final MockHttpServletResponse response = new MockHttpServletResponse();
        final FilterChain chain = mock(FilterChain.class);

        final Map<String, String> actual = new HashMap<>();

        doAnswer(invocation -> {
            final Map<String, String> context = MDC.getCopyOfContextMap();
            if (null != context) {
                actual.putAll(context);
            }
            return null;
        }).when(chain).doFilter(request, response);

        filter.doFilter(request, response, chain);

        assertThat(actual.get("correlationId"), notNullValue());
    }

    /**
     * Test clean.
     *
     * @throws Exception
     *             exception
     */
    @Test
    public void testClean() throws Exception {
        final LogContextFilter filter = new LogContextFilter();
        final MockHttpServletRequest request = new MockHttpServletRequest();
        final MockHttpServletResponse response = new MockHttpServletResponse();
        final FilterChain chain = mock(FilterChain.class);

        filter.doFilter(request, response, chain);

        assertTrue(MDC.getCopyOfContextMap().isEmpty());
    }

    /**
     * Test error.
     *
     * @throws Exception
     *             exception
     */
    @Test
    public void testError() throws Exception {
        final LogContextFilter filter = new LogContextFilter();
        final MockHttpServletRequest request = new MockHttpServletRequest();
        final MockHttpServletResponse response = new MockHttpServletResponse();
        final FilterChain chain = mock(FilterChain.class);

        final Map<String, String> actual = new HashMap<>();

        doAnswer(invocation -> {
            final Map<String, String> context = MDC.getCopyOfContextMap();
            if (null != context) {
                actual.putAll(context);
            }
            throw new RuntimeException();
        }).when(chain).doFilter(request, response);

        try {
            filter.doFilter(request, response, chain);
            fail("RuntimeException expected");
        } catch (final RuntimeException ex) {
            // Nothing to do
        }

        assertFalse(actual.isEmpty());
        assertTrue(MDC.getCopyOfContextMap().isEmpty());
    }

}
