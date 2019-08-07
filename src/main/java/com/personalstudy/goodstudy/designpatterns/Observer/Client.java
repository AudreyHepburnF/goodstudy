package com.personalstudy.goodstudy.designpatterns.Observer;

public class Client {
    public static void main(String[] args) {
        IMailService mailService = new MailService();
        IMail mail1 = new Mail("张三");
        IMail mail2 = new Mail("李四");
        IMail mail3 = new Mail("王五");

        mailService.add(mail1);
        mailService.add(mail2);
        mailService.add(mail3);
        mailService.notifyChanges("there is 3 new mail,to update client mail!");
    }
}
