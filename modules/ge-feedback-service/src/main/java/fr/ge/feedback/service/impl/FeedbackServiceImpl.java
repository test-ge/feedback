/**
 * 
 */
package fr.ge.feedback.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.ibatis.session.RowBounds;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import fr.ge.feedback.core.bean.search.SearchQuery;
import fr.ge.feedback.core.bean.search.SearchResult;
import fr.ge.feedback.service.IFeedbackService;
import fr.ge.feedback.service.bean.FeedbackBean;
import fr.ge.feedback.service.mapper.IFeedbackMapper;

/**
 * @author bsadil
 *
 */
public class FeedbackServiceImpl implements IFeedbackService {

    @Autowired
    private IFeedbackMapper feedbackMapper;

    @Autowired
    private DozerBeanMapper dozer;

    /**
     * {@inheritDoc}
     */
    @Override
    public long create(FeedbackBean entity) {
        final Long id = this.feedbackMapper.create(entity);
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long update(FeedbackBean entity) {
        final Long id = this.feedbackMapper.update(entity);
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long deleteById(Long id) {

        return this.feedbackMapper.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long deleteByPage(String page) {
        return this.feedbackMapper.deleteByPage(page);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FeedbackBean findById(Long id) {
        final FeedbackBean feedback = this.feedbackMapper.findById(id);
        return feedback;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<FeedbackBean> findByPage(String page) {
        final List<FeedbackBean> list = this.feedbackMapper.findByPage(page);
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <R> SearchResult<R> search(SearchQuery searchQuery, Class<R> expectedClass) {
        final Map<String, Object> filters = new HashMap<>();
        if (null != searchQuery.getFilters()) {
            searchQuery.getFilters().forEach(filter -> filters.put(filter.getColumn(), filter));
        }

        final RowBounds rowBounds = new RowBounds((int) searchQuery.getStartIndex(), (int) searchQuery.getMaxResults());
        final List<FeedbackBean> entities = this.feedbackMapper.findAll(filters, rowBounds);

        final SearchResult<R> searchResult = new SearchResult<>(searchQuery.getStartIndex(), searchQuery.getMaxResults());
        if (null != entities) {
            searchResult.setContent(entities.stream().map(elm -> this.dozer.map(elm, expectedClass)).collect(Collectors.toList()));
        }

        searchResult.setTotalResults(this.feedbackMapper.count(filters));

        return searchResult;
    }

}
