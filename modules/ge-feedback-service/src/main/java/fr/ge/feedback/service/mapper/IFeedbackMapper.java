/**
 * 
 */
package fr.ge.feedback.service.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import fr.ge.feedback.core.bean.search.SearchQueryOrder;
import fr.ge.feedback.service.bean.FeedbackBean;

/**
 * @author bsadil
 *
 */
public interface IFeedbackMapper {

    /**
     * Creates the FeedbackBean.
     *
     * @param entity
     *            the FeedbackBean
     * @return the long
     */
    long create(@Param("feedback") FeedbackBean entity);

    /**
     * Update the FeedbackBean.
     *
     * @param entity
     *            the FeedbackBean
     * @return the long
     */
    long update(@Param("feedback") FeedbackBean entity);

    /**
     * delete queue
     *
     * @param id
     *            of queue
     * @return
     */
    long deleteById(@Param("id") Long id);

    /**
     * delete queue
     *
     * @param uid
     *            of queue
     * @return
     */
    long deleteByPage(@Param("page") String string);

    /**
     * @param id
     * @return
     */
    FeedbackBean findById(@Param("id") Long id);

    /**
     * @param page
     * @return
     */
    List<FeedbackBean> findByPage(@Param("page") String page);

    /**
     * Search for queue messages.
     *
     * @param filters
     *            filters
     * @param rowBounds
     *            pagination
     * @param orders
     *            orders
     * @return feedback messages list
     */
    List<FeedbackBean> findAll(@Param("filters") Map<String, Object> filters, @Param("orders") List<SearchQueryOrder> orders, RowBounds rowBounds);

    /**
     * Count queue messages.
     *
     * @param filters
     *            filters
     * @return total elements corresponding
     */
    long count(@Param("filters") Map<String, Object> filters);
}
