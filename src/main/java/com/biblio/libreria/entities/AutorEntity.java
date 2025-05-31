package com.biblio.libreria.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutorEntity implements Serializable {

	public static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String nombre;
	
}
