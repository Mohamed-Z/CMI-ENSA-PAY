package com.ensas.api.cmi.services;

import com.ensas.api.cmi.entities.*;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AccountService {
    public Client saveClient(Client client);
    public AppRole saveRole(AppRole role);
    public Client loadClientByLogin(String login);
    public void addRoleToClient(String numTel, String roleName);
    public void deleteAll();
    public Double getClientSolde(String token);
    public List<Creancier> getListCreancier();
    public String generateRandomPassword(int len, int randNumOrigin, int randNumBound);
    public ResultImpaye getImpayes(String codeCreancier, String codeCreance);
    public Client updateClient(Client client);
    public void deleteClient(String id);
    public List<Client> getListClient();
    public boolean getIsActivated(String numTel);
    public List<Transaction> getListTransaction(String token);
    public Transaction createTransaction(String clientID, String codeCr ,Double montant);
    public boolean confirmePaye(String token, String codeCreancier, String codeImpaye);
    public void updateClientSolde(String id, Double montant);
    public void setClientPassword(String token, String password);

    //Methodes de test
    public Creancier saveCreancier(Creancier creancier);
}
