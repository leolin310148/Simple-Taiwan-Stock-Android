package me.leolin.stock.data.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author leolin
 */
public class StockDto {
    private String id;
    private String name;

    public StockDto() {
    }

    public StockDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
