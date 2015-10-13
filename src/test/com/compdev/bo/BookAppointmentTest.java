package test.com.compdev.bo;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.compdev.bo.BookAppointment;
import com.compdev.exceptions.LAMSException;
import com.compdev.vo.Appointment;
import com.compdev.vo.Patient;

import components.data.DB;
import components.data.IComponentsData;
import components.data.Physician;

import java.util.HashMap;
import java.util.List;


/**
 * 
 * @author Neal Willbur, Anthony Perez
 *
 */
public class BookAppointmentTest {

	@BeforeClass
	public static void setUpClass() throws Exception {
		//Initialize the Cellular Won Laboratories Database
		IComponentsData testDB = new DB();
		testDB.initialLoad("LAMS");
	}
	
	/* Test Case 1 - Test the Pre-Conditions*/
	/* Step 1 - Verify User Exists */
	/* Expected Result: Exception - User does not exist */
	/**
     * Tests the makeAppointment method when the patient does not exist in the system
     * Should throw a LAMSException
     * @throws java.lang.Exception
     **/
    @Test (expected=LAMSException.class)
    public void testPatientExists() throws Exception {        
        BookAppointment bookApp = new BookAppointment();
        
        Appointment t_app = new Appointment();
        t_app.setAppointmentDate("2016-10-15");
        t_app.setAppointmentTime("10:00:00");
        t_app.setAppointmentNumber("1000");
        t_app.setPatientId("1"); 
        t_app.setPatientServiceCenterCode("500");
        t_app.setPhlebotomistId("100");
        t_app.setPhysicianId("10");
        
        HashMap<String, String> testDetails = new HashMap<String, String>();
        testDetails.put("82088","290.0");
        t_app.setTestDetails(testDetails);
        
        bookApp.makeAppointment(t_app);
    }
	
    /* Step 2 - Verify Patient Physician Exists */
    /* Expected Result: LAMSException - Physician does not exist */
    /**
     * Tests the makeAppointment method when the Patient Physician does not exist in the system
     * Should throw a LAMSException
     * @throws Exception
     */
    @Test (expected=LAMSException.class)
    public void testPhysicianExists() throws Exception {
    	BookAppointment bookApp = new BookAppointment();
    	
    	Appointment t_app = new Appointment();
    	t_app.setAppointmentDate("2016-10-15");
    	t_app.setAppointmentTime("10:00:00");
    	t_app.setAppointmentNumber("1000");
    	t_app.setPatientId("210");
    	t_app.setPatientServiceCenterCode("500");
    	t_app.setPhlebotomistId("100");
    	t_app.setPhysicianId("1");
    	
    	HashMap<String, String> testDetails = new HashMap<String, String>();
    	testDetails.put("82088","290.0");
        t_app.setTestDetails(testDetails);
        
        bookApp.makeAppointment(t_app);
    }
    
    /* Step 3 - Verify Ordered Lab Test Exists and is Valid */
    /* Expected Result: LAMSException - Lab Test is not valid */
    /**
     * Tests the makeAppointment method when the test DSM code and id are invalid
     * Should throw a LAMSException
     * @throws java.lang.Exception
     **/
    @Test (expected=LAMSException.class)
    public void testDSMValidity() throws Exception {        
        BookAppointment bookApp = new BookAppointment();
        
        Appointment app;
        app = new Appointment();
        app.setAppointmentDate("2016-10-15");
        app.setAppointmentTime("10:00:00");
        app.setAppointmentNumber("1000");
        app.setPatientId("210"); 
        app.setPatientServiceCenterCode("500");
        app.setPhlebotomistId("100");
        app.setPhysicianId("10");
        
        HashMap<String, String> testDetails = new HashMap<String, String>();
        //Invalid test id. Can only be 5 characters
        testDetails.put("111111","1");
        app.setTestDetails(testDetails);
        
        bookApp.makeAppointment(app);
    }
    
