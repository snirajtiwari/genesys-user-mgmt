package com.genesys.usermgmt.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "sequence")
public class Sequence implements Serializable {
	private static final long serialVersionUID = 1696926036927427862L;

	private Long sequenceId;
	private String tableName;

	@Id
	@Column(name = "tablename")
	public String getTableName() {
		return tableName;
	}

	@Column(name = "sequenceid")
	public Long getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(Long sequenceId) {
		this.sequenceId = sequenceId;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
