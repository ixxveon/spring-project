package kr.co.spring_project.join.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResJoinDTO {
	
	private Long id;
	private Long userId;
	private Long meetingId;
	private String status;
	private LocalDateTime createdAt;

}
