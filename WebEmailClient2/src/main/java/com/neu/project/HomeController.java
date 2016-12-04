package com.neu.project;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import antlr.collections.List;

import com.neu.project.dao.HibernateUtil;
import com.neu.project.dao.UserDAO;
import com.neu.project.model.EmailAccount;
import com.neu.project.model.Messages;
import com.neu.project.model.User;
import com.neu.project.model.UserFile;
import com.neu.project.server.SendMail;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	@Qualifier("userValidator")
	private Validator validator;

	@Autowired
	private UserDAO userDao;

	/*
	 * This is to initialize webDataBinder,set its validator as we specify.
	 */
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "homePage";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String dashboard(Model model, User user, BindingResult result,
			HttpServletRequest request) {
		String returnVal = "home";
		if (result.hasErrors()) {
			return "homePage";
		} else {
			try {
				User u = userDao.queryUserByNameAndPassword(user.getUserName(),
						user.getPassword());
				if (u != null) {
					HttpSession session = request.getSession();
					session.setAttribute("user", u);
					// model.addAttribute("user", u);
					if (user.getUserName().equalsIgnoreCase("admin")) {
						return "adminPage";
					} else {
						return returnVal;
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// return "loginError";
			}
			// return "home";
			return "loginErrorPage";
		}
	}

	@RequestMapping(value = "/userSignup", method = RequestMethod.POST)
	public String userSignup(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false) String q, Model model) {
		String returnVal = "home";

		try {
			User user = new User();
			String userName = request.getParameter("userName");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String password = request.getParameter("password");
			String month = request.getParameter("month");
			String day = request.getParameter("day");
			String year = request.getParameter("year");

			Date birthDate = new Date();
			birthDate.setYear(Integer.parseInt(year) - 1900);
			System.out.println(year + "-" + month + "-" + day);
			birthDate.setMonth(Integer.parseInt(month) - 1);
			birthDate.setDate(Integer.parseInt(day));

			String emailPassword = request.getParameter("emailPassword");
			String emailAccount = request.getParameter("emailAccount");
			String gender = request.getParameter("gender");

			user.setUserName(userName);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.getPassword();
			user.setGender(gender);
			user.setUser_Status("");
			user.setBirthDate(birthDate);
			userDao.saveUser(user);
			EmailAccount account = new EmailAccount();
			account.setEmailAccount(emailAccount);
			account.setEmailPassword(emailPassword);
			account.setUser(user);
			userDao.saveEmailAccount(account);

			HttpSession session = request.getSession();
			session.setAttribute("user", user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// return "loginError";
		}
		// return "home";
		return returnVal;

	}

	@RequestMapping(value = "/inbox", method = RequestMethod.GET)
	public String getInbox(Locale locale, Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		ArrayList<Messages> messageList = userDao.getMessages(user.getUserID(),
				"INBOX");
		System.out.println("Messages for userId: " + user.getUserID()
				+ " count:" + messageList.size());
		model.addAttribute("messageList", messageList);
		return "inbox";
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String getDashboard(Locale locale, Model model) {

		return "dashboard";
	}

	@RequestMapping(value = "/SignupPage", method = RequestMethod.GET)
	public String getSignUpPage(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "SignupPage";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String getTestPage(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "test";
	}

	@RequestMapping(value = "/drafts", method = RequestMethod.GET)
	public String getDrafts(Locale locale, Model model,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		ArrayList<Messages> messageList = userDao.getMessages(user.getUserID(),
				"DRAFTS");
		System.out.println("Messages for userId: " + user.getUserID()
				+ " count:" + messageList.size());
		model.addAttribute("messageList", messageList);
		return "drafts";
	}

	@RequestMapping(value = "/starred", method = RequestMethod.GET)
	public String getStarred(Locale locale, Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		ArrayList<Messages> messageList = userDao.getMessages(user.getUserID(),
				"STARRED");
		System.out.println("Messages for userId: " + user.getUserID()
				+ " count:" + messageList.size());
		model.addAttribute("messageList", messageList);

		return "starred";
	}

	@RequestMapping(value = "/spam", method = RequestMethod.GET)
	public String getSpam(Locale locale, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		ArrayList<Messages> messageList = userDao.getMessages(user.getUserID(),
				"SPAM");
		System.out.println("Messages for userId: " + user.getUserID()
				+ " count:" + messageList.size());
		model.addAttribute("messageList", messageList);

		return "spam";
	}

	@RequestMapping(value = "/trash", method = RequestMethod.GET)
	public String getTrash(Locale locale, Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		ArrayList<Messages> messageList = userDao.getMessages(user.getUserID(),
				"TRASH");
		System.out.println("Messages for userId: " + user.getUserID()
				+ " count:" + messageList.size());
		model.addAttribute("messageList", messageList);

		return "trash";
	}

	@RequestMapping(value = "/outbox", method = RequestMethod.GET)
	public String getOutbox(Locale locale, Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		ArrayList<Messages> messageList = userDao.getMessages(user.getUserID(),
				"STARRED");
		System.out.println("Messages for userId: " + user.getUserID()
				+ " count:" + messageList.size());
		model.addAttribute("messageList", messageList);

		return "outbox";
	}

	@RequestMapping(value = "/sendMessage", method = RequestMethod.GET)
	public void sendMail(Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String receiver = request.getParameter("receiver");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		Messages message = new Messages();
		message.setContent(content);
		message.setDate(new Date());
		ArrayList<EmailAccount> emailAccountsList = new ArrayList(
				Arrays.asList(user.getEmailAccounts().toArray()));
		// message.setSender(((ArrayList<EmailAccount>)user.getEmailAccounts()).get(0).getEmailAccount());
		message.setSender(emailAccountsList.get(0).getEmailAccount());
		message.setReceiver(receiver);
		message.setEmail_Id(emailAccountsList.get(0).getEmail_Id());
		message.setEmail_status("SENT");
		message.setSubject(subject);
		userDao.saveSentEmail(message);
		SendMail.sendEMail(message, user);

	}

	@RequestMapping(value = "/deleteMessage", method = RequestMethod.GET)
	public void deleteMessage(Locale locale, Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int id = Integer.parseInt(request.getParameter("id"));
		userDao.deleteMessage(id);

	}

	@RequestMapping(value = "/starredMessage", method = RequestMethod.GET)
	public void starredMessage(Locale locale, Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int id = Integer.parseInt(request.getParameter("id"));
		userDao.starredMessage(id);

	}

	@RequestMapping(value = "/myProfile", method = RequestMethod.GET)
	public void myProfile(Locale locale, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);

	}

	@RequestMapping(value="/logout")
	public String showLogout(Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
		return "logout";
		
	}
}
