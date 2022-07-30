package com.example.sunspotanalysertest.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GridRepository extends JpaRepository<GridsEntity, Long> {

//	List<GridsEntity> findByGridNumber(String msisdnNumber);

//	List<GridsEntity> findByMsisdnNumberContaining(String partialNumber);

//	GridsEntity findByMsisdnNumberAndPinNumber(String msisdnNumber, String pinNumber);

}