package com.proyecto.services;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.controllers.dto.UsuarioItem;
import com.proyecto.exceptions.ApiException;
import com.proyecto.models.Usuarios;
import com.proyecto.repository.UsuariosRepository;

@Service
public class UsuariosServices {
	
	@Autowired
	UsuariosRepository usuariosRepository;
	
	private String formatSeo(String text) {
		return StringUtils.strip(text.replaceAll("([^a-zA-Z0-9]+)", "-"), "-");
	}
	
	public UsuarioItem getUsuario(int id) {

		try {
			Optional<Usuarios> usuario = usuariosRepository.findById(id);
			UsuarioItem usuarioItem = new UsuarioItem();
			if (usuario.isPresent()) {
				usuarioItem.id_usuario = usuario.get().getId_usuario();
				usuarioItem.usuario = usuario.get().getUsuario();
				usuarioItem.clave = usuario.get().getClave();
				usuarioItem.nombre = usuario.get().getNombre();
				usuarioItem.apellido = usuario.get().getApellido();
				usuarioItem.dni = usuario.get().getDni();
				usuarioItem.correo = usuario.get().getCorreo();
				usuarioItem.verificar_correo = usuario.get().getVerificarCorreo();
				usuarioItem.fecha_nacimiento = usuario.get().getFecha_nacimiento();
				usuarioItem.nacionalidad = usuario.get().getNacionalidad();
				usuarioItem.sexo = usuario.get().getSexo();
				usuarioItem.telefono = usuario.get().getTelefono();
				usuarioItem.archivoImagen = "/image/"+ usuario.get().getId_usuario() + "/" + formatSeo(usuario.get().getUsuario())+ ".jpg";
				return usuarioItem;
			} else {
				throw new ApiException(404, "No existe el usuario");
			}
		} catch (ApiException e) {
			throw e;
		} catch (Exception exception) {
			throw exception;
		}

	}
	
	public int addUsuario(UsuarioItem usuarioIn) throws NoSuchAlgorithmException {

		try {
			Usuarios usuario = new Usuarios();
			usuario.setUsuario(usuarioIn.usuario);
			usuario.setClave(usuarioIn.clave);
			usuario.setNombre(usuarioIn.nombre);
			usuario.setApellido(usuarioIn.apellido);
			usuario.setDni(usuarioIn.dni);
			usuario.setCorreo(usuarioIn.correo);
			usuario.setVerificar_correo(false);
			usuario.setFecha_nacimiento(new Date());
			usuario.setNacionalidad(usuarioIn.nacionalidad);
			usuario.setSexo(usuarioIn.sexo);
			usuario.setTelefono(usuarioIn.telefono);
			usuario.setImagen(usuarioIn.imagen);
			usuario = usuariosRepository.save(usuario);
			return usuario.getId_usuario();
		} catch (ApiException e) {
			throw e;
		} catch (Exception exception) {
			throw exception;
		}

	}
	
}
