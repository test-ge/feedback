/**
 * 
 */
package fr.ge.feedback.service;

import java.util.List;

import fr.ge.feedback.core.bean.search.SearchQuery;
import fr.ge.feedback.core.bean.search.SearchResult;
import fr.ge.feedback.service.bean.FeedbackBean;

/**
 * @author bsadil
 *
 */
public interface IFeedbackService {

    /**
     * Creates the FeedbackBean.
     *
     * @param entity
     *            the FeedbackBean
     * @return the long
     */
    long create(FeedbackBean entity);

    /**
     * Update the FeedbackBean.
     *
     * @param entity
     *            the FeedbackBean
     * @return the long
     */
    long update(FeedbackBean entity);

    /**
     * delete queue
     *
     * @param id
     *            of queue
     * @return
     */
    long deleteById(Long id);

    /**
     * delete queue
     *
     * @param uid
     *            of queue
     * @return
     */
    long deleteByPage(String string);

    /**
     * @param id
     * @return
     */
    FeedbackBean findById(Long id);

    /**
     * @param page
     * @return
     */
    List<FeedbackBean> findByPage(String page);

    /**
     * Search for queue messages.
     *
     * @param query
     *            search parameters
     * @return search result
     */
    <R> SearchResult<R> search(SearchQuery query, Class<R> expectedClass);

}
