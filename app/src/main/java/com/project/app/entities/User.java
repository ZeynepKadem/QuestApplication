package com.project.app.entities;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",nullable = false)
	private long id;
	
	@Column(name="userName",nullable = false)
	private String userName;
	
	@Column(name="password",nullable = false)
	private String password;
	
	@Column(name="avatar",nullable = false)
	private int avatar;
	

}
