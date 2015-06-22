package me.leolin.twse.core.ioc;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import com.google.inject.AbstractModule;
import me.leolin.twse.BuildConfig;
import me.leolin.twse.data.DaoMaster;
import me.leolin.twse.data.DaoSession;
import me.leolin.twse.data.SubscriptionEntityDao;
import me.leolin.twse.data.SubscriptionSetEntityDao;
import me.leolin.twse.rest.StockWs;
import me.leolin.twse.subscription.business.SubscriptionFacade;
import me.leolin.twse.subscription.business.SubscriptionFacadeImpl;
import me.leolin.twse.subscription.dao.SubscriptionSetGreenDaoRepository;
import me.leolin.twse.subscription.dao.SubscriptionSetRepository;
import retrofit.RestAdapter;

/**
 * @author Leolin
 */
public class IocModule extends AbstractModule {

    private Application application;

    public IocModule(Application application) {
        this.application = application;
    }

    @Override
    protected void configure() {
        bindDaos();
        bindRestServices();
        bindFacade();
    }

    private void bindRestServices() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BuildConfig.SERVER_URL)
                .build();

        binder.bind(StockWs.class).toInstance(restAdapter.create(StockWs.class));
    }

    private void bindFacade() {
        binder.bind(SubscriptionFacade.class).to(SubscriptionFacadeImpl.class);
    }

    private void bindDaos() {
        DaoSession daoSession = getDaoSession();

        binder.bind(SubscriptionSetRepository.class).to(SubscriptionSetGreenDaoRepository.class);
        binder.bind(SubscriptionSetEntityDao.class).toInstance(daoSession.getSubscriptionSetEntityDao());
        binder.bind(SubscriptionEntityDao.class).toInstance(daoSession.getSubscriptionEntityDao());
    }

    private DaoSession getDaoSession() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(application, "StockDB", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }


}
