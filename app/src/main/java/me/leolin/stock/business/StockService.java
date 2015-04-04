package me.leolin.stock.business;

import com.google.inject.Singleton;
import me.leolin.stock.core.StockApplication;
import me.leolin.stock.data.holder.GetStockResultHolder;
import me.leolin.stock.ws.StockWs;
import rx.Observable;

/**
 * @author leolin
 */
@Singleton
public class StockService {
    private final StockWs stockWs = StockApplication.getRestAdapter().create(StockWs.class);

    private static final String LOG_TAG = StockService.class.getSimpleName();

    public Observable<GetStockResultHolder> queryStocks(String queryText) {
        return Observable.defer(() -> Observable.just(stockWs.queryStock(queryText))).onErrorReturn(t -> new GetStockResultHolder());
    }
}
