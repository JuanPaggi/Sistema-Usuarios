package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.models.Usuarios;

/**
 * @author pachi
 * Repositorio de la tabla usuarios.
 */

@Repository
public interface UsuariosRepository extends JpaRepository <Usuarios , Integer>  {

}
