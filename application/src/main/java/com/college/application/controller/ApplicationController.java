package com.college.application.controller;

import com.college.application.Application;
import com.college.application.dto.ApplicationDTO;
import com.college.application.dto.ResponseDTO;
import com.college.application.service.ApplicationService;
import com.college.application.service.ApplicationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/application")
@Slf4j
public class ApplicationController {

    @Autowired
    ApplicationServiceImpl applicationServiceImpl;

    /**
     * post an application using this api
     * @param applicationDTO
     * @return
     * @throws ApplicationException
     */
    @PostMapping("/")
    @RequestMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> apply(@RequestBody ApplicationDTO applicationDTO) throws ApplicationException {
        try{
            ResponseDTO response = applicationServiceImpl.apply(applicationDTO);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch(Exception exc){
            throw new ApplicationException("Invalid Form data");
        }
    }

    /**
     * health check
     * @return
     */
    @GetMapping("/healthcheck")
    public String getSampleResponse(){
        return "Got the heartbeat";
    }

    /**
     *
     */
    private class ApplicationException extends Exception {
        String customMessage;
        ApplicationException(String customMessage){
            this.customMessage = customMessage;
        }
    }
}
