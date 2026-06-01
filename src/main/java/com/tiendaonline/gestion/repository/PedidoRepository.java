package com.tiendaonline.gestion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiendaonline.gestion.model.Pedido;
import com.tiendaonline.gestion.model.Usuario;

public interface PedidoRepository  extends JpaRepository<Pedido, Long>{
	
	List<Pedido> findByUsuario(Usuario usuario);

}
