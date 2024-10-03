package com.test.test.controllers;

import com.test.test.services.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @GetMapping("/run")
    public ResponseEntity<String> runBatch() {
        batchService.runBatchJob();
        return ResponseEntity.ok("Batch job started successfully");
    }
}
