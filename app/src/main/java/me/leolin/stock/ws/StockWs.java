package me.leolin.stock.ws;

import me.leolin.stock.data.holder.GetIndustryResultHolder;
import me.leolin.stock.data.holder.GetStockResultHolder;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * @author leolin
 */
public interface StockWs {
    @GET("/industry")
    GetIndustryResultHolder getIndustries();

    @GET("/stock")
    GetStockResultHolder queryStock(@Query("search") String search);
}
