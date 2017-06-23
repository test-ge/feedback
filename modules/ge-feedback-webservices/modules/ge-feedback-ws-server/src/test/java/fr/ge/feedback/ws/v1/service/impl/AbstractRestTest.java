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

package fr.ge.feedback.ws.v1.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MappingJsonFactory;

/**
 * Utility methods for testing REST classes.
 *
 * @author Christian Cougourdan
 */
public class AbstractRestTest {

    /**
     * Read as bean.
     *
     * @param <T>
     *            type générique
     * @param response
     *            response
     * @param expected
     *            expected
     * @return t
     * @throws Exception
     *             exception
     */
    protected <T> T readAsBean(final Response response, final Class<T> expected) throws Exception {
        final Object entity = response.getEntity();

        if (null == entity) {
            return null;
        }

        final MappingJsonFactory factory = new MappingJsonFactory();
        final JsonParser parser = factory.createParser((InputStream) response.getEntity());
        return parser.readValueAs(expected);
    }

    /**
     * Read as bean.
     *
     * @param <T>
     *            type générique
     * @param response
     *            response
     * @param typeReference
     *            type reference
     * @return t
     * @throws Exception
     *             exception
     */
    protected <T> T readAsBean(final Response response, final TypeReference<T> typeReference) throws Exception {
        final Object entity = response.getEntity();

        if (null == entity) {
            return null;
        }

        final MappingJsonFactory factory = new MappingJsonFactory();
        final JsonParser parser = factory.createParser((InputStream) response.getEntity());
        return parser.readValueAs(typeReference);
    }

    /**
     * Read as string.
     *
     * @param response
     *            response
     * @return string
     * @throws Exception
     *             exception
     */
    protected String readAsString(final Response response) throws Exception {
        final byte[] asBytes = this.readAsBytes(response);

        if (null == asBytes) {
            return null;
        }

        return new String(asBytes, StandardCharsets.UTF_8);
    }

    /**
     * Read as bytes.
     *
     * @param response
     *            response
     * @return byte[]
     * @throws Exception
     *             exception
     */
    protected byte[] readAsBytes(final Response response) throws Exception {
        final Object entity = response.getEntity();

        if (null == entity) {
            return null;
        }

        try (final ByteArrayOutputStream out = new ByteArrayOutputStream(); InputStream in = (InputStream) entity) {
            int len = 0;
            final byte[] buffer = new byte[4096];
            do {
                len = in.read(buffer);
                if (len > 0) {
                    out.write(buffer, 0, len);
                }
            } while (len > 0);

            return out.toByteArray();
        }

    }

}