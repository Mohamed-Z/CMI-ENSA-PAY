package com.ensas.api.cmi.services;

import com.ensas.api.cmi.entities.*;
import com.ensas.api.cmi.proxy.ApiProxy;
import com.ensas.api.cmi.repositories.AppRoleRepository;
import com.ensas.api.cmi.repositories.ClientRepository;
import com.ensas.api.cmi.repositories.CreancierRepository;
import com.ensas.api.cmi.repositories.TransactionRepository;
import com.ensas.api.cmi.sec.UserContext;
import org.json.simple.JSONObject;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AppRoleRepository appRoleRepository;
    @Autowired
    private CreancierRepository creancierRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserContext userContext;
    @Autowired
    private ApiProxy proxy;

    @Override
    public Client saveClient(Client client) {
        Client c=clientRepository.findByNumTel(client.getNumTel());
        if(c!=null) throw new RuntimeException("User already exist");
        String randomPass = generateRandomPassword(10, 48, 122);
        System.out.println("***************");
        System.out.println("Login : "+client.getNumTel()+"\nPassword : "+randomPass);
        System.out.println("***************");
        client.setPassword(bCryptPasswordEncoder.encode(randomPass));
        client.setActived(false);
        //client.setRoles(new ArrayList<>());
        client.setId(null);
        clientRepository.save(client);
        addRoleToClient(client.getNumTel(),"USER");
        client=clientRepository.findByNumTel(client.getNumTel());
        return client;
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return appRoleRepository.save(role);
    }

    @Override
    public Client loadClientByLogin(String login) {
        return clientRepository.findByNumTel(login);
    }

    @Override
    public void addRoleToClient(String numTel, String roleName) {
        Client client=clientRepository.findByNumTel(numTel);
        AppRole role=appRoleRepository.findByRoleName(roleName);
        client.getRoles().add(role);
        clientRepository.save(client);
    }

    @Override
    public void deleteAll() {
        clientRepository.deleteAll();
        appRoleRepository.deleteAll();
        creancierRepository.deleteAll();
    }

    @Override
    public Double getClientSolde(String token) {
        String login=userContext.getContextLogin(token);
        Client client=clientRepository.findByNumTel(login);
        return client.getSolde();
    }

    @Override
    public List<Creancier> getListCreancier() {
        List<Creancier> creancierList = creancierRepository.findAll();
        for(int i=0;i<creancierList.size();i++){
            creancierList.get(i).setCreances((Collection<Creance>) proxy.listCreance(creancierList.get(i).getUrl()));
        }
        return creancierList;
    }


    @Override
    public String generateRandomPassword(int len, int randNumOrigin, int randNumBound) {
        SecureRandom random = new SecureRandom();
        return random.ints(randNumOrigin, randNumBound + 1)
                .filter(i -> Character.isAlphabetic(i) || Character.isDigit(i))
                .limit(len)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }

    @Override
    public ResultImpaye getImpayes(String codeCreancier, String codeCreance) {
        String apiUrl = creancierRepository.findById(codeCreancier).get().getUrl();
        List<Impaye> impayeList = (List<Impaye>) proxy.listImpaye(apiUrl,codeCreance);
        Creancier creancier = creancierRepository.findById(codeCreancier).get();
        Creance creance = proxy.getCreance(creancier.getUrl(), codeCreance);
        creancier.getCreances().add(creance);
        return new ResultImpaye(impayeList, creancier);
    }

    @Override
    public Client updateClient(Client client) {
        Client currentClient = clientRepository.findById(client.getId()).get();
        String nom = client.getNom();
        if(nom!=null){
            currentClient.setNom(nom);
        }
        String prenom = client.getPrenom();
        if(prenom!=null){
            currentClient.setPrenom(prenom);
        }
        String email = client.getEmail();
        if(email!=null){
            currentClient.setEmail(email);
        }
        clientRepository.save(currentClient);
        return null;
    }

    @Override
    public void deleteClient(String id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<Client> getListClient() {
        return clientRepository.findAll();
    }

    @Override
    public boolean getIsActivated(String numTel) {
        return clientRepository.findByNumTel(numTel).isActived();
    }

    @Override
    public List<Transaction> getListTransaction(String token) {
        String login=userContext.getContextLogin(token);
        Client client=clientRepository.findByNumTel(login);
        return transactionRepository.findAllByEnPar(client.getId());
    }

    @Override
    public Transaction createTransaction(String clientID, String codeCr ,Double montant) {
        Date date = new Date();
        //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        //System.out.println(formatter.format(date));
        Transaction transaction = new Transaction(null,clientID,codeCr,montant,date);
        return transactionRepository.save(transaction);
    }

    @Override
    public boolean confirmePaye(String token, String codeCreancier, String codeImpaye) {
        String login=userContext.getContextLogin(token);
        Client client=clientRepository.findByNumTel(login);
        Creancier creancier = creancierRepository.findById(codeCreancier).get();
        Impaye impaye = proxy.getImpaye(creancier.getUrl(), codeImpaye);
        Transaction transaction = createTransaction(client.getId(), codeCreancier, impaye.getAmount());
        if (client.getSolde()>=impaye.getAmount()){
            updateClientSolde(client.getId(),impaye.getAmount());
            JSONObject result = proxy.confirmePaye(creancier.getUrl(), codeImpaye, client.getNumTel(), transaction.getId());
            if(result.get("ok").equals("ok")){
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateClientSolde(String id, Double montant) {
        Client client = clientRepository.findById(id).get();
        client.setSolde(client.getSolde()-montant);
        clientRepository.save(client);
    }

    @Override
    public void setClientPassword(String token, String password) {
        String login=userContext.getContextLogin(token);
        Client client=clientRepository.findByNumTel(login);
        client.setPassword(bCryptPasswordEncoder.encode(password));
        client.setActived(true);
        clientRepository.save(client);
    }

    @Override
    public Creancier saveCreancier(Creancier creancier) {
        return creancierRepository.save(creancier);
    }


}
