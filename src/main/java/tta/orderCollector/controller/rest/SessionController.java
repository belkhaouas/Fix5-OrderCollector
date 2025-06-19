package tta.orderCollector.controller.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tta.orderCollector.tasks.FixRouter;

@RestController
@RequestMapping("/session") 
public class SessionController {

	private static final Logger logger = LoggerFactory.getLogger(SessionController.class);
	
	@Resource(name = "fixRouter")
	FixRouter fixRouter;
	
	@GetMapping("/connect")
	public String connect() {
		logger.info(" connect");
		return fixRouter.connectSessionOEG();		
	}

	@GetMapping("/disconnect")
	public String disconnect() {
		return fixRouter.disconnectSessionOEG();
	}
	
	@GetMapping(value = "/statusFix", produces = MediaType.APPLICATION_JSON_VALUE)
	public int statusFix() {
		if(fixRouter.getStreamReceiver()!= null)
		return fixRouter.getStreamReceiver().isStatusFix()? 1 : 0;
		else return -1;
		
	}
	
}
