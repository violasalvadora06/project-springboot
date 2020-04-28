package com.latihan.rest.sederhana.repository;

import com.latihan.rest.sederhana.model.Lagu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LaguRepository extends JpaRepository<Lagu, Long> {

}