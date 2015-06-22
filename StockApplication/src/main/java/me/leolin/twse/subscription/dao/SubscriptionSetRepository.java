package me.leolin.twse.subscription.dao;

import me.leolin.twse.data.SubscriptionSetEntity;

import java.util.List;

/**
 * @author Leolin
 */
public interface SubscriptionSetRepository {
    long save(SubscriptionSetEntity subscriptionSetEntity);

    void update(SubscriptionSetEntity subscriptionSetEntity);

    List<SubscriptionSetEntity> findAll();

    void delete(Long id);

    SubscriptionSetEntity findOne(Long id);
}
