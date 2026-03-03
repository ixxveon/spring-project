package kr.co.spring_project.join.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReqJoinDTO {
	private Long meetingId;
	private int memberCount;
	private String phoneNumber;
	private String message;
}
