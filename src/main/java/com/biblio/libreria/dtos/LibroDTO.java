package com.biblio.libreria.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LibroDTO implements Serializable{

	public static final long serialVersionUID = 1L;

	private Integer id;
	
    private String titulo;
	
    private Integer autorId;
}
