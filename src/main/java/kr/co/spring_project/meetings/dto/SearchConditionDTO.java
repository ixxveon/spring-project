package kr.co.spring_project.meetings.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchConditionDTO {
	private String category;
	private String region;
	private Boolean openOnly;

}
