package com.proyecto.services;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

	private static final String error404 = "No existe el usuario";

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
				usuarioItem.archivoImagen = "/image/" + usuario.get().getId_usuario() + "/"
						+ formatSeo(usuario.get().getUsuario()) + ".jpg";
				return usuarioItem;
			} else {
				throw new ApiException(404, error404);
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

	public List<UsuarioItem> getAllUsuarios() {

		try {
			List<Usuarios> usuarios = usuariosRepository.findAll();
			List<UsuarioItem> out = new ArrayList<>();
			for (Usuarios usuario : usuarios) {
				UsuarioItem usuarioItem = new UsuarioItem();
				usuarioItem.id_usuario = usuario.getId_usuario();
				usuarioItem.usuario = usuario.getUsuario();
				usuarioItem.clave = usuario.getClave();
				usuarioItem.nombre = usuario.getNombre();
				usuarioItem.apellido = usuario.getApellido();
				usuarioItem.dni = usuario.getDni();
				usuarioItem.correo = usuario.getCorreo();
				usuarioItem.verificar_correo = usuario.getVerificarCorreo();
				usuarioItem.fecha_nacimiento = usuario.getFecha_nacimiento();
				usuarioItem.nacionalidad = usuario.getNacionalidad();
				usuarioItem.sexo = usuario.getSexo();
				usuarioItem.telefono = usuario.getTelefono();
				usuarioItem.archivoImagen = "/image/" + usuario.getId_usuario() + "/" + formatSeo(usuario.getUsuario())
						+ ".jpg";
				out.add(usuarioItem);
			}
			return out;
		} catch (ApiException e) {
			throw e;
		} catch (Exception exception) {
			throw exception;
		}

	}

	public void removeUsuario(int id) {

		try {
			Optional<Usuarios> usuario = usuariosRepository.findById(id);
			if (usuario.isPresent()) {
				usuariosRepository.delete(usuario.get());
			} else {
				throw new ApiException(404, error404);
			}
		} catch (ApiException e) {
			throw e;
		} catch (Exception exception) {
			throw exception;
		}

	}

	public void editUsuario(int id, UsuarioItem usuarioIn) throws NoSuchAlgorithmException {

		try {
			Optional<Usuarios> usuario = usuariosRepository.findById(id);
			if (usuario.isPresent()) {
				Usuarios usuarioObj = usuario.get();
				usuarioObj.setUsuario(usuarioIn.usuario);
				usuarioObj.setClave(usuarioIn.clave);
				usuarioObj.setNombre(usuarioIn.nombre);
				usuarioObj.setApellido(usuarioIn.apellido);
				usuarioObj.setDni(usuarioIn.dni);
				usuarioObj.setCorreo(usuarioIn.correo);
				usuarioObj.setVerificar_correo(false);
				usuarioObj.setFecha_nacimiento(new Date());
				usuarioObj.setNacionalidad(usuarioIn.nacionalidad);
				usuarioObj.setSexo(usuarioIn.sexo);
				usuarioObj.setTelefono(usuarioIn.telefono);
				usuarioObj.setImagen(usuarioIn.imagen);
				usuariosRepository.save(usuarioObj);
			} else {
				throw new ApiException(404, error404);
			}
		} catch (ApiException e) {
			throw e;
		} catch (Exception exception) {
			throw exception;
		}

	}

	/*
	 * Si el usuario existe en la base se retornara su id, de lo contrario se
	 * retornara 0.
	 */

	public int verificarLogin(String usuario, String clave) {

		try {
			Optional<Usuarios> user = usuariosRepository.findByUser(usuario, clave);
			if (user.isPresent()) {
				return user.get().getId_usuario();
			}
			return 0;
		} catch (ApiException e) {
			throw e;
		} catch (Exception exception) {
			throw exception;
		}

	}

	public int cambiarClave(int id, String claveActual, String claveNueva) {

		try {			
			Optional<Usuarios> user = usuariosRepository.findById(id);
			if (claveActual == user.get().getClave()) {
				return usuariosRepository.cambiarClave(id, claveNueva);
			} else {
				throw new ApiException(403, "La clave es incorrecta");
			}
		} catch (ApiException e) {
			throw e;
		} catch (Exception exception) {
			throw exception;
		}

	}

}
