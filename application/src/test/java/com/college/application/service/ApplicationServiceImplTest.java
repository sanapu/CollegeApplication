package com.college.application.service;

import com.college.application.dto.ApplicationDTO;
import com.college.application.dto.ResponseDTO;
import com.college.application.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

//@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
class ApplicationServiceImplTest {

    @Autowired
    ApplicationServiceImpl applicationServiceImpl;

    @TestConfiguration
    static class ApplicationServiceTestContextConfiguration {

        @Bean
        public ApplicationServiceImpl applicationService() {
            return new ApplicationServiceImpl();
        }
    }


    @Test
    void validateApplication_Accept_Test() {
        ApplicationDTO applicationDTO = ApplicationDTO.builder()
                .firstName("Michael")
                .lastName("Grayson")
                .age(22)
                .felonies(0)
                .inState("california")
                .fromState("california")
                .gpa(3.7)
                .maxGpa(4)
                .satScore(1940)
                .actScore(29)
                .build();

        ResponseDTO response = ResponseDTO.builder()
                .reviewStatus(Status.ACCEPT.getMessage())
                .build();

        ResponseDTO actualResponse = applicationServiceImpl.classifyApplication(applicationDTO);
        Assertions.assertEquals(actualResponse.getReviewStatus(),response.getReviewStatus());

    }

    @Test
    void validateApplication_Reject_Test_ForName() {
        ApplicationDTO applicationDTO = ApplicationDTO.builder()
                .firstName("Michael")
                .lastName("GraysOn")
                .age(22)
                .felonies(0)
                .inState("california")
                .fromState("california")
                .gpa(3.7)
                .maxGpa(4)
                .satScore(1940)
                .actScore(29)
                .build();

        ResponseDTO response = ResponseDTO.builder()
                .reviewStatus(Status.REJECT.getMessage())
                .message("Invalid First/Last name")
                .build();

        ResponseDTO actualResponse = applicationServiceImpl.classifyApplication(applicationDTO);
        Assertions.assertEquals(actualResponse.getMessage(),response.getMessage());

    }

    @Test
    void validateApplication_Further_Test() {
        ApplicationDTO applicationDTO = ApplicationDTO.builder()
                .firstName("Michael")
                .lastName("Grayson")
                .age(40)
                .felonies(0)
                .inState("california")
                .fromState("california")
                .gpa(3.7)
                .maxGpa(4)
                .satScore(1940)
                .actScore(29)
                .build();

        ResponseDTO response = ResponseDTO.builder()
                .reviewStatus(Status.REVIEW.getMessage())
                .build();

        ResponseDTO actualResponse = applicationServiceImpl.classifyApplication(applicationDTO);
        Assertions.assertEquals(actualResponse.getMessage(),response.getMessage());

    }
}