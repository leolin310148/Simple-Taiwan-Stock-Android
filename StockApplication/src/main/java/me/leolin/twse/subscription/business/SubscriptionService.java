package me.leolin.twse.subscription.business;

import me.leolin.twse.R;
import me.leolin.twse.data.SubscriptionEntity;
import me.leolin.twse.data.SubscriptionSetEntity;
import me.leolin.twse.subscription.dao.SubscriptionSetRepository;
import me.leolin.twse.subscription.vo.SubscriptionSetVo;
import me.leolin.twse.subscription.vo.SubscriptionVo;
import roboguice.inject.InjectResource;
import rx.Observable;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * @author Leolin
 */
@Singleton
public class SubscriptionService {

    @Inject
    private SubscriptionSetRepository subscriptionSetRepository;
    @InjectResource(R.string.defaultSubscriptionSetName)
    private String defaultSubscriptionSetName;

    public List<SubscriptionSetVo> getAllSubscriptionSets() {
        List<SubscriptionSetEntity> subscriptionSetEntities = subscriptionSetRepository.findAll();
        if (subscriptionSetEntities.isEmpty()) {
            SubscriptionSetEntity entity = new SubscriptionSetEntity();
            entity.setName(defaultSubscriptionSetName);
            subscriptionSetRepository.save(entity);
            subscriptionSetEntities = subscriptionSetRepository.findAll();
        }

        return Observable.from(subscriptionSetEntities)
                .map(this::entityToVo)
                .toList()
                .toBlocking()
                .first();
    }


    public SubscriptionSetVo findSubscriptionSetById(Long id) {
        return entityToVo(subscriptionSetRepository.findOne(id));
    }

    public Void saveSubscriptionSet(SubscriptionSetVo subscriptionSetVo) {
        Long setId = subscriptionSetVo.getId();
        SubscriptionSetEntity entity;
        if (setId == null) {
            entity = new SubscriptionSetEntity();
            entity.setName(subscriptionSetVo.getName());
            setId = subscriptionSetRepository.save(entity);
        }

        entity = subscriptionSetRepository.findOne(setId);
        entity.setName(subscriptionSetVo.getName());
        entity.getSubscriptionEntityList().clear();

        final Long finalSetId = setId;
        Observable.from(subscriptionSetVo.getSubscriptions())
                .map(vo -> new SubscriptionEntity(vo.getStockId(), vo.getOrder(), finalSetId))
                .subscribe(entity.getSubscriptionEntityList()::add);

        subscriptionSetRepository.update(entity);
        return null;
    }


    private SubscriptionVo entityToVo(SubscriptionEntity entity) {
        SubscriptionVo vo = new SubscriptionVo();
        vo.setStockId(entity.getStockId());
        vo.setOrder(entity.getOrder());

        return vo;
    }


    private SubscriptionSetVo entityToVo(SubscriptionSetEntity entity) {
        SubscriptionSetVo vo = new SubscriptionSetVo();
        vo.setId(entity.getId());
        vo.setName(entity.getName());

        Observable.from(entity.getSubscriptionEntityList())
                .map(this::entityToVo)
                .toSortedList()
                .subscribe(vo::setSubscriptions);

        return vo;
    }
}
