package com.accountbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accountbook.controller.base.ApiController;
import com.accountbook.model.dto.Subject;
import com.accountbook.service.SubjectService;
import com.accountbook.utils.JsonUtils;

/**
 * [Subject API]
 * 
 * @author cano.su
 * @since 2024/10/25
 */
@RestController
@RequestMapping("/subject")
public class SubjectController extends ApiController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping(value = "/list", produces = "application/json; charset=UTF-8")
    public String list() {
        final List<Subject> subjects = subjectService.findAll();
        return JsonUtils.toJson(subjects);
    }

}
