package com.example.exercisesjava8;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
public class ExercisesJava8Application {

    public static void main(String[] args) {
        SpringApplication.run(ExercisesJava8Application.class, args);
    }

    private static final String[] SUBJECTS = {
            "Computer Science", "Engineering", "Business Administration",
            "Psychology", "Biology", "English", "History", "Mathematics",
            "Political Science", "Communications", "Art", "Music", "Physics",
            "Chemistry", "Environmental Science", "Social Work"
    };


    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository) {
        return args -> {
            Map<Student, List<Double>> studentGrades = new HashMap<>();
            Faker faker = new Faker();
            for (int i = 0; i < 200; i++) {
                String firstName = faker.name().firstName();
                String lastName = faker.name().lastName();
                String email = String.format("%s.%s@amigoscode.edu", firstName, lastName);
                Student student = new Student(
                        firstName, lastName, email, faker.number().numberBetween(18, 65), subjectGenerator()
                );
                studentRepository.save(student);
                studentGrades.put(student, generateGrades());
            }

            // TODO: 15.04.2023 wyciągnij za pomocą stream email studenta o id 10

            // TODO: 15.04.2023 wyciągnij za pomocą stream nazwiska wszystkich studnetów kierunku History

            // TODO: 15.04.2023 wyciągnij za pomocą stream wiek wszystkich studentów którycha nazwiska kończą się na a

            // TODO: 15.04.2023  wyciągnij za pomocą stream jakiegokolwiek studneta który studiuje na kierunku Chemistry

            // TODO: 15.04.2023 wyciągnij za pomocą stream jakiegokolwiek studneta o nazwisku Weber jeżeli nie istnieje rzuć wyjątek RuntimeException o komunikacie "There is no such student"

            // TODO: 15.04.2023 wyciągnij za pomocą stream jakiegokolwiek studneta w wieku 25 lat, jeżeli istnieje wypisz jego email

            //ĆWICZENIA Z MAP
            // TODO: 15.04.2023 wyciągnij z mapy  studntów studentów ze średnią ocen wyższą niż 4 i wyświetl ich maile

            // TODO: 15.04.2023 wyciągnij z mapy  jakiegokolwiek studneta który ma średnią ocen równą 4 i imię kończącę się na a jeżeli taki student nie istnieje rzuć wyjątek RuntimeException o tereści "There is no such student"

            // TODO: 15.04.2023 wyciągnij z mapy  studntów w wieku pomiędzy 29 a 36 i wyświetli ich 3 najwyższe oceny
        };
    }


    private static String subjectGenerator() {
        Random random = new Random();
        int index = random.nextInt(SUBJECTS.length);
        return SUBJECTS[index];
    }

    private List<Double> generateGrades() {
        List<Double> grades = new ArrayList<>();
        Random random = new Random();
        double minGrade = 2.0;
        double maxGrade = 5.0;
        double step = 0.5;

        for (int i = 0; i < 10; i++) {
            double grade = minGrade + step * random.nextInt((int) ((maxGrade - minGrade) / step) + 1);
            grades.add(grade);
        }

        return grades;
    }

}

