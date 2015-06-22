package me.leolin.twse.subscription.dao;

import me.leolin.twse.data.SubscriptionSetEntity;
import me.leolin.twse.data.SubscriptionSetEntityDao;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * @author Leolin
 */
@Singleton
public class SubscriptionSetGreenDaoRepository implements SubscriptionSetRepository {

    @Inject
    private SubscriptionSetEntityDao subscriptionSetEntityDao;

    @Override
    public long save(SubscriptionSetEntity subscriptionSetEntity) {
       return subscriptionSetEntityDao.insert(subscriptionSetEntity);
    }

    @Override
    public void update(SubscriptionSetEntity subscriptionSetEntity) {
        subscriptionSetEntityDao.update(subscriptionSetEntity);
    }

    @Override
    public List<SubscriptionSetEntity> findAll() {
        return subscriptionSetEntityDao.loadAll();
    }

    @Override
    public void delete(Long id) {
        subscriptionSetEntityDao.deleteByKey(id);
    }

    @Override
    public SubscriptionSetEntity findOne(Long id) {
        return subscriptionSetEntityDao.load(id);
    }
}
