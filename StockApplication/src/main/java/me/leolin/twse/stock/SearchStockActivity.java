package me.leolin.twse.stock;

import android.view.Menu;
import me.leolin.twse.R;
import me.leolin.twse.core.GeneralSubActivity;
import roboguice.inject.ContentView;

/**
 * @author Leolin
 */
@ContentView(R.layout.activity_search_stock)
public class SearchStockActivity extends GeneralSubActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_stock_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
