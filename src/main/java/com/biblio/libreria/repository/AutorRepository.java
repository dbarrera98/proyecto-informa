package com.biblio.libreria.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.biblio.libreria.entities.AutorEntity;

@Repository
public class AutorRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertarAutor(String nombre) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("insertar_autor");
        jdbcCall.execute(nombre);
    }

    public void actualizarAutor(Integer id, String nombre) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("actualizar_autor");
        jdbcCall.execute(id, nombre);
    }

    public void eliminarAutor(Integer id) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("eliminar_autor");
        jdbcCall.execute(id);
    }

    public AutorEntity consultarAutor(Integer id) {
        String sql = "SELECT * FROM consultar_autor(?)";
        return jdbcTemplate.queryForObject(sql, this::mapRowToAutor, id);
    }

    public List<AutorEntity> consultarAutores() {
        String sql = "SELECT * FROM consultar_autores()";
        return jdbcTemplate.query(sql, this::mapRowToAutor);
    }

    private AutorEntity mapRowToAutor(ResultSet rs, int rowNum) throws SQLException {
        AutorEntity autor = new AutorEntity();
        autor.setId(rs.getInt("id"));
        autor.setNombre(rs.getString("nombre"));
        return autor;
    }
}
