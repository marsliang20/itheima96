package com.itheima.dao;

import com.itheima.pojo.Account;

import java.util.List;

public interface AccountDao {
    List<Account> queryAll();

    void add(Account account);
}
