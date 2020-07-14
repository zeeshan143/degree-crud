package zee.cms.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class CMSHome {

	@GetMapping("/")
	public String homePage() { return "Welcome to Degree CRUD operations...!"; }
}
