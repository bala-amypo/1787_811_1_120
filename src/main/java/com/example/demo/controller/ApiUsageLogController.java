package com.example.demo.controller;

import com.example.demo.entity.ApiUsageLog;
import com.example.demo.service.ApiUsageLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usage")
public class ApiUsageLogController {

    private final ApiUsageLogService service;

    public ApiUsageLogController(ApiUsageLogService service) {
        this.service = service;
    }

    @PostMapping
    public ApiUsageLog log(@RequestBody ApiUsageLog log) {
        return service.logUsage(log);
    }

    @GetMapping("/{apiKeyId}")
    public List<ApiUsageLog> getByKey(@PathVariable Long apiKeyId) {
        return service.getUsageForApiKey(apiKeyId);
    }

    @GetMapping("/today/{apiKeyId}")
    public List<ApiUsageLog> getToday(@PathVariable Long apiKeyId) {
        return service.getUsageForToday(apiKeyId);
    }

    @GetMapping("/count/{apiKeyId}")
    public int countToday(@PathVariable Long apiKeyId) {
        return service.countRequestsToday(apiKeyId);
    }
}
