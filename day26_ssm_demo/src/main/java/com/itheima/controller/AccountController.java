package com.itheima.controller;

import com.itheima.pojo.Account;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/queryAll")
    public String queryAll(Model model){
        System.out.println("-------------------");
        List<Account> accounts = accountService.queryAll();
        model.addAttribute("accounts", accounts);
        //return "/pages/list.jsp";
        return "list";
    }

    @RequestMapping("/add")
    public String add(Account account){
        accountService.add(account);
        //跳转到列表页面
        return "redirect:queryAll";
    }
}
