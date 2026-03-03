package kr.co.spring_project.meetings.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReqBoardDTO {
	@NotBlank
	private String title;
	@NotBlank
	private String category;
	@NotBlank
	private String region;
	@NotBlank
	private String content;
	@NotNull
	private LocalDateTime meetingStart;
	private LocalDateTime meetingEnd;
	
	@NotNull
	@Min(1)
	private Integer capacity;
}
