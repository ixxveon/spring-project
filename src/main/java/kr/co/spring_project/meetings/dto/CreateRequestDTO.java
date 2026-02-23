package kr.co.spring_project.meetings.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRequestDTO {
	
	private String title;
	private String category;
	private String region;
	
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime meetingsStart;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime meetingsEnd;

	private Integer capacity;

	private String content;
	
	
}
