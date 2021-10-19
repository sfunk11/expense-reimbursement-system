package com.revature.project1.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.project1.dao.ReimbursementDaoImpl;
import com.revature.project1.dao.UserDaoImpl;
import com.revature.project1.models.ReimbursementItem;
import com.revature.project1.models.User;
import com.revature.project1.services.ReimbursementService;
import com.revature.project1.services.UserService;

public class ServiceTests {

	@Mock
	private UserDaoImpl uDao;
	@Mock
	private ReimbursementDaoImpl rDao;
	
	private UserService uServ;
	private ReimbursementService rServ;
	

	private User testUser = new User(3, "mthompson","5f4dcc3b5aa765d61d8327deb882cf99", "Michael", "Thompson", "mthompson@email.com", 1, "Employee");
	private User testUser1 = new User(3, "mthompson","7c6a180b36896a0a8c02787eeafb0e4c", "Michael", "Thompson", "mthompson@email.com", 1, "Employee");
	private User testManager = new User(1, "sfunk", "5f4dcc3b5aa765d61d8327deb882cf99", "Sam", "Funk", "sfunk@email.com", 2, "Manager");
	private ReimbursementItem item1 = new ReimbursementItem(1,360.50,null, null, "Hotel Stay", 3,0, 1, 2, "Pending" , "TRAVEL", "mthompson");
	private ReimbursementItem item2 = new ReimbursementItem(2,420.00,null, null, "Rental Car", 3,0, 1, 2, "Pending" , "TRAVEL", "mthompson");
	private ReimbursementItem item3 = new ReimbursementItem(3,50.00,null, null, "Dinner with client", 3,0, 1, 3, "Pending" , "FOOD", "mthompson");
	private List<User> userList = new ArrayList<>();
	private List<ReimbursementItem> itemList = new ArrayList<>();
	
	
	@BeforeEach
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		uServ = new UserService(uDao);
		rServ = new ReimbursementService(rDao);
		userList.add(testManager);
		userList.add(testUser);
		itemList.add(item1);
		itemList.add(item2);
		itemList.add(item3);
		when(uDao.getById(3)).thenReturn(testUser);
		when(uDao.getById(1)).thenReturn(testManager);
		when(uDao.getByName("mthompson")).thenReturn(testUser);
		when(uDao.getByName("sfunk")).thenReturn(testManager);
		when(uDao.getAll()).thenReturn(userList);
		when(rDao.getById(1)).thenReturn(item1);
		when(rDao.getById(2)).thenReturn(item2);
		when(rDao.getById(3)).thenReturn(item3);
		when(rDao.getByUsername("mthompson")).thenReturn(itemList);
		when(rDao.getAll()).thenReturn(itemList);
		
		
		

	}
	
	@Test
	public void loginUserTest() {
		User user = uServ.verifyUserCreds("mthompson", "password");
		Assert.assertNotNull(user);
	}
	
	@Test
	public void loginFailTest() {
		User user = uServ.verifyUserCreds("mthompson", "somethingelse");
		Assert.assertNull(user);
	}
	
	@Test
	public void changePasswordTest() {
		User user = uServ.changePassword("sfunk", "Sam Funk", "password1");
		assertEquals("7c6a180b36896a0a8c02787eeafb0e4c", user.getPassword());
	}
	
	@Test
	public void getEmployeeListTest() {
		List<User> employeeList = uServ.getAllEmployees();
		assertEquals("sfunk", employeeList.get(0).getUsername());
	}
	
	@Test
	public void getEmployeebyIdTest() {
		User user =uServ.getEmployeeById(3);
		assertEquals("mthompson@email.com", user.getEmail());
	}
	
	@Test
	public void getAllReimbTest() {
		List<ReimbursementItem> rlist = rDao.getAll();
		assertEquals("Dinner with client", rlist.get(2).getDescription());
	}
	
	@Test
	public void getReimbursementByUserTest() {
		List<ReimbursementItem> rList = rDao.getByUsername("mthompson");
		assertEquals("Hotel Stay", rList.get(0).getDescription());
	}
	
	@Test
	public void submitItemTest(){
		List<ReimbursementItem> list = rServ.submitItem(item3, testUser);
		assertEquals("Hotel Stay", list.get(0).getDescription());
	}
	
	@Test
	public void getReimbByIdTest() {
		ReimbursementItem item = rServ.getReimbById(2);
		assertEquals("Rental Car", item.getDescription());
		
	}
	
}

