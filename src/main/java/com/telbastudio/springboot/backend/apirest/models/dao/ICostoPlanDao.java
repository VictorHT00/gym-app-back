package com.telbastudio.springboot.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telbastudio.springboot.backend.apirest.models.entity.CostoPlan;

public interface ICostoPlanDao extends JpaRepository<CostoPlan, Long>{

}
