package com.example.sunspotanalysertest.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class Matrix<N> implements Serializable {

	static final long serialVersionUID = -5147121320442393376L;

	public N[] numberedMatrix;

}