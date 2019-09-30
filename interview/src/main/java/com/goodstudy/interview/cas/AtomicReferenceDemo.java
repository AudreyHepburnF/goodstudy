package com.goodstudy.interview.cas;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子引用demo
 */
@Getter
@ToString
@AllArgsConstructor
class User{
    private Integer age;

    private String name;
}


public class AtomicReferenceDemo{

    public static void main(String[] args) {
        User z3 = new User(18, "zs");
        User l4 = new User(20, "ls");

        AtomicReference<User> atomicReference = new AtomicReference<User>();
        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3, l4) + "\t current value is : " + atomicReference.get());

        System.out.println(atomicReference.compareAndSet(z3, l4) + "\t current value is : " + atomicReference.get());
    }
}
