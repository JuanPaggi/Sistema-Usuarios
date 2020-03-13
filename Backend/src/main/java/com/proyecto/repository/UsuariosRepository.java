package com.proyecto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.models.Usuarios;

/**
 * @author pachi
 * Repositorio de la tabla usuarios.
 */

@Repository
public interface UsuariosRepository extends JpaRepository <Usuarios , Integer>  {

	// Consulta para traer un usuario por su user y clave
	@Query(value="select * from usuarios where usuario = ?1 and clave = ?2", nativeQuery=true)
	Optional<Usuarios> findByUser(String usuario, String clave);
	
	// Consulta para traer un usuario por su user y clave
	@Query(value="UPDATE usuarios SET clave = ?2 WHERE id_usuario = ?1", nativeQuery=true)
	int cambiarClave(int id, String claveNueva);
	
}
