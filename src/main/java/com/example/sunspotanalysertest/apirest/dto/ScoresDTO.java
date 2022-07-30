package com.example.sunspotanalysertest.apirest.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScoresDTO implements Serializable {

	private static final long serialVersionUID = 8533997259042039161L;

	@Builder.Default
	private List<ScoreDTO> scores = new ArrayList<>();
}
