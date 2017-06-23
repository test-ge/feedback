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
package fr.ge.feedback.core.support.appstatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import net.sf.appstatus.core.property.impl.AbstractPropertyProvider;

/**
 * Class PropertyProvider.
 *
 * @author Christian Cougourdan
 */
public class PropertyProvider extends AbstractPropertyProvider {

    /** La constante PATTERN_PASSWORD. */
    private static final Pattern PATTERN_PASSWORD = Pattern.compile("(password|pwd|passwd)", Pattern.CASE_INSENSITIVE);

    /** properties. */
    private Properties properties;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCategory() {
        return "Configuration";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getProperties() {
        final Map<String, String> map = new HashMap<>();

        if (null != this.properties) {
            for (final Map.Entry<Object, Object> entry : this.properties.entrySet()) {
                final String key = (String) entry.getKey();
                String value;
                if (this.isRelativeToPassword(key)) {
                    value = "********";
                } else {
                    value = (String) entry.getValue();
                }
                map.put(key, value);
            }
        }

        return map;
    }

    /**
     * Vérifie si c'est relative to password.
     *
     * @param key
     *            key
     * @return true, si c'est relative to password
     */
    private boolean isRelativeToPassword(final String key) {
        return PATTERN_PASSWORD.matcher(key).find();
    }

    /**
     * Setter on attribute {@link #properties}.
     *
     * @param properties
     *            the properties to set
     */
    public void setProperties(final Properties properties) {
        this.properties = properties;
    }

}
