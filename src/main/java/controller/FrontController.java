package controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptioin.ContactCreationFailedException;
import exceptioin.ContactDelitionFailedException;
import model.entity.Address;
import model.entity.Contact;
import model.entity.Person;
import model.entity.Phone;
import model.service.ContactService;

@SuppressWarnings("serial")
public class FrontController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>Hello Servlet</h1>");
        response.getWriter().println("session=" + request.getSession(true).getId());
        ContactService service = new ContactService();
//        Contact c = new Contact();
//        Person p = new Person();
//        p.setIdPerson(2);
//        p.setFirstName("Hello");
//        p.setSecondName("Agi");
//        p.setDateOfBirth(new Date(2000, 12, 25));
//        Address a = new Address();
//        a.setCountry("USA");
//        a.setCity("LA");
//        a.setStreet("SantaMonica");
//        c.setPerson(p);
//        c.setAddress(a);
//        Phone p1 = new Phone();
//        p1.setOperatorCode(12);
//        p1.setCountryCode(2);
//        p1.setPhoneType("дом");
//        c.addPhone(p1);
//        try {
//        	service.createContact(c);
//        	service.deleteContact(9);
//        } catch (ContactDelitionFailedException | ContactCreationFailedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
