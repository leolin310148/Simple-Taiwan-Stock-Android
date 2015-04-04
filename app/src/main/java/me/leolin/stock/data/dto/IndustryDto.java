package me.leolin.stock.data.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author leolin
 */
public class IndustryDto {
    private String code;
    private String name;

    public IndustryDto() {
    }

    public IndustryDto(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
