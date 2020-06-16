package com.college.application.enums;

public enum Status {
        ACCEPT("Instant accept"), REJECT("Instant reject"), REVIEW("Further review");

        private String message;

        Status(String message){
            this.message = message;
        }

        public String getMessage(){
            return message;
        }

}
