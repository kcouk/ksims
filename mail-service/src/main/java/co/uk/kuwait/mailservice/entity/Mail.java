package co.uk.kuwait.mailservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "mail", catalog = "mail")
public class Mail {

	@Id
	@GeneratedValue
	private Integer id; // id
	private String name; // mail_name
	private String description;
	private String longDesc;


	public Mail(String name) {
		super();
		this.name = name;
	}


}
