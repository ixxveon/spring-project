package kr.co.spring_project.users.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResLoginDTO {
	private Long Id;
	private String email;
	private String nickname;
	private String password;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
