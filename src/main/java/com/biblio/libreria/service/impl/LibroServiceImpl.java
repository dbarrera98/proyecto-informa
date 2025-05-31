package com.biblio.libreria.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.biblio.libreria.dtos.LibroDTO;
import com.biblio.libreria.entities.LibroEntity;
import com.biblio.libreria.repository.LibroRepository;
import com.biblio.libreria.service.ILibroService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements ILibroService{

	private final LibroRepository libroRepository;

    @Override
    public LibroDTO create(LibroDTO libroDTO) {
        libroRepository.insertarLibro(libroDTO.getTitulo(), libroDTO.getAutorId());
        List<LibroEntity> libros = libroRepository.consultarLibros();
        LibroEntity libro = libros.stream()
                .filter(l -> l.getTitulo().equals(libroDTO.getTitulo()) && l.getAutorId().equals(libroDTO.getAutorId()))
                .reduce((first, second) -> second)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado tras inserción"));
        libroRepository.agregarLibro(libro);
        return new LibroDTO(libro.getId(), libro.getTitulo(), libro.getAutorId());
    }

    @Override
    public LibroDTO update(Integer id, LibroDTO libroDTO) {
        LibroEntity libroActual = libroRepository.consultarLibro(id);
        if (libroActual == null) {
            throw new RuntimeException("Libro no encontrado con id: " + id);
        }

        String nuevoTitulo = libroDTO.getTitulo() != null ? libroDTO.getTitulo() : libroActual.getTitulo();

        Integer nuevoAutorId = libroDTO.getAutorId() != null ? libroDTO.getAutorId() : libroActual.getAutorId();

        libroRepository.actualizarLibro(id, nuevoTitulo, nuevoAutorId);

        LibroEntity libroActualizado = libroRepository.consultarLibro(id);
        return new LibroDTO(libroActualizado.getId(), libroActualizado.getTitulo(), libroActualizado.getAutorId());
    }

    @Override
    public void delete(Integer id) {
        libroRepository.eliminarLibro(id);
    }

    @Override
    public LibroDTO getById(Integer id) {
        LibroEntity libro = libroRepository.consultarLibro(id);
        return new LibroDTO(libro.getId(), libro.getTitulo(), libro.getAutorId());
    }

    @Override
    public List<LibroDTO> getAll() {
        return libroRepository.consultarLibros()
                .stream()
                .map(l -> new LibroDTO(l.getId(), l.getTitulo(), l.getAutorId()))
                .collect(Collectors.toList());
    }
}
