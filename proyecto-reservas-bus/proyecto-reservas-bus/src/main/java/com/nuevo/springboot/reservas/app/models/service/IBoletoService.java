package com.nuevo.springboot.reservas.app.models.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.nuevo.springboot.reservas.app.models.entity.Boleto;

public interface IBoletoService {

	public void save(Boleto entity);
    public Boleto findOne(Integer id);
    public void delete(Integer id);
    public List<Boleto> findAll();
	public Boleto save1(Boleto entity);
	public Boleto findById(Integer id);
	public void delete1(Integer id);
	
	
	List<Boleto> findByIdUsuario(Long  usuarioId);
	
	List<Boleto> findByIdUsuarioEfectivo(Long  usuarioId);
	List<Boleto> findByIdUsuarioTarjeta(Long  usuarioId);
	
	List<Boleto> findByUnidadId(Integer unidadId);

	List<Boleto> findByIdUsuarioCronograma(Long  usuarioId,Integer  cronogramaId);
	
	List<Boleto> getBoletosFechaActualMetodo();
	
	List<Boleto> getBoletosFechaActual();
	
	List<Boleto> getBoletosFechaActualMetodoDescuento();
	
	List<Boleto> findBoletosByUnidadIdAndFecha(Integer unidadId, LocalDate fecha);
	
}
