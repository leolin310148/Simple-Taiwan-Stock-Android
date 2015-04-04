package me.leolin.stock.data;

import me.leolin.stock.data.dto.IndustryDto;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * @author leolin
 */
public class GetIndustryResult {
    private List<IndustryDto> tse;
    private List<IndustryDto> otc;

    public List<IndustryDto> getTse() {
        return tse;
    }

    public void setTse(List<IndustryDto> tse) {
        this.tse = tse;
    }

    public List<IndustryDto> getOtc() {
        return otc;
    }

    public void setOtc(List<IndustryDto> otc) {
        this.otc = otc;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
