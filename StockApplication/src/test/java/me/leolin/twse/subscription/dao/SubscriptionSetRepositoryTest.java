package me.leolin.twse.subscription.dao;

import android.app.Application;
import me.leolin.twse.core.ioc.IocModule;
import me.leolin.twse.data.SubscriptionSetEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import roboguice.RoboGuice;

import java.util.List;

/**
 * @author Leolin
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class SubscriptionSetRepositoryTest {

    private SubscriptionSetRepository subscriptionSetRepository;

    @Before
    public void setUp() throws Exception {
        Application app = Robolectric.application;
        RoboGuice.overrideApplicationInjector(app, new IocModule(app));
        subscriptionSetRepository = RoboGuice.getInjector(app).getInstance(SubscriptionSetRepository.class);

        Assert.assertNotNull(subscriptionSetRepository);
    }

    @After
    public void tearDown() throws Exception {
        RoboGuice.Util.reset();
    }

    @Test
    public void testSave() throws Exception {
        int sizeBeforeSave = subscriptionSetRepository.findAll().size();
        SubscriptionSetEntity entity = new SubscriptionSetEntity();
        entity.setName("test");
        subscriptionSetRepository.save(entity);
        int sizeAfterSave = subscriptionSetRepository.findAll().size();

        Assert.assertEquals(sizeAfterSave, sizeBeforeSave + 1);
    }


    @Test
    public void testUpdate() throws Exception {
        testSave();
        SubscriptionSetEntity entity = subscriptionSetRepository.findAll().get(0);
        entity.setName("updated");
        subscriptionSetRepository.update(entity);

        Assert.assertEquals(subscriptionSetRepository.findAll().get(0).getName(),"updated");
    }



    @Test
    public void testDelete() throws Exception {
        testSave();
        List<SubscriptionSetEntity> entities = subscriptionSetRepository.findAll();
        for (SubscriptionSetEntity entity : entities) {
            subscriptionSetRepository.delete(entity.getId());
        }

        Assert.assertEquals(subscriptionSetRepository.findAll().size(), 0);
    }

}