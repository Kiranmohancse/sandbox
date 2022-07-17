package com.apple.sandbox.sandbox.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.apple.sandbox.sandbox.model.JavaBeans;
import com.apple.sandbox.sandbox.service.manager.ShortnerHandler;

@RestController

public class UrlController {

    @Autowired
    private ShortnerHandler urlManager;

    @RequestMapping(value = "/serviceUrl/{url}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity shortenUrl(@PathVariable String url) {
    	 
        JavaBeans shortUrlEntry = urlManager.vanityURL(url);
        return ResponseEntity.ok(shortUrlEntry);
    }

    @RequestMapping(value = "/serviceKey/{key}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getUrl(@PathVariable String key) {
        String url = urlManager.getURL(key);
        return ResponseEntity.ok(url);
    }
    
    
    @GetMapping(value = "/redirect/{shorturl}")
    @ResponseBody
    public ResponseEntity<Void> redirect(@PathVariable String shorturl,HttpServletResponse response) throws IOException{
        String url = urlManager.getURL(shorturl);
		response.sendRedirect("//"+url);
		return new ResponseEntity<>(HttpStatus.FOUND);
		

    }
    
}
