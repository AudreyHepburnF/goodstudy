package com.personalstudy.designpattern.Visitor;

/**
 * 主体接口
 */
public interface Subject {
    void accept(Visitor visitor);
    String getSubject();
}
