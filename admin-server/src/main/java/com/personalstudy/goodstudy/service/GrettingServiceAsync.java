package com.personalstudy.goodstudy.service;

import java.util.concurrent.CompletableFuture;

//@AsyncFor(GreetingsService.class)
public interface GrettingServiceAsync extends GreetingsService{

    CompletableFuture<String> sayHiAsync(String name);
}
