package me.leolin.twse.subscription.business;

import me.leolin.twse.subscription.vo.SubscriptionSetVo;
import rx.Observable;
import rx.schedulers.Schedulers;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * @author Leolin
 */
@Singleton
public class SubscriptionFacadeImpl implements SubscriptionFacade {

    @Inject
    private SubscriptionService subscriptionService;

    @Override
    public Observable<List<SubscriptionSetVo>> getAllSubscriptionSets() {
        return Observable.defer(() -> Observable.just(subscriptionService.getAllSubscriptionSets())).observeOn(Schedulers.io());
    }

    @Override
    public Observable<SubscriptionSetVo> findSubscriptionSetById(Long id) {
        return Observable.defer(() -> Observable.just(subscriptionService.findSubscriptionSetById(id))).observeOn(Schedulers.io());
    }

    @Override
    public Observable<Void> saveSubscriptionSet(SubscriptionSetVo subscriptionSetVo) {
        return Observable.defer(() -> Observable.just(subscriptionService.saveSubscriptionSet(subscriptionSetVo))).observeOn(Schedulers.io());
    }
}
