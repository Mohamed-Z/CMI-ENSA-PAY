package com.ensas.api.cmi;

import com.ensas.api.cmi.entities.AppRole;
import com.ensas.api.cmi.entities.Client;
import com.ensas.api.cmi.entities.Creancier;
import com.ensas.api.cmi.proxy.ApiProxy;
import com.ensas.api.cmi.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.stream.Stream;

@SpringBootApplication
public class CmiApplication {

	@Autowired
	private ApiProxy apiProxy;

	public static void main(String[] args) {
		SpringApplication.run(CmiApplication.class, args);
	}

	@Bean
	CommandLineRunner start(AccountService accountService) {
		return args -> {
			accountService.deleteAll();

			accountService.saveRole(new AppRole(null,"USER"));
			accountService.saveRole(new AppRole(null,"ADMIN"));

			Stream.of("ZIDANI Mohamed +212649735026").forEach(a->{
				accountService.saveClient(new Client(null,a.split(" ")[0],a.split(" ")[1],null,a.split(" ")[2],null,false,200000.0,"a1",new ArrayList<>()));
			});

			//accountService.addRoleToClient("0668797072", "ADMIN");

			Stream.of("c1 IAM RECHARGES operateur").forEach(a->{
				accountService.saveCreancier(new Creancier(a.split(" ")[0],a.split(" ")[1]+" "+a.split(" ")[2],a.split(" ")[3],"http://localhost:4000/",new ArrayList<>()));
			});

			/*
			Stream.of("l1 telephone et internet sim","l2 produit internet sim","l3 produit fixe sim","l4 facture eau","l5 facture electricite").forEach(a->{
				accountService.saveCreance(new Creance(a.split(" ")[0],a.substring(a.split(" ")[0].length()+1)));
			});

			accountService.addCreancetoCreancier("c1","l1");
			accountService.addCreancetoCreancier("c2","l2");
			accountService.addCreancetoCreancier("c2","l3");
			accountService.addCreancetoCreancier("c3","l4");
			accountService.addCreancetoCreancier("c3","l5");
			*/

			//System.out.println(apiProxy.listImpaye("http://localhost:4000/","cr01"));
			//System.out.println(impayeProxy.recharge().get("ok"));
		};
	}

	@Bean
	BCryptPasswordEncoder getBCPE(){
		return new BCryptPasswordEncoder();
	}

}
