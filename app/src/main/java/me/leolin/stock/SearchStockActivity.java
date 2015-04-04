package me.leolin.stock;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.inject.Inject;
import me.leolin.stock.business.StockService;
import me.leolin.stock.data.holder.GetStockResultHolder;
import me.leolin.stock.ui.StockListAdapter;
import me.leolin.stock.util.TextWatcherObservable;
import roboguice.activity.RoboActionBarActivity;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

import java.util.concurrent.TimeUnit;


public class SearchStockActivity extends RoboActionBarActivity {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private EditText editText;
    private final PublishSubject<Observable<String>> searchString = PublishSubject.create();

    @Inject
    private StockService stockService;
    private CompositeSubscription compositeSubscription;

    private static final String LOG_TAG = SearchStockActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_stock);

        getSupportActionBar().hide();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);


        editText = (EditText) findViewById(R.id.editTextSearchStock);


        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();

        compositeSubscription.add(
                Observable.switchOnNext(
                        Observable.switchOnNext(searchString)
                                .filter(s -> !TextUtils.isEmpty(s))
                                .doOnNext(s -> progressBar.setVisibility(View.VISIBLE))
                                .throttleLast(2, TimeUnit.SECONDS)
                                .map(stockService::queryStocks)
                ).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::onGetStocks)
        );
        searchString.onNext(TextWatcherObservable.create(editText));
    }

    private void onGetStocks(GetStockResultHolder holder) {
        if (!holder.isSuccess()) {
            Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
        }
        progressBar.setVisibility(View.GONE);
        recyclerView.setAdapter(new StockListAdapter(holder.getStocks()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeSubscription.unsubscribe();
        compositeSubscription.clear();
        compositeSubscription = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_stock, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
