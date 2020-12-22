package com.teletracnavman.interview.codetest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.teletracnavman.interview.codetest.entity.RecordEntity;

@Repository
public interface RecordRepository extends JpaRepository<RecordEntity,Long > {

}
