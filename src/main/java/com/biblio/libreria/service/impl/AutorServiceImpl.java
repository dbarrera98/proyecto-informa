package com.biblio.libreria.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.biblio.libreria.dtos.AutorDTO;
import com.biblio.libreria.dtos.LibroDTO;
import com.biblio.libreria.entities.AutorEntity;
import com.biblio.libreria.entities.LibroEntity;
import com.biblio.libreria.repository.AutorRepository;
import com.biblio.libreria.repository.LibroRepository;
import com.biblio.libreria.service.IAutorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutorServiceImpl implements IAutorService {

	private final AutorRepository autorRepository;
	private final LibroRepository libroRepository;

	@Override
	public AutorDTO create(AutorDTO autorDTO) {
	    autorRepository.insertarAutor(autorDTO.getNombre());
	    List<AutorEntity> autores = autorRepository.consultarAutores();
	    AutorEntity autor = autores.stream()
	            .filter(a -> a.getNombre().equals(autorDTO.getNombre()))
	            .reduce((first, second) -> second)
	            .orElseThrow(() -> new RuntimeException("Autor no encontrado tras inserción"));
	    
	    return new AutorDTO(autor.getId(), autor.getNombre(), List.of());
	}

	@Override
	public AutorDTO update(Integer id, AutorDTO autorDTO) {
	    autorRepository.actualizarAutor(id, autorDTO.getNombre());
	    AutorEntity autor = autorRepository.consultarAutor(id);
	    List<LibroEntity> libros = libroRepository.consultarLibrosPorAutor(id);
	    List<LibroDTO> librosDTO = libros.stream()
	        .map(libro -> new LibroDTO(libro.getId(), libro.getTitulo(), libro.getAutorId()))
	        .collect(Collectors.toList());
	    return new AutorDTO(autor.getId(), autor.getNombre(), librosDTO);
	}

	@Override
	public void delete(Integer id) {
	    autorRepository.eliminarAutor(id);
	}

	@Override
	public AutorDTO getById(Integer id) {
	    AutorEntity autor = autorRepository.consultarAutor(id);
	    List<LibroEntity> libros = libroRepository.consultarLibrosPorAutor(id);
	    List<LibroDTO> librosDTO = libros.stream()
	        .map(libro -> new LibroDTO(libro.getId(), libro.getTitulo(), libro.getAutorId()))
	        .collect(Collectors.toList());
	    return new AutorDTO(autor.getId(), autor.getNombre(), librosDTO);
	}

	@Override
	public List<AutorDTO> getAll() {
	    return autorRepository.consultarAutores()
	            .stream()
	            .map(a -> {
	                List<LibroEntity> libros = libroRepository.consultarLibrosPorAutor(a.getId());
	                List<LibroDTO> librosDTO = libros.stream()
	                    .map(libro -> new LibroDTO(libro.getId(), libro.getTitulo(), libro.getAutorId()))
	                    .collect(Collectors.toList());
	                return new AutorDTO(a.getId(), a.getNombre(), librosDTO);
	            })
	            .collect(Collectors.toList());
	}
}
