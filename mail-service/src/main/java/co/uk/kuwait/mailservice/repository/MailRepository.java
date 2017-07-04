package co.uk.kuwait.mailservice.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import co.uk.kuwait.mailservice.entity.Mail;

@RepositoryRestResource(collectionResourceDescription = @Description("Mail Repository"), path = "mails")
public interface MailRepository extends JpaRepository<Mail, Integer>, MailRepositoryCustom {

	// /mail/search/by-name?rn=xxxxxxx
	@RestResource(path = "by-name")
	Collection<Mail> findByName(@Param("name") String name);


	@RestResource(path = "by-name-desc")
	Collection<Mail> findByNameAndDescription(@Param("name") String name, @Param("desc") String desc);


	@RestResource(path = "by-like-name")
	Collection<Mail> findByNameLikeOrDescription(@Param("name") String name, @Param("desc") String desc);

}


interface MailRepositoryCustom {

	Collection<Mail> findByNombre(@Param("name") String name);
}

