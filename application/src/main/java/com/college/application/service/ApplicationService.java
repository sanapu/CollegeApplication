package com.college.application.service;

import com.college.application.dto.ApplicationDTO;
import com.college.application.dto.ResponseDTO;

public interface ApplicationService {
    /**
     *
     * @param applicationDTO
     * @return
     */
    ResponseDTO classifyApplication(ApplicationDTO applicationDTO);
}
