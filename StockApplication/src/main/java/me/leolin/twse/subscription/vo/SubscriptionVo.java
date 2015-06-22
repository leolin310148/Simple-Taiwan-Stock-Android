package me.leolin.twse.subscription.vo;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author Leolin
 */
public class SubscriptionVo implements Comparable<SubscriptionVo> {
    private String stockId;
    private int order;

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int compareTo(SubscriptionVo another) {
        return new CompareToBuilder().append(this.order, another.order).toComparison();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
