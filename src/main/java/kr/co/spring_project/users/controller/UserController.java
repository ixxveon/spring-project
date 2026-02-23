package kr.co.spring_project.users.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import kr.co.spring_project.users.dto.ReqLoginDTO;
import kr.co.spring_project.users.dto.ReqSignupDTO;
import kr.co.spring_project.users.dto.ResLoginDTO;
import kr.co.spring_project.users.service.UsersService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
	 private final UsersService usersService;
	   
	   // 회원가입 화면 보여줘야지
	   @GetMapping("/signup/form")
	   public String signupForm() {
	      return "pages/signup";
	   }
	   
	   // 회원가입 해야지 (요청 dto 필요하겠지?)
	   @PostMapping("/signup")
	   public String signup(ReqSignupDTO request) {
		   System.out.println(request.getPassword());
	      usersService.signup(request);
	      return "redirect:/users/login/form";
	   }
	   
	   // 로그인 화면 보여주자
	   @GetMapping("/login/form")
	   public String loginForm() {
	      return "pages/login"; 
	   }
	   
	   // 로그인 해야지
	   @PostMapping("/login")
	   public String login(ReqLoginDTO request, HttpSession session) {
		   
		   
		   ResLoginDTO loginUser = usersService.login(request); // 요청 dto 써야지
		   if (loginUser == null) {
			   System.out.println("로그인null");
			   return "redirect:/users/login/form?error=true";
		   }
		   
		   session.setAttribute("LOGIN_USER", loginUser);
		   
		   return "redirect:/"; // 로그인 성공시 홈으로 가자
	   }
	   
	   // 로그아웃
	   @GetMapping("/logout")
	   public String logout(HttpSession session) {
		   session.invalidate();
		   return "pages/home";
	   }
	   
	   // 마이페이지
	   // 
	   @GetMapping("/mypage")
	   public String mypage(ReqLoginDTO request, HttpSession session) {
		   ResLoginDTO loginUser = usersService.login(request);
		   
		   if(loginUser == null) {
			   return "redirect:/users/login/form";
		   }
		   
		   
		   
		   
		   
		   return "/pages/mypage";
	   }
	
	
}
