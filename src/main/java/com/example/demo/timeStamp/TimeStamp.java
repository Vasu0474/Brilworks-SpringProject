package com.example.demo.timeStamp;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class TimeStamp {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_time")
	private Date modified;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time")
	private Date created;

	@PrePersist
	public void prePersist() {
		modified = new Date();
		created = new Date();
	}

	@PreUpdate
	public void preUpdate() {

		modified = new Date();
	}

}
