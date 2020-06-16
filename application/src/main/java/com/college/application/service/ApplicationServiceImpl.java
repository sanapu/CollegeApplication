package com.college.application.service;

import com.college.application.dto.ApplicationDTO;
import com.college.application.dto.ResponseDTO;
import com.college.application.enums.Status;
import com.college.application.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;
import java.awt.event.ActionListener;

@Service
public class ApplicationServiceImpl implements ApplicationService{

    private static final int REJECT_PERCENT = 70;
    private static final int MIN_SAT_SCORE = 1920;
    private static final int MIN_ACT_SCORE = 27;
    private static final int MIN_AGE = 17;
    private static final int MAX_AGE = 26;
    private static final int OLD_AGE = 60;

    /**
     *
     * @param applicationDTO
     * @return
     */
    public ResponseDTO apply(ApplicationDTO applicationDTO){
        return classifyApplication(applicationDTO);
    }

    @Override
    public ResponseDTO classifyApplication(ApplicationDTO applicationDTO){
        ResponseDTO responseDTO = new ResponseDTO();

        if(isReject(applicationDTO,responseDTO)){
            responseDTO.setReviewStatus(Status.REJECT.getMessage());
            return responseDTO;
        }

        if(isAccept(applicationDTO)){
            responseDTO.setReviewStatus(Status.ACCEPT.getMessage());
            return responseDTO;
        }else{
            // Further review
            responseDTO.setReviewStatus(Status.REVIEW.getMessage());
            return responseDTO;
        }
    }

    /*
    To qualify as instant accept, all of the following criteria must be met.
        o In-state (California) age 17 or older, and younger than 26; or older than 80 from any
        state.
        o High School GPA of 90% or higher of scale provided in their application. For example,
        3.6 on a 4.0 scale; or 4.5 on a 5.0 scale.
        o SAT score greater than 1920 or ACT score greater than 27. Note: Both, or only one of
        these, may be present in the applicant.
        o No “instant reject” criteria is hit (see below).
    */
    protected boolean isAccept(ApplicationDTO applicationDTO){

        return (isValidAgeAccept(applicationDTO.getAge(),applicationDTO.getInState(),applicationDTO.getFromState())
                && isValidScore(applicationDTO.getSatScore(),applicationDTO.getActScore())
                && isValidGPA(applicationDTO.getGpa(),applicationDTO.getMaxGpa()));
    }

    /*
    All applicants can be subject to instant reject, if they meet any of the following criteria. Some of
    the criteria is dubious, admittedly, but the Dean insisted on it.
        o 1 or more felonies over the past 5 years.
        o High School GPA below 70% of scale provided on application. For example, 2.8 on a 4.0
        scale.
        o The applicant claimed to be a negative age (it happens!) e.g. “-20” years old.
        o The applicant’s first and/or last name are not in the form of first letter capitalized, the
        rest lower case.
     */
    protected boolean isReject(ApplicationDTO applicationDTO,ResponseDTO responseDTO){

        if(applicationDTO.getFelonies() > 0){
            responseDTO.setMessage("Felony present");
            return true;
        }
        if(applicationDTO.getAge()<=0){
            responseDTO.setMessage("Invalid age");
            return true;
        }

        if(!isValidName(applicationDTO.getFirstName(),applicationDTO.getLastName())){
            responseDTO.setMessage("Invalid First/Last name");
            return true;
        }

        if(!isValidGPA(applicationDTO.getGpa(),applicationDTO.getMaxGpa())){
            responseDTO.setMessage("Invalid GPA");
            return true;
        }
        return false;
    }

    /**
     *
     * @param age
     * @param inState
     * @param fromState
     * @return
     */
    private boolean isValidAgeAccept(int age,String inState, String fromState){
        if(inState.equalsIgnoreCase(fromState)){
            if((age >= MIN_AGE && age < MAX_AGE)){
                return true;
            }
        }
        if(age>OLD_AGE){
            return true;
        }

        return false;
    }

    private int calculateGPAPercentage(double gpa, double maxGpa){
        return (int) ((gpa/maxGpa)*100);
    }

    /**
     *
     * @param gpa
     * @param maxGpa
     * @return
     */
    private boolean isValidGPA(double gpa, double maxGpa){
        int percentage = calculateGPAPercentage(gpa,maxGpa);
        if(percentage<REJECT_PERCENT){
            return false;
        }
        return true;
    }

    /**
     *
     * @param satScore
     * @param actScore
     * @return
     */
    private boolean isValidScore(int satScore,int actScore){
        if((satScore !=0 && satScore > MIN_SAT_SCORE) || (actScore!=0 && actScore>MIN_ACT_SCORE)){
            return true;
        }
        return false;
    }

    /**
     *
     * @param fName
     * @param lName
     * @return
     */
    private boolean isValidName(String fName, String lName){
        if((fName == null || fName.isEmpty()) && (lName == null || lName.isEmpty())){
            return false;
        }else if(StringUtils.isCamelCase(fName) && StringUtils.isCamelCase(lName)){
            return true;
        }
        return false;
    }

}
