package co.uk.kuwait.mailservice.repository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import co.uk.kuwait.mailservice.entity.Mail;

public class MailRepositoryImpl implements MailRepositoryCustom {


	@Autowired
	MailRepository mailRepository;

	@Override
	public Collection<Mail> findByNombre(@Param("name") String name) {
		return this.mailRepository.findByName(name);
	}
}

