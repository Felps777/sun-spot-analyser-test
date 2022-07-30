package com.example.sunspotanalysertest.persistence;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter
@Table(name = "Grids", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class GridsEntity implements Serializable {

	private static final long serialVersionUID = -6954098001221654907L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	private Integer size;

	@NonNull
	private int[] linearMatrix;

	@UpdateTimestamp
	@NonNull
	@Builder.Default
	private LocalDateTime updatedTime = LocalDateTime.now();

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof GridsEntity))
			return false;
		GridsEntity grid = (GridsEntity) o;
		return id != null && id.equals(grid.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
