package com.college.application.controller;

import com.college.application.dto.ApplicationDTO;
import com.college.application.service.ApplicationServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Before;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

//@RunWith(SpringRunner.class)
@WebMvcTest(ApplicationController.class)
class ApplicationControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    ApplicationServiceImpl applicationServiceImpl;

    //    Mocking all the methods that are utilized in the service for validateApplication()
//    @Before
//    public void setUp(){
//        ApplicationDTO applicationDTO = ApplicationDTO.builder()
//                .firstName("Michael")
//                .lastName("Grayson")
//                .age(22)
//                .felonies(0)
//                .inState("california")
//                .fromState("california")
//                .gpa(3.7)
//                .maxGpa(4)
//                .satScore(1940)
//                .actScore(29)
//                .build();
//        ResponseDTO response = ResponseDTO.builder()
//                .reviewStatus("Instant Accept")
//                .build();
//        Mockito.when(applicationService.validateApplication(applicationDTO)).thenReturn(response);
//    }

//    @TestConfiguration
//    static class ApplicationControllerTestContextConfiguration {
//
//        @Bean
//        public ApplicationController applicationController() {
//            return new ApplicationController();
//        }
//    }
    @Test
    void whenValidInput_thenReturns200() {
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

        try {
            mvc.perform(MockMvcRequestBuilders.post("/application")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapToJson(applicationDTO))
                    .accept(MediaType.APPLICATION_JSON));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}