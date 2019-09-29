package com.personalstudy.designpattern.Observer;

public interface IMailService {
    void add(IMail mail);
    void remove(IMail mail);
    void notifyChanges(String str);
}
