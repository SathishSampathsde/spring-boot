package com.elevenamspringboot.application.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elevenamspringboot.application.payload.HelloWorldBean;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldController {

    // @GetMapping
    // public HelloWorldBean getHelloWorld() {
    //     return new HelloWorldBean(true, "Welcome to springboot");
    // }

    @GetMapping("{id}")
    public HelloWorldBean getHelloWorldName(@PathVariable(name = "id") String name) {
        return new HelloWorldBean(true, name);
    }

    @GetMapping
    public HelloWorldBean getHelloWorld(@RequestParam(name = "name",defaultValue = "sathish") String name) {
        return new HelloWorldBean(true, name);
    }

    @PostMapping
    public HelloWorldBean postMethod(@RequestBody HelloWorldBean data) {
        return new HelloWorldBean(data.isSuccess(), data.getMessage());
    }

    @PutMapping
    public HelloWorldBean putMethod(@RequestBody HelloWorldBean data) {
        return new HelloWorldBean(data.isSuccess(), data.getMessage());
    }

    @DeleteMapping
    public HelloWorldBean deleteMethod(@RequestBody HelloWorldBean data) {
        return new HelloWorldBean(data.isSuccess(), data.getMessage());
    }

}
