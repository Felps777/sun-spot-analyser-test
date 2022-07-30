package com.example.sunspotanalysertest.apirest.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GridDTO implements Serializable {

	private static final long serialVersionUID = 6403398558887581807L;

	// to avoid show this field as an input parameter on the endpoint
	@Hidden
	private Long id;
	private Integer size;
	private String values;

}
