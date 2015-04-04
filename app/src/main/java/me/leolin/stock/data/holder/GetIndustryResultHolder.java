package me.leolin.stock.data.holder;

import me.leolin.stock.data.GetIndustryResult;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author leolin
 */
public class GetIndustryResultHolder extends DefaultResultHolder{
    private GetIndustryResult result;

    public GetIndustryResult getResult() {
        return result;
    }

    public void setResult(GetIndustryResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
