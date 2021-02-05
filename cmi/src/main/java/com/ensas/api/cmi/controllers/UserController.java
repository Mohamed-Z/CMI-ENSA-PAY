package com.ensas.api.cmi.controllers;

import com.ensas.api.cmi.entities.Client;
import com.ensas.api.cmi.entities.Creancier;
import com.ensas.api.cmi.services.AccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserController {
/*
    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public Client register(@RequestBody Client client){
        return accountService.saveClient(client);
    }

    @GetMapping("/solde")
    public Double getSolde(){
        return accountService.getClientSolde();
    }

    @GetMapping("/listcreancier")
    public List<Creancier> getListCreancier(){
        return accountService.getListCreancier();
    }*/
}

