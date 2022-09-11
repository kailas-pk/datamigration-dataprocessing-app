package com.infosys.datamigrationprocessingapp.writer;

import java.io.IOException;
import java.io.Writer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.infosys.datamigrationprocessingapp.modal.Employee;

@Component
@StepScope
public class CsvFileItemWriter extends FlatFileItemWriter<Employee>{
	
	public CsvFileItemWriter() {
		setResource(new FileSystemResource("csv/employee_records.csv"));
		setHeaderCallback(new FlatFileHeaderCallback() {
			@Override
			public void writeHeader(Writer writer) throws IOException {
				writer.write("EmployeeNo, Name, Email, Phone");
			}
		});
		setLineAggregator(lineAggregator());
	}

	private DelimitedLineAggregator<Employee> lineAggregator() {
		BeanWrapperFieldExtractor<Employee> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<Employee>();
		beanWrapperFieldExtractor.setNames(new String[] {"EmployeeNumber", "Name", "Email", "PhoneNumber"});
		
		DelimitedLineAggregator<Employee> delimitedLineAggregator = new DelimitedLineAggregator<Employee>();
		delimitedLineAggregator.setDelimiter(",");
		delimitedLineAggregator.setFieldExtractor(beanWrapperFieldExtractor);
		
		return delimitedLineAggregator;
	}
}
