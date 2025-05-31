package com.biblio.libreria.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibroEntity implements Serializable {

	public static final long serialVersionUID = 1L;
	
	private Integer id;
	
    private String titulo;
	
    private Integer autorId;
}
