package com.infosys.datamigrationprocessingapp.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.infosys.datamigrationprocessingapp.modal.Employee;
import com.infosys.datamigrationprocessingapp.reader.DatabaseItemReader;
import com.infosys.datamigrationprocessingapp.writer.CsvFileItemWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfigurer {
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	DatabaseItemReader dbReader;

	@Autowired
	CsvFileItemWriter csvFileWriter;

	@Bean
	public Job createJob() {
		return jobBuilderFactory.get("Employee-Migration").incrementer(new RunIdIncrementer()).flow(createStep()).end()
				.build();
	}

	@Bean
	public Step createStep() {
		return stepBuilderFactory.get("MyStep").<Employee, Employee>chunk(10000).reader(dbReader).writer(csvFileWriter)
				.build();
	}

	@Override
	public void setDataSource(DataSource dataSource) {

	}

}