    /* Expected Result: LAMSException - Lab Test doesn't exist in system */
    /**
     * Tests the makeAppointment method when the DSM code/id don't exist in the system
     * Should throw a LAMSException
     * @throws Exception
     */
    @Test (expected=LAMSException.class)
    public void testDSMExists() throws Exception {        
        BookAppointment bookApp = new BookAppointment();
        
        Appointment app;
        app = new Appointment();
        app.setAppointmentDate("2016-10-15");
        app.setAppointmentTime("10:00:00");
        app.setAppointmentNumber("1000");
        app.setPatientId("210"); 
        app.setPatientServiceCenterCode("500");
        app.setPhlebotomistId("100");
        app.setPhysicianId("10");
        
        HashMap<String, String> testDetails = new HashMap<String, String>();
        //Invalid test id. Can only be 5 characters
        testDetails.put("11111","111.1");
        app.setTestDetails(testDetails);
        
        bookApp.makeAppointment(app);
    }
    
    /* Step 4 - Verify Phlebotomist Exists */
    /* Expected result: LAMSException - Phlebotomist doesn't exist in system */
    /**
     * Tests the makeAppointment method when the Phlebotomist doesn't exist
     * Should throw a LAMSException
     * @throws java.lang.Exception
     */
    @Test (expected=LAMSException.class)
    public void testPhlebotomistExists() throws Exception {
        BookAppointment bookApp = new BookAppointment();
        
        Appointment app;
        app = new Appointment();
        app.setAppointmentDate("2016-10-15");
        app.setAppointmentTime("10:00:00");
        app.setAppointmentNumber("1000");
        app.setPatientId("210"); 
        app.setPatientServiceCenterCode("500");
        app.setPhlebotomistId("1");
        app.setPhysicianId("10");
        
        HashMap testDetails = new HashMap();
        testDetails.put("82088","290.0");
        app.setTestDetails(testDetails);
        
        bookApp.makeAppointment(app);
    }
    
    /* Test Case 2 - Test other inputs */
    /* Step 1 - Verify date format */
    /**
     * Tests the makeAppointment method when the date is formatted incorrectly
     * Should throw a LAMSException
     * @throws Exception
     */
    @Test (expected=LAMSException.class)
	public void testDateValidity() throws Exception {
		BookAppointment bookApp = new BookAppointment();
		
		//Create an appointment to test Use Case 1, uses known valid data
		Appointment t_app = new Appointment();
		t_app.setAppointmentDate("2016-10-1");
		t_app.setAppointmentTime("10:00:00");
		t_app.setAppointmentNumber("1000");
		t_app.setPatientId("210");
		t_app.setPatientServiceCenterCode("500");
		t_app.setPhlebotomistId("100");
		t_app.setPhysicianId("10");
		HashMap testDetails = new HashMap();
		testDetails.put("82088", "290.0");
		t_app.setTestDetails(testDetails);
			
		bookApp.makeAppointment(t_app);
	}       
    
    /* Step 2 - Verify PSC exists in the system */
    /**
     * Tests the makeAppointment method when the PSC doesn't exist
     * Should throw a LAMSException
     * @throws java.lang.exception
     */
    @Test (expected=Exception.class)
    public void testPSCExists() throws Exception {
        
        BookAppointment bookApp = new BookAppointment();
        
        Appointment app;
        app = new Appointment();
        app.setAppointmentDate("2016-10-15");
        app.setAppointmentTime("10:00:00");
        app.setAppointmentNumber("1000");
        app.setPatientId("210"); 
        app.setPatientServiceCenterCode("1");
        app.setPhlebotomistId("100");
        app.setPhysicianId("10");
        
        HashMap testDetails = new HashMap();
        testDetails.put("82088","290.0");
        app.setTestDetails(testDetails);
        
        bookApp.makeAppointment(app);
    }
    
