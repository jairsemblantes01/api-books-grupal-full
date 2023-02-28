package com.distribuida.author;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class AuthorConection {
RestTemplate restTemplate = new RestTemplate();
String urlAuthor = "http://localhost:8082/authors";
public AuthorConection () {
	Map<String, String> env = System.getenv();
	for (String envName : env.keySet()) {
		if (envName.equals("URL_AUTHOR")) {
			urlAuthor = env.get(envName);
		}
	}
	System.out.println("URL_AUTHOR**********: " + urlAuthor);
}

public String getAuthor(Integer id) {
	try {
		AuthorsCliente autor =  restTemplate.getForObject(this.urlAuthor+ "/"+id, AuthorsCliente.class);
		System.out.println(autor);
		if (autor == null) {
			return "Sin nombre";
		} else {
			return autor.getFirstname()+ " " +autor.getLastname();
		}
	} catch (Exception e) {
		e.printStackTrace();
		return "Sin nombre";
	}
}
}
