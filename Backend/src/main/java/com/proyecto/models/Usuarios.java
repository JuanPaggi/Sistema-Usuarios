package com.proyecto.models;

import java.security.NoSuchAlgorithmException;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.proyecto.utils.Sha1Hasher;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)

@Entity
@Table(name="usuarios")
public class Usuarios {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_usuario;
	
	@Column(nullable = false)
	private String usuario;
	
	@Column(nullable = false)
	private String clave;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column(nullable = false)
	private String apellido;
	
	@Column(nullable = false)
	private String dni;
	
	@Column(nullable = false)
	private String correo;
	
	@Column(nullable = false)
	private boolean verificar_correo;
	
	@Column(nullable = false)
	private Date fecha_nacimiento;
	
	@Column(nullable = false)
	private String nacionalidad;
	
	@Column(nullable = false)
	private String sexo;
	
	@Column(nullable = false)
	private String telefono;
	
	@Column(nullable = false)
	private byte[] imagen;
	
	@Column(nullable = false)
	private byte[] imagen_checksum;
	
	public void setImagen(byte[] imagen) throws NoSuchAlgorithmException {
		this.imagen_checksum = Sha1Hasher.hashBytes(imagen);
		this.imagen = imagen;
	}
	
	public void setVerificarCorreo(boolean verificacion) {
		this.verificar_correo = verificacion;
	}
	
	public boolean getVerificarCorreo() {
		return this.verificar_correo;
	}
	
}
