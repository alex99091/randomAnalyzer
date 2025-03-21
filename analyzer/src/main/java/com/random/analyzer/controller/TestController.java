package com.random.analyzer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class TestController {

    @GetMapping("test")
    public String test() {
        return "test";
    }
}