    /* Step 3 - Test getting Patient ID and Info from Name & DoB */
    /**
     * Tests the getPatientInfoByDetails method when you have Patient name and DoB
     */
    @Test
    public void testGetPatientInfoByDetails() throws Exception {
       BookAppointment bookApp = new BookAppointment();
       Patient t_patient = new Patient();
       t_patient.setName("Tom Thumb");
       t_patient.setDob("1959-09-22");
       assertEquals("210", bookApp.getPatientInfoByDetails(t_patient).getPatientId());
       assertTrue(bookApp.getPatientInfoByDetails(t_patient).isInsurance());
       assertEquals("10", bookApp.getPatientInfoByDetails(t_patient).getPhysicianId());
    }
    
    /* Test Case 3 - Valid Appointment */
	/**
	 * Tests the makeAppointment method when all inputs are valid
	 */
	@Test
	public void testMakeAppointment() throws Exception {
		BookAppointment bookApp = new BookAppointment();
		
		//Create an appointment to test Use Case 1, uses known valid data
		Appointment t_app;
		t_app = new Appointment();
		t_app.setAppointmentDate("2016-10-15");
		t_app.setAppointmentTime("10:00:00");
		t_app.setAppointmentNumber("1000");
		t_app.setPatientId("210");
		t_app.setPatientServiceCenterCode("500");
		t_app.setPhlebotomistId("100");
		t_app.setPhysicianId("10");
		HashMap<String, String> testDetails = new HashMap<String, String>();
		testDetails.put("82088", "290.0");
		t_app.setTestDetails(testDetails);
		
		//Test that the appointment returns a number
		assertTrue(!bookApp.makeAppointment(t_app).equals(""));
	}
	
	/* Test Case 4 - Unavailable Appointment */
	/**
	 * Tests the makeAppointment method when appointment is not available
	 * Should throw a LAMSException
	 * @throws Exception
	 */
	@Test (expected=LAMSException.class)
	public void testUnavailableAppointment() throws Exception {
		BookAppointment bookApp = new BookAppointment();
		
		Appointment t_app = new Appointment();
		t_app.setAppointmentDate("2016-10-15");
		t_app.setAppointmentTime("10:10:00");
		t_app.setAppointmentNumber("1001");
		t_app.setPatientId("210");
		t_app.setPatientServiceCenterCode("500");
		t_app.setPhlebotomistId("100");
		t_app.setPhysicianId("10");
		HashMap<String, String> testDetails = new HashMap<String, String>();
		testDetails.put("82088", "290.0");
		t_app.setTestDetails(testDetails);
		
		bookApp.makeAppointment(t_app);
	}
    
	/* Test Case 5 - Test Assumptions */
    /**
     * Tests the getNextAppointment method confirming the assumptions of phlebotomists needing 
     * 30 minutes between appointments if changing PSCs
     */
    @Test
    public void testGetNextAppointment() throws Exception {
        BookAppointment bookApp = new BookAppointment();
    	
    	Appointment t_app = new Appointment();
    	t_app.setAppointmentDate("2016-10-15");
    	t_app.setAppointmentTime("11:00:00");
    	t_app.setAppointmentNumber("1002");
    	t_app.setPatientId("210");
    	t_app.setPatientServiceCenterCode("500");
    	t_app.setPhlebotomistId("100");
    	t_app.setPhysicianId("10");        
        HashMap<String, String> testDetails = new HashMap<String, String>();
        testDetails.put("82088","290.0");        
        t_app.setTestDetails(testDetails);        
        
        assertEquals("1002", bookApp.getNextAppointment(t_app).getAppointmentNumber());
        assertEquals("2016-10-15", bookApp.getNextAppointment(t_app).getAppointmentDate());
        assertEquals("11:45", bookApp.getNextAppointment(t_app).getAppointmentTime());
        
        assertEquals("1002", bookApp.getNextAppointment(t_app).getAppointmentNumber());
        assertEquals("2016-10-15", bookApp.getNextAppointment(t_app).getAppointmentDate());
        assertEquals("12:30", bookApp.getNextAppointment(t_app).getAppointmentTime());
    }    
}