package com.example.sunspotanalysertest.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sunspotanalysertest.apirest.dto.GridDTO;
import com.example.sunspotanalysertest.apirest.dto.ScoresDTO;
import com.example.sunspotanalysertest.engine.GridTools;
import com.example.sunspotanalysertest.mappers.GridMapper;
import com.example.sunspotanalysertest.persistence.GridRepository;
import com.example.sunspotanalysertest.persistence.GridsEntity;

@Service
public class GridService {

	@Autowired
	private GridRepository gridRepository;

	@Autowired
	private GridTools gridTools;

	@Autowired
	private GridMapper gridMapper;

	public GridsEntity addGrid(GridDTO dto) {

		return gridRepository.save(gridMapper.convertToEntity(dto));

	}

	public ScoresDTO findById(long id) {

		// find Grid data
		Optional<GridsEntity> gridsEntity = gridRepository.findById(id);

		ScoresDTO scoresDTO = ScoresDTO.builder().build();
		// calculates scores if entity exists
		gridsEntity.ifPresent((ent) -> {
			// System.out.println(ent.toString());
			scoresDTO.setScores(gridTools.calculateScores(ent));
		});

		return scoresDTO;
	}

}
