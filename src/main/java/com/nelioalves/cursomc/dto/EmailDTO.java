package com.nelioalves.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailDTO implements Serializable {

    private static final long serialVersionUID = 4601985480927865729L;
    
    @Email(message = "Email inválido")
    @NotEmpty(message = "Preenchimento obrigatório")
    private String email;

    public EmailDTO() {
	super();
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

}
