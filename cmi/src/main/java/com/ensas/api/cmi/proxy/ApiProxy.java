package com.ensas.api.cmi.proxy;

import com.ensas.api.cmi.config.CustomProperties;
import com.ensas.api.cmi.entities.Creance;
import com.ensas.api.cmi.entities.Creancier;
import com.ensas.api.cmi.entities.Impaye;
import com.ensas.api.cmi.entities.Transaction;
import com.ensas.api.cmi.services.AccountService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class ApiProxy {

    @Autowired
    private CustomProperties props;

    public Iterable<Impaye> listImpaye(String apiUrl,String code){
        String impayeUrl = apiUrl;

        HttpHeaders headers = new HttpHeaders();
        headers.add("client-id","123");
        headers.add("client-secret","456");
        HttpEntity request = new HttpEntity(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(impayeUrl)
                .queryParam("code", code);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Impaye>> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<Iterable<Impaye>>() {}
        );

        //log.debug("Login call " + response.getStatusCode().toString());
        return response.getBody();
    }

    public Impaye getImpaye(String apiUrl,String codeImp){
        String impayeUrl = apiUrl+"/impaye";

        HttpHeaders headers = new HttpHeaders();
        headers.add("client-id","123");
        headers.add("client-secret","456");
        HttpEntity request = new HttpEntity(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(impayeUrl)
                .queryParam("code", codeImp);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Impaye>> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<Iterable<Impaye>>() {}
        );

        //log.debug("Login call " + response.getStatusCode().toString());

        System.out.println(codeImp+" "+response.getBody());
        Impaye impaye = ((List<Impaye>) response.getBody()).get(0);

        return impaye;
    }

    public Creance getCreance(String apiUrl,String codeCr){
        String creanceUrl = apiUrl+"/creance";

        HttpHeaders headers = new HttpHeaders();
        headers.add("client-id","123");
        headers.add("client-secret","456");
        HttpEntity request = new HttpEntity(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(creanceUrl)
                .queryParam("code", codeCr);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Creance>> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<Iterable<Creance>>() {}
        );

        System.out.println(response.getBody());
        Creance creance = ((List<Creance>) response.getBody()).get(0);
        //log.debug("Login call " + response.getStatusCode().toString());
        return creance;
    }

    public Iterable<Creance> listCreance(String apiUrl){
        String creanceUrl = apiUrl+"/creances";

        HttpHeaders headers = new HttpHeaders();
        headers.add("client-id","123");
        headers.add("client-secret","456");
        HttpEntity request = new HttpEntity(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Creance>> response = restTemplate.exchange(
                creanceUrl,
                HttpMethod.GET,
                request,
                new ParameterizedTypeReference<Iterable<Creance>>() {}
        );

        //log.debug("Login call " + response.getStatusCode().toString());
        return response.getBody();
    }

    public JSONObject confirmePaye(String apiUrl, String codeImp, String num, String transID){
        String payeUrl = apiUrl;

        HttpHeaders headers = new HttpHeaders();
        headers.add("client-id","123");
        headers.add("client-secret","456");


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("phoneNumber",num);
        jsonObject.put("code",codeImp);
        jsonObject.put("transacationID",transID);
        HttpEntity request = new HttpEntity(jsonObject,headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JSONObject> response = restTemplate.exchange(
                payeUrl,
                HttpMethod.POST,
                request,
                JSONObject.class
        );

        //log.debug("Login call " + response.getStatusCode().toString());
        return response.getBody();
    }
}
