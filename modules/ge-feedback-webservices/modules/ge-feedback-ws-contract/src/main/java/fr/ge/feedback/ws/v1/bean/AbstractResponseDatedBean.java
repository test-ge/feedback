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

package fr.ge.feedback.ws.v1.bean;

import java.util.Date;
import java.util.Optional;

/**
 * The Class AbstractDatedBean.
 *
 * @author Christian Cougourdan
 * @param <T>
 *            the generic type
 */
public abstract class AbstractResponseDatedBean<T extends AbstractResponseDatedBean<T>> {

    /** The id. */
    private Long id;

    /** The created. */
    private Date created;

    /** The updated. */
    private Date updated;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the id.
     *
     * @param id
     *            the new id
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public T setId(final Long id) {
        this.id = id;
        return (T) this;
    }

    /**
     * Gets the created.
     *
     * @return the created
     */
    public Date getCreated() {
        return this.created;
    }

    /**
     * Sets the created.
     *
     * @param created
     *            the new created
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public T setCreated(final Date created) {
        this.created = Optional.ofNullable(created).map(value -> (Date) value.clone()).orElse(null);
        return (T) this;
    }

    /**
     * Gets the updated.
     *
     * @return the updated
     */
    public Date getUpdated() {
        return this.updated;
    }

    /**
     * Sets the updated.
     *
     * @param updated
     *            the new updated
     * @return the t
     */
    @SuppressWarnings("unchecked")
    public T setUpdated(final Date updated) {
        this.updated = Optional.ofNullable(updated).map(value -> (Date) value.clone()).orElse(null);
        return (T) this;
    }

}
