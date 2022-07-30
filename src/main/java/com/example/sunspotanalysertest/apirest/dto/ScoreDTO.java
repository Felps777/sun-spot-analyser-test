package com.example.sunspotanalysertest.apirest.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScoreDTO implements Serializable {

	private static final long serialVersionUID = -5915180669848384216L;

	private long x;
	private long y;
	private int score;

}
