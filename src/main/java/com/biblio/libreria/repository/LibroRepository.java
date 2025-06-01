package com.biblio.libreria.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.biblio.libreria.entities.LibroEntity;

@Repository
public class LibroRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	private final List<LibroEntity> libros = new ArrayList<>();

    public void agregarLibro(LibroEntity libro) {
        libros.add(libro);
    }

    public List<LibroEntity> consultarLibrosPorAutor(Integer autorId) {
        String sql = "SELECT * FROM libros WHERE autor_id = ?";
        return jdbcTemplate.query(sql, this::mapRowToLibro, autorId);
    }

    public void insertarLibro(String titulo, Integer autorId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("insertar_libro");
        jdbcCall.execute(titulo, autorId);
    }

    public void actualizarLibro(Integer id, String titulo, Integer autorId) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("actualizar_libro");
        jdbcCall.execute(id, titulo, autorId);
    }

    public void eliminarLibro(Integer id) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("eliminar_libro");
        jdbcCall.execute(id);
    }

    public LibroEntity consultarLibro(Integer id) {
        String sql = "SELECT * FROM consultar_libro(?)";
        return jdbcTemplate.queryForObject(sql, this::mapRowToLibro, id);
    }

    public List<LibroEntity> consultarLibros() {
        String sql = "SELECT * FROM consultar_libros()";
        return jdbcTemplate.query(sql, this::mapRowToLibro);
    }

    private LibroEntity mapRowToLibro(ResultSet rs, int rowNum) throws SQLException {
        LibroEntity libro = new LibroEntity();
        libro.setId(rs.getInt("id"));
        libro.setTitulo(rs.getString("titulo"));
        libro.setAutorId(rs.getInt("autor_id"));
        return libro;
    }
    
}
