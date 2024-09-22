package hello.hello_spring.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {
	
	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String hello(Model model) {
		model.addAttribute("data", "spring!!");
		return "hello";
	}
	
	@GetMapping("hello-mvc")
	public String helloMvc(@RequestParam(required = false, value = "name") String name, Model model) {
		System.out.println("name = " + name);
		model.addAttribute("name", name);
		return "hello-template";
	}

	@GetMapping("hello-string")
	@ResponseBody
	public String helloString(@RequestParam(required = false, value = "name") String name) {
		return "hello " + name;
	}
	
	@GetMapping("hello-api")
	@ResponseBody
	public Hello helloApi(@RequestParam(required = false, value = "name") String name) {
		Hello hello = new Hello();
		hello.setName(name);
		return hello;
	}

	@Getter @Setter
	public static class Hello {
		private String name;
	}
}
