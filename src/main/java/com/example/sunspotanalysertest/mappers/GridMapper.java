package com.example.sunspotanalysertest.mappers;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.example.sunspotanalysertest.apirest.dto.GridDTO;
import com.example.sunspotanalysertest.engine.GridTools;
import com.example.sunspotanalysertest.persistence.GridEntity;

@Component
public class GridMapper {

	public GridDTO convertToDto(GridEntity entity) {
		String strArr = Arrays.toString(entity.getLinearMatrix());
		return GridDTO.builder().id(entity.getId()).size(entity.getSize()).values(strArr).build();
	}

	public GridEntity convertToEntity(GridDTO dto) {

		if (dto == null) {
			return null;
		}

		int[] mtxStr = GridTools.parseStringToIntArray(dto.getValues());

		return GridEntity.builder().id(dto.getId()).size(dto.getSize()).linearMatrix(mtxStr).build();
	}

}
