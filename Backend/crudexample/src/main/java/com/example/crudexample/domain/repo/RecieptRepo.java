package com.example.crudexample.domain.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crudexample.domain.entity.Receipt;

public interface RecieptRepo extends JpaRepository<Receipt, Long> {

	

}
