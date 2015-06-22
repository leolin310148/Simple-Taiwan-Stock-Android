package me.leolin.twse.subscription.business;

import me.leolin.twse.subscription.vo.SubscriptionSetVo;
import rx.Observable;

import java.util.List;

/**
 * @author Leolin
 */
public interface SubscriptionFacade {

    Observable<List<SubscriptionSetVo>> getAllSubscriptionSets();

    Observable<SubscriptionSetVo> findSubscriptionSetById(Long id);

    Observable<Void> saveSubscriptionSet(SubscriptionSetVo subscriptionSetVo);
}
