package co.uk.kuwait.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("notification-service")
interface NotificationReader {

	@RequestMapping(method = RequestMethod.GET, value = "/notifications")
	Resources<Notification> read();


	@RequestMapping(method = RequestMethod.GET, value = "/notifications/{id}")
	Resources<Notification> getNotification(@PathVariable("id") Integer id);

}
