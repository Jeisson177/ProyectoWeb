package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ItemPedido;




@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {



    
}