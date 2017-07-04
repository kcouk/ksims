package co.uk.kuwait.mailservice.resource;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.uk.kuwait.mailservice.entity.Mail;
import co.uk.kuwait.mailservice.repository.MailRepository;

@RestController
@RequestMapping("/rest/mails")
public class MailResource {

	@Autowired
	MailRepository mailRepository;


	@GetMapping("/all")
	public List<Mail> getAll() {

		return this.mailRepository.findAll();
	}


	@GetMapping("/{name}")
	public Collection<Mail> getMail(@PathVariable("name") final String name) {
		return this.mailRepository.findByName(name);

	}

	@GetMapping("/nombre/{name}")
	public Collection<Mail> getPolla(@PathVariable("name") final String name) {
		return this.mailRepository.findByNombre(name);

	}


	@GetMapping("/id/{id}")
	public Mail getId(@PathVariable("id") final Integer id) {
		return this.mailRepository.findOne(id);
	}

	// @GetMapping("/update/{id}/{name}")
	// public Mail update(@PathVariable("id") final Integer id, @PathVariable("name")
	// final String name) {
	//
	//
	// Mail mails = getId(id);
	// mails.setName(name);
	//
	// return mailRepository.save(mails);
	// }



	@GetMapping("/allHATEOAS")
	public @ResponseBody ResponseEntity<?> getAllHATEOAS() {

		List<Mail> mails = this.mailRepository.findAll();

		Resources<Mail> resources = new Resources<>(mails);

		return ResponseEntity.ok(resources);


	}



}
