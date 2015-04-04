package me.leolin.stock.data.holder;

import com.google.gson.annotations.SerializedName;
import me.leolin.stock.data.dto.StockDto;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.LinkedList;
import java.util.List;

/**
 * @author leolin
 */
public class GetStockResultHolder extends DefaultResultHolder {
    @SerializedName("result")
    private List<StockDto> stocks = new LinkedList<>();

    public List<StockDto> getStocks() {
        return stocks;
    }

    public void setStocks(List<StockDto> stocks) {
        this.stocks = stocks;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
