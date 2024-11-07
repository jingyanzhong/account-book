package com.accountbook.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accountbook.controller.base.ApiController;

/**
 * [首頁]
 * 
 * @author cano.su
 * @since 2024/10/25
 */
@RestController
public class TestController extends ApiController {

    @GetMapping("/test")
    public String page(Model model) {
        return "{\"attr\":9}";
    }

}
