package co.uk.kuwait.client;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
// @Setter
@NoArgsConstructor
public class Notification {

	private Integer id; // id
	private String name; // notification_name
	private String description;


	public Notification(String name) {
		super();
		this.name = name;
	}


}
