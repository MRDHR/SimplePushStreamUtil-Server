package com.dhr.simplepushstreamutil.bean;

import com.hiczp.bilibili.api.BilibiliAccount;

import java.io.Serializable;
import java.util.List;

public class LocalDataBean implements Serializable {
    private BilibiliAccount bilibiliAccount;

    public BilibiliAccount getBilibiliAccount() {
        return bilibiliAccount;
    }

    public void setBilibiliAccount(BilibiliAccount bilibiliAccount) {
        this.bilibiliAccount = bilibiliAccount;
    }
}
