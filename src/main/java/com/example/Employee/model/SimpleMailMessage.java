package com.example.Employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

public class SimpleMailMessage {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "recipient") // Change the column name to "recipient"
	    private String to;

	    private String subject;
	    private String text;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getTo() {
			return to;
		}
		public void setTo(String to) {
			this.to = to;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public SimpleMailMessage(Long id, String to, String subject, String text) {
			super();
			this.id = id;
			this.to = to;
			this.subject = subject;
			this.text = text;
		}
		public SimpleMailMessage() {
			super();
			// TODO Auto-generated constructor stub
		}

	
}
