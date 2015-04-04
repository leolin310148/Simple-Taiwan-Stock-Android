package me.leolin.stock.data.holder;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author leolin
 */
public class DefaultResultHolder {
    private boolean success = false;
    private List<String> msgs = new LinkedList<>();

    public DefaultResultHolder() {
    }

    public DefaultResultHolder(String... messages) {
        msgs.addAll(Arrays.asList(messages));
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getMsgs() {
        return msgs;
    }

    public void setMsgs(List<String> msgs) {
        this.msgs = msgs;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
