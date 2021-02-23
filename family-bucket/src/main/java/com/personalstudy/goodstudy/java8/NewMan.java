package com.personalstudy.goodstudy.java8;

import com.personalstudy.goodstudy.base.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class NewMan {

    private Optional<Godness> godness;

    public Optional<Godness> getGodness() {
        return godness;
    }

    public void setGodness(Optional<Godness> godness) {
        this.godness = godness;
    }

    @Override
    public String toString() {
        return "NewMan{" +
                "godness=" + godness +
                '}';
    }

    public NewMan(Optional<Godness> godness) {
        this.godness = godness;
    }

    public NewMan() {
    }
}
