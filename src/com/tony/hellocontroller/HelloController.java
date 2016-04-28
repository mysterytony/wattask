package com.tony.hellocontroller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hello")
public class HelloController
{
	
	@RequestMapping("/hellopage/{username}/{msg}")
	public ModelAndView hellopage(@PathVariable Map<String, String> para) throws Exception
	{
		ModelAndView modelandview = new ModelAndView("HelloPage");
		modelandview.addObject("welcomeMessage", "Hello World!");
		modelandview.addObject("username", para.get("username"));
		modelandview.addObject("msg", para.get("msg"));
		return modelandview;
	}
	
	@RequestMapping(value = "/helloform.html", method = RequestMethod.GET)
	public ModelAndView hellopageform()
	{
		ModelAndView modelandview = new ModelAndView("HelloForm");
		return modelandview;
	}
	
	@RequestMapping(value = "/helloformsubmit.html", method = RequestMethod.POST)
	public ModelAndView hellopageformsubmit(
			@RequestParam(value = "name", defaultValue = "No Name") String name,
			@RequestParam(value = "message", defaultValue = "No Msg") String message)
	{
		ModelAndView modelandview = new ModelAndView("HelloFormSubmit");
		modelandview.addObject("name", name);
		modelandview.addObject("message", message);
		return modelandview;
	}
	
}
