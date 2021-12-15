package com.megaport.exercise;

import com.megaport.exercise.application.FileOperation;
import com.megaport.exercise.application.SortingHandler;
import com.megaport.exercise.domain.person.PersonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.megaport.exercise.utli.MegaPortConstants.DEFAULT_FILE_LOCATION;

@SpringBootApplication
public class ExerciseApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ExerciseApplication.class, args);
		final String sortCriteria = "FirstNameAndLastName";

		MegaPortConverter megaPortConverter = new MegaPortConverter(
				new FileOperation(),
				new SortingHandler(new PersonService())
		);

		megaPortConverter.sortAndGenerateNewFile("name.txt", sortCriteria);
	}
}
