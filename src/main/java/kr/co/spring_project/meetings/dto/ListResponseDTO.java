package kr.co.spring_project.meetings.dto;

import java.time.LocalDateTime;

import kr.co.spring_project.meetings.entity.MeetingsStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ListResponseDTO {
	
	private Long id;
	private String title;
	private String category;
	private String region;
	
	private LocalDateTime meetingsStart;
	
	private Integer capacity;
	private Integer currentCount;
	
	private MeetingsStatus status;
	private Integer viewCount;

}
