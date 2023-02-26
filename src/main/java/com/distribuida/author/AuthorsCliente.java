package com.distribuida.author;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class AuthorsCliente {
@Getter @Setter private Long id;
@Getter @Setter private String firstName;
@Getter @Setter private String lastName;

	public AuthorsCliente(int i, String firstName, String lastName) {
		this.id = (long) i;
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
