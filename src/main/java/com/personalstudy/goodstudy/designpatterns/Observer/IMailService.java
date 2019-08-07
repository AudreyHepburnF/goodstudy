package com.personalstudy.goodstudy.designpatterns.Observer;

public interface IMailService {
    void add(IMail mail);
    void remove(IMail mail);
    void notifyChanges(String str);
}
