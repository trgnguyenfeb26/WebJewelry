package jewelry.controller;

import java.io.File;
import java.io.IOException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.catalina.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jewelry.bean.UploadFile;
import jewelry.entity.OrderEntity;
import jewelry.entity.UserEntity;
import jewelry.ultils.PasswordUltils;

@Controller

public class ProfileController {
	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext context;

	@Transactional
	@RequestMapping(value = "user/profile", method = RequestMethod.GET)
	public String profile(ModelMap model, HttpSession session) {
		UserEntity userTemp=(UserEntity) session.getAttribute("user");
		Session session2 = factory.openSession();
		UserEntity userE = (UserEntity) session2.get(UserEntity.class, userTemp.getId());
		model.addAttribute("userE", userE);
		model.addAttribute("frmCtrlLastName", "form-control form-control-lg");
		model.addAttribute("lastname", userE.getLastname());
		model.addAttribute("frmCtrlFirstName", "form-control form-control-lg");
		model.addAttribute("firstname", userE.getFirstname());
		model.addAttribute("frmCtrlEmail", "form-control form-control-lg");
		model.addAttribute("email", userE.getEmail());
		model.addAttribute("frmCtrlPhone", "form-control form-control-lg");
		model.addAttribute("phone", userE.getPhone());
		model.addAttribute("frmCtrlAdd", "form-control form-control-lg");
		model.addAttribute("address", userE.getAddress());
		model.addAttribute("frmCtrlBD", "form-control form-control-lg");
		
		model.addAttribute("birthday", userE.getBirthday());
		if (userE.getAvatar() != "") {
			model.addAttribute("avatar", userE.getAvatar());
		} else {
			if (userE.getSex() == 0) {
				model.addAttribute("avatar", "men.jpg");
			} else {
				model.addAttribute("avatar", "women.jpg");
			}

		}

		return "profile";
	}

	public Integer updateProfile(UserEntity user) {
		Session session = factory.openSession();
		org.hibernate.Transaction t = session.beginTransaction();
		try {
			session.update(user);
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
			t.rollback();
			return 0;
		} finally {
			session.close();
		}
		return 1;
	}
	
	@Autowired
	@Qualifier("uploadfile")
	UploadFile baseUploadFile;

