package me.leolin.twse.subscription;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import me.leolin.twse.R;
import me.leolin.twse.core.GeneralSubActivity;
import me.leolin.twse.stock.SearchStockActivity;
import me.leolin.twse.subscription.business.SubscriptionFacade;
import me.leolin.twse.subscription.vo.SubscriptionSetVo;
import roboguice.inject.ContentView;
import rx.android.app.AppObservable;

import javax.inject.Inject;

/**
 * @author Leolin
 */
@ContentView(R.layout.activity_edit_subscription)
public class EditSubscriptionSetActivity extends GeneralSubActivity {

    @Inject
    private SubscriptionFacade subscriptionFacade;
    private TextInputLayout textInputSubscriptionSetName;
    private SubscriptionSetVo subscriptionSetVo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        textInputSubscriptionSetName = speedView.just(R.id.textInputSubscriptionSetName);
        speedView.view(R.id.floatingActionButtonAddSubscription).click(v -> startActivityForResult(new Intent(this, SearchStockActivity.class), 0));
    }

    @Override
    protected void onStart() {
        super.onStart();

        long id = getIntent().getLongExtra("SubscriptionSetId", -1L);
        if (id == -1L) {
            render(new SubscriptionSetVo());
        } else {
            AppObservable.bindActivity(this, subscriptionFacade.findSubscriptionSetById(id))
                    .subscribe(this::render);
        }

    }

    private void render(SubscriptionSetVo subscriptionSetVo) {
        this.subscriptionSetVo = subscriptionSetVo;

        if (subscriptionSetVo.getId() == null) {
            getSupportActionBar().setTitle(R.string.label_add_subscription);
        }

        EditText editTextSubscriptionName = textInputSubscriptionSetName.getEditText();
        editTextSubscriptionName.setText(this.subscriptionSetVo.getName());
        editTextSubscriptionName.setSelection(editTextSubscriptionName.getText().length());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_subscription_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                String name = textInputSubscriptionSetName.getEditText().getText().toString();
                if (TextUtils.isEmpty(name)) {
                    textInputSubscriptionSetName.setError(getString(R.string.validation_msg_subscription_set_name_required));
                } else {
                    subscriptionSetVo.setName(name);
                    subscriptionFacade.saveSubscriptionSet(subscriptionSetVo).subscribe(aVoid -> this.finish());
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
