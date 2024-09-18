package com.telbastudio.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="clientes")
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "no puede estar vacío")
	@Size(min=4, max=30, message = "el tamaño tiene que estar entre 4 y 30")
	@Column(nullable = false)
	private String nombre;
	
	@NotEmpty(message = "no puede estar vacío")
	@Size(min=4, max=30, message = "el tamaño tiene que estar entre 4 y 30")
	private String apellido;
	
	//@NotEmpty(message = "no puede estar vacío")
	//@Column(nullable = false, unique = true)
	private String telefono;
	
	@Column(name = "telefono_emergencia", nullable = true)
	private String telefonoEmergencia;
	
	@NotNull(message = "no puede estar vacío")
	@Column(name = "fecha_inscripcion")
	@Temporal(TemporalType.DATE)
	private Date fechaInscripcion;
	
	@Column(name = "meses_suscripcion")
	private int mesesSuscripcion;
	
	@Column(nullable = true, name = "siguiente_pago")
	@Temporal(TemporalType.DATE)
	private Date siguientePago;
	
	@Column(nullable = true, name = "vigencia_dia")
	@Temporal(TemporalType.DATE)
	private Date vigenciaDia;
	
	@Column(nullable = true, name = "pagos_consecutivos")
	private boolean pagoConsecutivo;
	
	private String foto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "costo_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private CostoPlan costoPlan;
	
	/*@NotNull(message="no puede ser vacía")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "region_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Region region;
	
	
	@JsonIgnoreProperties(value = {"cliente", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Factura> facturas;
	*/
	
	@JsonIgnoreProperties(value = {"cliente", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Mensualidad> mensualidades;
	
	@NotNull(message = "no puede estar vacío")
	@Column(nullable = false, unique = true, name = "no_cliente")
	private int noCliente;

	public Cliente() {
		//facturas = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Mensualidad> getMensualidades() {
		return mensualidades;
	}

	public void setMensualidades(List<Mensualidad> mensualidades) {
		this.mensualidades = mensualidades;
	}

	public boolean isPagoConsecutivo() {
		return pagoConsecutivo;
	}

	public void setPagoConsecutivo(boolean pagoConsecutivo) {
		this.pagoConsecutivo = pagoConsecutivo;
	}

	public Date getSiguientePago() {
		return siguientePago;
	}

	public void setSiguientePago(Date siguientePago) {
		this.siguientePago = siguientePago;
	}

	public int getMesesSuscripcion() {
		return mesesSuscripcion;
	}

	public void setMesesSuscripcion(int mesesSuscripcion) {
		this.mesesSuscripcion = mesesSuscripcion;
	}

	public Date getVigenciaDia() {
		return vigenciaDia;
	}

	public void setVigenciaDia(Date vigenciaDia) {
		this.vigenciaDia = vigenciaDia;
	}

	public CostoPlan getCostoPlan() {
		return costoPlan;
	}

	public void setCostoPlan(CostoPlan costoPlan) {
		this.costoPlan = costoPlan;
	}

	public int getNoCliente() {
		return noCliente;
	}

	public void setNoCliente(int noCliente) {
		this.noCliente = noCliente;
	}

	public String getTelefonoEmergencia() {
		return telefonoEmergencia;
	}

	public void setTelefonoEmergencia(String telefonoEmergencia) {
		this.telefonoEmergencia = telefonoEmergencia;
	}
}