	@RequestMapping(value = "user/profile/validate", method = RequestMethod.POST)
	public String validate(ModelMap model, @ModelAttribute("userE") UserEntity userE, BindingResult errors,
			@RequestParam("birthday") @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthday, 
			HttpServletRequest request,
			HttpSession ss, HttpServletResponse response,
			@RequestParam("lastname") String lastname, @RequestParam("firstname") String firstname,
			@RequestParam("address") String address, @RequestParam("phone") String phone, 
			@RequestParam("sex") int sex,
			@RequestParam("avatar") MultipartFile avatar) throws InterruptedException 
			{
				boolean err = false;
				UserEntity userTemp=(UserEntity) ss.getAttribute("user");
				model.addAttribute("email", userTemp.getEmail());
				model.addAttribute("avatar", userTemp.getAvatar());
				if (lastname.equals("")) {
					model.addAttribute("frmCtrlLastName", "form-control form-control-lg is-invalid");
					model.addAttribute("fbLastName", "invalid-feedback");
					model.addAttribute("contentFBLN", "Không được để trống họ");
					err = true;
				} else {
					model.addAttribute("frmCtrlLastName", "form-control form-control-lg is-valid");
					model.addAttribute("lastname", request.getParameter("lastname"));

				}
		
				model.addAttribute("frmCtrlBD", "form-control form-control-lg is-valid");
				model.addAttribute("birthday", request.getParameter("birthday"));
		
				// model.addAttribute("email", (UserEntity)ss.getAttribute("user").getEmail());

				if (address.isBlank()) {
					model.addAttribute("frmCtrlAdd", "form-control form-control-lg is-invalid");
					model.addAttribute("fbAdd", "invalid-feedback");
					model.addAttribute("contentFBAdd", "Không được để trống địa chỉ");
					err = true;
				} else {
					model.addAttribute("frmCtrlAdd", "form-control form-control-lg is-valid");
					model.addAttribute("address", request.getParameter("address"));
				}

				if (firstname.equals("")) {
					model.addAttribute("frmCtrlFirstName", "form-control form-control-lg is-invalid");
					model.addAttribute("fbFirstName", "invalid-feedback");
					model.addAttribute("contentFBFN", "Không được để trống tên");
					err = true;
		
				} else {
					model.addAttribute("frmCtrlFirstName", "form-control form-control-lg is-valid");
					model.addAttribute("firstname", request.getParameter("firstname"));
		
				}
				String regexPhone = "(84|0[3|5|7|8|9])+([0-9]{8})";
				Pattern patternPhone = Pattern.compile(regexPhone);
				if (phone.isBlank()) {
					model.addAttribute("frmCtrlPhone", "form-control form-control-lg is-invalid");
					model.addAttribute("fbPhone", "invalid-feedback");
					model.addAttribute("contentFBPhone", "Không được để trống số điện thoại");
					err = true;
				} else if (!phone.isBlank()) {
					Matcher matcher = patternPhone.matcher(userE.getPhone());
					boolean match = matcher.matches();
					if (!match) {
						model.addAttribute("frmCtrlPhone", "form-control form-control-lg is-invalid");
						model.addAttribute("fbPhone", "invalid-feedback");
						model.addAttribute("contentFBPhone", "Số điện thoại không hợp lệ");
						err = true;
					} else {
						model.addAttribute("frmCtrlPhone", "form-control form-control-lg is-valid");
						model.addAttribute("phone", request.getParameter("phone"));
		
					}
				}
				if(avatar!=null)
				{
					//addAttribute("fileAvatar",avatar);
					model.addAttribute("avatar",avatar);
				}
				Date date1 = new Date();
				String fileName="";
				if (err == false) {
	
				if(avatar.getSize()!=0)
					{
						String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss-"));
						fileName = date + avatar.getOriginalFilename();
						String photoPath = //"D:\\web_class\\workspace2\\JEWELRY6\\WebContent\\resources\\images"
						baseUploadFile.getBasePath() + File.separator 
						+ fileName;
						//String photoPath1 = context.getRealPath("/resources/images/"+avatar.getOriginalFilename());
					
						System.out.println("Pathfile is: " + photoPath);
						try {
							//avatar.transferTo(new File(photoPath));
							avatar.transferTo(new File(photoPath));
							Thread.sleep(2500);
						//	System.out.println("Pathfile is: " + photoPath1);
						} catch (IllegalStateException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
	
						userE.setAvatar(fileName);
					}

				System.out.println("Original File Name:" + avatar.getOriginalFilename());
				System.out.println("11");
				String lastname2 = request.getParameter("lastname");
				String firstname2 = request.getParameter("firstname");
				String address2 = request.getParameter("address");
				String phone2 = request.getParameter("phone");
				String birthday2 = request.getParameter("birthday");

				try {
					date1 = new SimpleDateFormat("yyyy-MM-dd").parse(birthday2);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				Session session = factory.openSession();
		
				Transaction t = session.beginTransaction();
				UserEntity user = (UserEntity) session.get(UserEntity.class, userTemp.getId());
				try {
					user.setFirstname(firstname2);
					user.setLastname(lastname2);
					user.setAddress(address2);
					user.setPhone(phone2);
					user.setBirthday(date1);
					user.setSex(sex);
					if (avatar.getSize()!=0) {
						user.setAvatar(fileName);
					}
					session.update(user);
					model.addAttribute("userE", user);
					t.commit();

					model.addAttribute("message","Cập nhật thông tin thành công");
					model.addAttribute("back","Quay về trang thông tin cá nhân");
					model.addAttribute("url","user/profile.htm");
			
					// Cáº­p nháº­t thÃ´ng tin phiÃªn hiá»‡n táº¡i
					HttpSession session1 = request.getSession();
					session1.setAttribute("user", user);
					return "user/success";
				} catch (Exception e) {
					t.rollback();
					model.addAttribute("message", "Cập nhật thất bại");
				} finally {
					session.close();
				}
			}
		return "profile";
	}

}
