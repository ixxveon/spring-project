package kr.co.spring_project.meetings.dto;

import java.time.LocalDateTime;

import kr.co.spring_project.meetings.entity.MeetingsStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DetailResponseDTO {
	
	private Long id;
	
	private String title;
	private String category;
	private String region;
	
	private LocalDateTime meetingsStart;
	private LocalDateTime meetingsEnd;
	
	private Integer capacity;
	private Integer currentCount;
	
	private String content;
	
	private MeetingsStatus status;
	private Integer viewCount;
	
	private Long writerId;
	private String writerNickname;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
