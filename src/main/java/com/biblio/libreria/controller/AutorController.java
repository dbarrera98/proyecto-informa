package com.biblio.libreria.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblio.libreria.dtos.ApiResponse;
import com.biblio.libreria.dtos.AutorDTO;
import com.biblio.libreria.service.IAutorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/autor")
@RequiredArgsConstructor
public class AutorController {

	private final IAutorService autorService;

	@PostMapping
    public ResponseEntity<ApiResponse<AutorDTO>> create(@RequestBody AutorDTO autorDTO) {
        AutorDTO autor = autorService.create(autorDTO);
        ApiResponse<AutorDTO> response = new ApiResponse<>(true, "Autor creado exitosamente", autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AutorDTO>> update(@PathVariable Integer id, @RequestBody AutorDTO autorDTO) {
        AutorDTO updatedAutor = autorService.update(id, autorDTO);
        ApiResponse<AutorDTO> response = new ApiResponse<>(true, "Autor actualizado exitosamente", updatedAutor);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Integer id) {
        autorService.delete(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Autor eliminado exitosamente", null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AutorDTO>> getById(@PathVariable Integer id) {
        AutorDTO autor = autorService.getById(id);
        ApiResponse<AutorDTO> response = new ApiResponse<>(true, "Autor encontrado", autor);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AutorDTO>>> getAll() {
        List<AutorDTO> autores = autorService.getAll();
        ApiResponse<List<AutorDTO>> response = new ApiResponse<>(true, "Lista de autores obtenida exitosamente", autores);
        return ResponseEntity.ok(response);
    }
}
