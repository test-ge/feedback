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

package fr.ge.feedback.core.support.aop;

import java.io.InputStream;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Aspect for logging method calls.
 *
 * @author jpauchet
 */
public class MethodCallLoggingAspect implements MethodInterceptor {

    /** Trace. */
    private static final String TRACE = "TRACE";

    /** Debug. */
    private static final String DEBUG = "DEBUG";

    /** Info. */
    private static final String INFO = "INFO";

    /** Log level. */
    private String logLevel;

    /**
     * Constructor.
     */
    public MethodCallLoggingAspect() {
        this.logLevel = INFO;
    }

    /**
     * Constructor.
     *
     * @param logLevel
     *            the log level
     */
    public MethodCallLoggingAspect(final String logLevel) {
        this.logLevel = logLevel;
    }

    /**
     * Logs a message.
     *
     * @param clazz
     *            the class
     * @param message
     *            the message
     */
    private void addLog(final Class<?> clazz, final String message) {
        final Logger logger = LoggerFactory.getLogger(clazz);
        switch (this.logLevel) {
        case TRACE:
            logger.trace(message);
            break;
        case DEBUG:
            logger.debug(message);
            break;
        case INFO:
            logger.info(message);
            break;
        default:
            break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable {
        final Class<?> clazz = invocation.getThis().getClass();
        final String methodName = invocation.getMethod().getName();
        final StringBuilder log = new StringBuilder();
        log.append("Calling ");
        log.append(methodName);
        log.append('(');
        final Object[] arguments = invocation.getArguments();
        for (int i = 0; i < arguments.length; ++i) {
            if (i > 0) {
                log.append(", ");
            }
            if (arguments[i] == null) {
                log.append("null");
            } else if (arguments[i] instanceof byte[] || arguments[i] instanceof InputStream) {
                log.append("__");
                log.append(arguments[i].getClass().getSimpleName());
                log.append("__");
            } else {
                log.append(arguments[i]);
            }
        }
        log.append(')');
        this.addLog(clazz, log.toString());
        return invocation.proceed();
    }

    /**
     * Gets the log level.
     *
     * @return the log level
     */
    public String getLogLevel() {
        return this.logLevel;
    }

    /**
     * Sets the log level.
     *
     * @param logLevel
     *            the new log level
     */
    public void setLogLevel(final String logLevel) {
        this.logLevel = logLevel;
    }

}
