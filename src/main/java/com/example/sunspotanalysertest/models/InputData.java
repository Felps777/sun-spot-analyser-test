package com.example.sunspotanalysertest.models;

import java.io.Serializable;

import lombok.Data;
import lombok.NonNull;

@Data
public class InputData<N extends Number> implements Serializable {

	private static final long serialVersionUID = 6800928975389599687L;
	//
	@NonNull
	private Long size;
	@NonNull
	private Matrix<N> integerMatrix;

}
