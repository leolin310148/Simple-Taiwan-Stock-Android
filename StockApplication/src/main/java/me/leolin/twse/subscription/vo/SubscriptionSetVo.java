package me.leolin.twse.subscription.vo;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Leolin
 */
public class SubscriptionSetVo {
    private Long id;
    private String name;
    private List<SubscriptionVo> subscriptions = new LinkedList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubscriptionVo> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<SubscriptionVo> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
