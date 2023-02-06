package APIPackage;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
//to change order of execution we use fix method order since it is executing from top to the bottom
//which is not good for us
//this method sorters will execute my methods in ascending/alphabetically order
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {
    //one thing to remember
    //base uri-base url
    //end then using when keyword, we will send the end point
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";

    //we need to perform crud operations
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
            "eyJpYXQiOjE2NzQyMDYxNzYsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY3NDI0OTM3NiwidXNlcklkIjoiNTA0OSJ9." +
            "5Vo1yH4z8Lzgu6wBb9O2TPIDMjzwwZvz_qbFmkdia9s";
    static String employee_id;

    @Test
    public void bgetOneEmployee() {
        //prepare the request
        //to prepare the request,we use request specification
        RequestSpecification request = given().header("Authorization", token)
                .header("Content-Type", "application/json").queryParam("employee_id", employee_id);
        //to hit the end point/to make the request which will return response
        Response response = request.when().get("/getOneEmployee.php");
        // System.out.println(response.asString());
        response.prettyPrint();
        //verifying the status code
        response.then().assertThat().statusCode(200);
        //using jsonpath method, we are extracting the value from the response body
        String firstName = response.jsonPath().getString("employee.emp_firstname");
        System.out.println(firstName);
        //first way of assertion
        Assert.assertEquals(firstName,"Samir");
        //second way of assertion to verify the value in response body using hamcrest.Matchers
        response.then().assertThat().body("employee.emp_firstname",equalTo("Samir"));

    }
    @Test
    public void acreateEmployee(){
        RequestSpecification request=given().header("Authorization", token)
                .header("Content-Type", "application/json").body("{\n" +
                        "  \"emp_firstname\": \"Samir\",\n" +
                        "  \"emp_lastname\": \"Alfonso\",\n" +
                        "  \"emp_middle_name\": \"ms\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2010-01-14\",\n" +
                        "  \"emp_status\": \"confirmed\",\n" +
                        "  \"emp_job_title\": \"HumanLover\"\n" +
                        "}");
        Response response = request.post("/createEmployee.php");
        response.prettyPrint();
        //verifying the status code which is 201
        response.then().assertThat().statusCode(201);
        //getting the employee id from the response and use it as static one
        employee_id=response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);
        response.then().assertThat().body("Employee.emp_lastname",equalTo("Alfonso"));
        response.then().assertThat().body("Employee.emp_middle_name",equalTo("ms"));
        //verify console header
        response.then().assertThat().header("Server","Apache/2.4.39 (Win64) PHP/7.2.18");
    }
@Test
public void cupdateEmployee(){
    RequestSpecification request=given().header("Authorization", token)
            .header("Content-Type", "application/json").body("{\n" +
                    "  \"employee_id\": \""+employee_id+"\",\n" +
                    "  \"emp_firstname\": \"ramzi\",\n" +
                    "  \"emp_lastname\": \"Nassima\",\n" +
                    "  \"emp_middle_name\": \"ms\",\n" +
                    "  \"emp_gender\": \"F\",\n" +
                    "  \"emp_birthday\": \"2005-08-20\",\n" +
                    "  \"emp_status\": \"probation\",\n" +
                    "  \"emp_job_title\": \"Superviser\"\n" +
                    "}");
    Response response = request.when().put("/updateEmployee.php");
    response.prettyPrint();
    //verification
    response.then().assertThat().statusCode(200);
    response.then().assertThat().body("Message",equalTo("Employee record Updated"));
}
@Test
public void dgetUpdateEmployee(){
    RequestSpecification request = given().header("Authorization", token)
            .header("Content-Type", "application/json")
            .queryParam("employee_id", employee_id);

    //to hit the end point/ to make the request which will return response
    Response response = request.when().get("/getOneEmployee.php");

    // System.out.println(response.asString());
    response.prettyPrint();
    //verifying the status code
    response.then().assertThat().statusCode(200);
    response.then().assertThat().body("employee.emp_job_title", equalTo("Superviser"));
}
}
