package com.test.test.configs;

import com.test.test.entities.Product;
import com.test.test.repositories.ProductRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public ListItemReader<Product> reader() {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Product product = new Product();
            product.setNombre("Product " + i);
            product.setCantidad((int) (Math.random() * 100));
            product.setPrecio(Math.random() * 100);

            products.add(product);
        }
        return new ListItemReader<>(products);
    }

    @Bean
    public ItemProcessor<Product, Product> processor() {
        return product -> {
            return product;
        };
    }

    @Bean
    public ItemWriter<Product> writer(ProductRepository productRepository) {
        return products -> productRepository.saveAll(products);
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager, ItemWriter<Product> writer) {
        return new StepBuilder("step1", jobRepository)
                .<Product, Product>chunk(1000, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

    @Bean
    public Job importProductsJob(JobRepository jobRepository, Step step1) {
        return new JobBuilder("importProductsJob", jobRepository)
                .start(step1)
                .build();
    }
}