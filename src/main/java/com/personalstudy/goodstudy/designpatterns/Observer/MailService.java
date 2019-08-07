package com.personalstudy.goodstudy.designpatterns.Observer;

import java.util.ArrayList;
import java.util.List;

public class MailService implements IMailService {
    private List<IMail> mailList = new ArrayList<>();
    @Override
    public void add(IMail mail) {
        mailList.add(mail);
    }

    @Override
    public void remove(IMail mail) {
        mailList.remove(mail);
    }

    @Override
    public void notifyChanges(String str) {
       for (IMail mail : mailList){
           mail.updateEMail(str);
       }
    }
}
