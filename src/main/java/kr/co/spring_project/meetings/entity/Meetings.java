package kr.co.spring_project.meetings.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import kr.co.spring_project.meetings.domain.MeetingsStatus;
import kr.co.spring_project.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Meetings {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// user 테이블이랑 조인 (작성자 회원 Id)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private Users writer;  
	
	@Column(nullable = false, length = 120)
	private String title;
	
	@Column(nullable = false, length = 30)
	private String category;
	
	@Column(nullable = false, length = 50)
	private String region;
	
	@Column(name="meeting_start", nullable = false)
	private LocalDateTime meetingStart;
	
	@Column(name="meeting_end", nullable = false)
	private LocalDateTime meetingEnd;
	
	@Column(nullable = false)
	private Integer capacity;
	
	@Lob
	@Column(nullable = false)
	private String content;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
    private MeetingsStatus status = MeetingsStatus.OPEN;
	
	@Column(name = "view_count", nullable = false)
	private Integer viewCount;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
	
	
	
	
	

}
