package com.example.sunspotanalysertest.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GridRepository extends JpaRepository<GridEntity, Long> {

}