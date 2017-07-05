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

package fr.ge.feedback.core.exception;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @author Christian Cougourdan
 *
 */
public class TechnicalExceptionTest {

    @Test
    public void testNoArgs() {
        final TechnicalException actual = new TechnicalException();

        assertThat(actual,
                allOf( //
                        hasProperty("message", nullValue()), //
                        hasProperty("cause", nullValue()) //
                ) //
        );
    }

    @Test
    public void testWithMessage() {
        final String message = "record not found exception message";
        final TechnicalException actual = new TechnicalException(message);

        assertThat(actual,
                allOf( //
                        hasProperty("message", equalTo(message)), //
                        hasProperty("cause", nullValue()) //
                ) //
        );
    }

    @Test
    public void testWithCause() {
        final String causeMessage = "cause exception message";

        final Exception cause = new Exception(causeMessage);
        final TechnicalException actual = new TechnicalException(cause);

        assertThat(actual,
                allOf( //
                        hasProperty("message", equalTo(cause.toString())), //
                        hasProperty("cause", equalTo(cause)) //
                ) //
        );
    }

    @Test
    public void testWithMessageAndCause() {
        final String causeMessage = "cause exception message";
        final String message = "record not found exception message";

        final Exception cause = new Exception(causeMessage);
        final TechnicalException actual = new TechnicalException(message, cause);

        assertThat(actual,
                allOf( //
                        hasProperty("message", equalTo(message)), //
                        hasProperty("cause", equalTo(cause)) //
                ) //
        );
    }

}
