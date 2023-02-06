package utils;

import APISteps.APIWorkFlowSteps;
import com.google.gson.JsonObject;
import org.json.JSONObject;

public class APIPayloadConstant {
    public static String createEmployeePayload(){
        String createEmployeePayload="{\n" +
                "  \"emp_firstname\": \"Samir\",\n" +
                "  \"emp_lastname\": \"Alfonso\",\n" +
                "  \"emp_middle_name\": \"ms\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"2010-01-14\",\n" +
                "  \"emp_status\": \"confirmed\",\n" +
                "  \"emp_job_title\": \"HumanLover\"\n" +
                "}";
        return createEmployeePayload;
    }

    public static String createEmployeeJsonBody(){
        JSONObject obj= new JSONObject();
        obj.put("emp_firstname","Samir");
        obj.put("emp_lastname","Alfonso");
        obj.put("emp_middle_name","ms");
        obj.put("emp_gender","M");
        obj.put("emp_birthday","2010-01-14");
        obj.put("emp_status","confirmed");
        obj.put("emp_job_title","HumanLover");
        return obj.toString();
    }
    public static String createEmployeePayloadDynamic(String firstname, String lastname, String middlename,
                                                      String gender, String dob,
                                                      String empStatus, String jobTitle){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", firstname);
        obj.put("emp_lastname", lastname);
        obj.put("emp_middle_name", middlename);
        obj.put("emp_gender", gender);
        obj.put("emp_birthday", dob);
        obj.put("emp_status", empStatus);
        obj.put("emp_job_title",jobTitle);
        return obj.toString();

    }

    public static String updateEmployeePayLoadDaynamic(String empId,String firstname, String lastname, String middlename,
                                                       String gender, String dob,
                                                       String empStatus, String jobTitle){
        JSONObject obj = new JSONObject();
        obj.put("employee_id", APIWorkFlowSteps.employee_id);
        obj.put("emp_firstname", firstname);
        obj.put("emp_lastname", lastname);
        obj.put("emp_middle_name", middlename);
        obj.put("emp_gender", gender);
        obj.put("emp_birthday", dob);
        obj.put("emp_status", empStatus);
        obj.put("emp_job_title",jobTitle);
        return obj.toString();

    }

    public static String adminPayLoad(){
        String adminPayload="{\n" +
                "  \"email\": \"Mohnon@gmail.com\",\n" +
                "  \"password\": \"Aboukal\"\n" +
                "}";
        return adminPayload;
    }

}
