package co.uk.kuwait.userservice.entity;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.hateoas.Identifiable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users", catalog = "user")
public class User implements UserDetails, Identifiable<Long> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1748508728260838232L;

	@Id
	@GeneratedValue
	private Long id;

	@LastModifiedDate
	@UpdateTimestamp
	Timestamp lastModified;

	@NotBlank
	private String username;

	@NotBlank
	// @JsonIgnore
	private String password;

	@Email
	private String email;

	private boolean enabled = true;

	private boolean accountNonLocked = true;

	private boolean accountNonExpired = true;

	private boolean credentialsNonExpired = true;

	private String rememberMeToken;


	@Column(unique = true, name = "CONFIRMATION_TOKEN")
	private String registrationConfirmationToken;

	@NotNull
	@CreatedDate
	@CreationTimestamp
	private Timestamp creationDate;

	private LocalDateTime lastLogin;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Authority> authorities;


	@Override
	public Collection<Authority> getAuthorities() {
		if (this.authorities == null) {
			return Collections.emptyList();
		}
		return this.authorities;
	}

	@Transient
	public void setUnencryptedPassword(String password) {
		this.setPassword(new BCryptPasswordEncoder(12, new SecureRandom()).encode(password));
	}

	public void addAuthority(Authority authority) {
		if (this.authorities == null) {
			this.authorities = new ArrayList<>();
		}
		this.authorities.add(authority);
	}

	public boolean hasAuthority(@NonNull String authority) {
		return this.hasAuthority(new Authority(authority));
	}

	public boolean hasAuthority(@NonNull Authority authority) {
		return !this.getAuthorities().isEmpty() && this.getAuthorities().contains(authority);
	}

	public String createRegistrationConfirmationToken() {
		final String uuid = UUID.randomUUID().toString();
		this.setRegistrationConfirmationToken(uuid);
		return uuid;
	}


	public void clearRegistrationConfirmationToken() {
		this.registrationConfirmationToken = null;
	}


	public void clearPassword() {
		this.password = null;
	}

}
