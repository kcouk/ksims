package co.uk.kuwait.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import feign.Feign;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/notifications")
class NotificationApiGateway {

	private final NotificationReader notificationReader;

	// private final NotificationWriter notificationWriter;

	// @Autowired
	// public NotificationApiGateway(NotificationReader notificationReader, NotificationWriter
	// notificationWriter) {
	// this.notificationReader = notificationReader;
	// this.notificationWriter = notificationWriter;
	// }

	@Autowired
	public NotificationApiGateway(NotificationReader notificationReader) {
		this.notificationReader = notificationReader;
	}

	public Collection<String> fallback() {
		return new ArrayList<>();
	}

	@HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(method = RequestMethod.GET, value = "/names")
	public Collection<String> names() {



		return this.notificationReader.read().getContent().stream().map(Notification::getName).collect(Collectors.toList());
	}


	@HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(method = RequestMethod.GET, value = "/names2")
	public Collection<String> names2() {

		NotificationReader nr = Feign.builder().target(NotificationReader.class, "http://localhost.8080");

		return nr.read().getContent().stream().map(Notification::getName).collect(Collectors.toList());

	}



	// @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Long getNotification(@PathVariable Integer id) {
		log.warn("getNotification(" + id + ")");
		return this.notificationReader.getNotification(id).getContent().stream().count();


	}

	// @RequestMapping(method = RequestMethod.POST)
	// public void write(@RequestBody Notification notification) {
	// this.notificationWriter.write(notification.getNotificationName());
	// }
}


