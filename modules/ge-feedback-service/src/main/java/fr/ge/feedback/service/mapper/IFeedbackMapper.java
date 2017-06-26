/**
 * 
 */
package fr.ge.feedback.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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

}
