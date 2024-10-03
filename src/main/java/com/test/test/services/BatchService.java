package com.test.test.services;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchService {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job importProductsJob;

    public void runBatchJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("startAt", System.currentTimeMillis()).toJobParameters();
            jobLauncher.run(importProductsJob, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
