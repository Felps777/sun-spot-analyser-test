package com.example.sunspotanalysertest.mappers;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.example.sunspotanalysertest.apirest.dto.GridDTO;
import com.example.sunspotanalysertest.persistence.GridsEntity;

@Component
public class GridMapper {

	public GridDTO convertToDto(GridsEntity entity) {

//		return GridDTO.builder().id(entity.getId()).size(entity.getSize()).values(entity.getMatrix().toString())
//				.build();
		return null;
	}

	public GridsEntity convertToEntity(GridDTO dto) {

		if (dto == null) {
			return null;
		}

//		List<Integer> a = dto.getValues();
//		System.out.println(a);
//		System.out.println(a.toString());
//		List list = new ObjectMapper().readValue(dto.getValues()., List.class);

		int[] mtxStr = Arrays.asList(dto.getValues().split(",")).stream().mapToInt(val -> Integer.valueOf(val.trim()))
				.toArray();

//		int numRows = mtxStr.length / dto.getSize();

//		List<List<Integer>> arrList = Arrays.stream(result) // 'array' is two-dimensional
//				.map(Arrays::asList).collect(Collectors.toList());

//		final ByteArrayOutputStream out = new ByteArrayOutputStream();
//		final ObjectMapper mapper = new ObjectMapper();
//		mapper.writeValue(out, arrList);
//		final byte[] data = out.toByteArray();
		return GridsEntity.builder().id(dto.getId()).size(dto.getSize()).linearMatrix(mtxStr).build();
	}

//	public void serializeCustomerAttributes() throws JsonProcessingException {
//		this.customerAttributeJSON = objectMapper.writeValueAsString(customerAttributes);
//	}
//
//	public void deserializeCustomerAttributes() throws IOException {
//		this.customerAttributes = objectMapper.readValue(customerAttributeJSON, HashMap.class);
//	}

}
