package com.distribuida.author;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Data
public class AuthorsCliente {
@Getter @Setter private Long id;
@Getter @Setter private String firstname;
@Getter @Setter private String lastname;
}
