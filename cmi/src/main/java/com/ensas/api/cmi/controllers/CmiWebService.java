package com.ensas.api.cmi.controllers;

import com.ensas.api.cmi.entities.Client;
import com.ensas.api.cmi.entities.Creancier;
import com.ensas.api.cmi.entities.ResultImpaye;
import com.ensas.api.cmi.entities.Transaction;
import com.ensas.api.cmi.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@Service
@WebService(serviceName = "CmiWS")
public class CmiWebService {

    @Autowired
    private AccountService accountService;

    @WebMethod
    public Client saveClient(@WebParam(name = "client") Client client){
        return accountService.saveClient(client);
    }

    @WebMethod
    public Double getSolde(@WebParam(name = "token") String token){
        return accountService.getClientSolde(token);
    }

    @WebMethod
    public List<Transaction> getListTransaction(@WebParam(name = "token") String token){
        return accountService.getListTransaction(token);
    }

    @WebMethod
    public List<Creancier> getListCreancier(){
        return accountService.getListCreancier();
    }

    @WebMethod
    public Client updateClient(@WebParam(name = "client") Client client){
        return accountService.updateClient(client);
    }

    @WebMethod
    public void deleteClient(@WebParam(name = "id") String id){
        accountService.deleteClient(id);
    }

    @WebMethod
    public List<Client> getListClient(){
        return accountService.getListClient();
    }

    @WebMethod
    public boolean getIsActivated(@WebParam(name = "num") String num){
        return accountService.getIsActivated(num);
    }

    @WebMethod
    public ResultImpaye getImpayes(@WebParam(name="codeCreancier") String codeCreancier, @WebParam(name = "codeCreance") String codeCreance){
        return accountService.getImpayes(codeCreancier,codeCreance);
    }

    @WebMethod
    public boolean confirmePaye(@WebParam(name = "token") String token, @WebParam(name="codeCreancier") String codeCreancier, @WebParam(name = "codeImpaye") String codeImpaye){
        return accountService.confirmePaye(token,codeCreancier,codeImpaye);
    }

    @WebMethod
    public void setClientPassword(@WebParam(name = "token") String token, @WebParam(name = "password") String password){
        accountService.setClientPassword(token, password);
    }
}
