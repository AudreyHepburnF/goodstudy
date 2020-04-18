package com.personalstudy.springcloud.eureka;

import java.math.BigDecimal;

/**
 * @author congyaozhu
 * @date 2020-01-14 9:16
 * @description
 */
public class Book {

    private String name;

    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
