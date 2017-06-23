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
package fr.ge.feedback.service.data;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.ArrayUtils;

import fr.ge.common.form.core.util.CoreUtil;
import fr.ge.common.form.core.util.JaxbFactoryImpl;

/**
 * The Class AbstractTest.
 *
 * @author Christian Cougourdan
 */
public abstract class AbstractTest {

    /** The resources. */
    private final Map<String, byte[]> resources = new HashMap<>();

    /**
     * Resource as bytes.
     *
     * @param name
     *            the name
     * @return the byte[]
     */
    public byte[] resourceAsBytes(final String name) {
        byte[] asBytes = this.resources.get(name);

        if (null == asBytes) {
            final String fullName = this.resourceName(name);
            asBytes = CoreUtil.resourceAsBytes(fullName, this.getClass());
            if (ArrayUtils.isEmpty(asBytes) && name.endsWith(".zip")) {
                asBytes = this.buildZipResource(name);
            }
            this.resources.put(name, asBytes);
        }

        return asBytes;
    }

    /**
     * Builds the zip resource.
     *
     * @param name
     *            the name
     * @return the byte[]
     */
    private byte[] buildZipResource(final String name) {
        final String folderName = this.resourceName(name.replaceAll("\\.zip$", ""));

        final URI folderUri = Optional.ofNullable(this.getClass().getResource(folderName)) //
                .map(url -> {
                    try {
                        return url.toURI();
                    } catch (final URISyntaxException ex) {
                        throw new RuntimeException("", ex);
                    }
                }) //
                .orElseThrow(() -> new RuntimeException("Resource '" + name + "' not found"));

        final File rootPath = Paths.get(folderUri).toFile();
        final List<String> entryNames = this.buildEntryList("", rootPath);

        final byte[] asBytes = CoreUtil.create(entryNames, (entryName) -> CoreUtil.resourceAsBytes(folderName + "/" + entryName, this.getClass()));

        return asBytes;
    }

    /**
     * Builds the entry list.
     *
     * @param prefix
     *            the prefix
     * @param rootPath
     *            the root path
     * @return the list
     */
    private List<String> buildEntryList(final String prefix, final File rootPath) {
        final List<String> entryNames = new ArrayList<>();

        for (final File f : Optional.ofNullable(rootPath.listFiles()).orElse(new File[] {})) {
            if (f.isDirectory()) {
                entryNames.addAll(this.buildEntryList(prefix + f.getName() + "/", f));
            } else {
                entryNames.add(prefix + f.getName());
            }
        }

        return entryNames;
    }

    /**
     * Resource as string.
     *
     * @param name
     *            the name
     * @return the string
     */
    public String resourceAsString(final String name) {
        return new String(this.resourceAsBytes(name), StandardCharsets.UTF_8);
    }

    /**
     * Resource as bean.
     *
     * @param <T>
     *            the generic type
     * @param name
     *            the name
     * @param clazz
     *            the clazz
     * @return the t
     */
    public <T> T resourceAsBean(final String name, final Class<T> clazz) {
        final byte[] asBytes = this.resourceAsBytes(name);
        return JaxbFactoryImpl.instance().unmarshal(asBytes, clazz);
    }

    /**
     * Resource name.
     *
     * @param name
     *            the name
     * @return the string
     */
    public String resourceName(final String name) {
        if (name.startsWith("/")) {
            return name;
        } else {
            return String.format("%s-%s", this.getClass().getSimpleName(), name);
        }
    }

}
