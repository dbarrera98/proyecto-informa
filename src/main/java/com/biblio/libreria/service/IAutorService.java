package com.biblio.libreria.service;

import java.util.List;

import com.biblio.libreria.dtos.AutorDTO;

public interface IAutorService {

	AutorDTO create(AutorDTO autorDTO);
	
    AutorDTO update(Integer id, AutorDTO autorDTO);
    
    void delete(Integer id);
    
    AutorDTO getById(Integer id);
    
    List<AutorDTO> getAll();
}
