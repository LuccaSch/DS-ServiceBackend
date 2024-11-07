package com.ds.tp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicController {

@GetMapping("/home")
public String home() {
    return "public home";
}

}