package ua.com.diploma.onheight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/report")
public class ReportController {
    @GetMapping("/charts")
    public String charts() {
        return "report/charts-list";
    }
}
