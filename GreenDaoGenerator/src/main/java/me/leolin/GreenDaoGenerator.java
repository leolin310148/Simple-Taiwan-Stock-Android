package me.leolin;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class GreenDaoGenerator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "me.leolin.twse.data");
        Entity subscriptionSet = schema.addEntity("SubscriptionSetEntity");
        Entity subscription = schema.addEntity("SubscriptionEntity");


        subscriptionSet.addIdProperty().autoincrement();
        subscriptionSet.addStringProperty("name");

        subscription.addStringProperty("stockId").primaryKey().getProperty();
        subscription.addIntProperty("order");
        Property fk = subscription.addLongProperty("setId").getProperty();

        subscriptionSet.addToMany(subscription, fk);

        new DaoGenerator().generateAll(schema, args[0]);
    }
}
