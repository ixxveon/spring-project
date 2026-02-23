package kr.co.spring_project.users.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReqSignupDTO {
	private String name;
	private String email;
	private String nickname;
	private String password;
	private String passwordCheck;
}
