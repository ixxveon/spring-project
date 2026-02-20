package kr.co.spring_project.join.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import kr.co.spring_project.meetings.entity.Meetings;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class JoinRequests {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private Meetings meetingId;
	
	//private Users userId;
	
	@Column(nullable = false)
	private int memberCount;
	
	@Column(nullable = false, length = 20)
	private String phoneNumber;
	
	@Lob
	@Column(nullable = true, length = 255)
	private String message;
	
	@Column(nullable = false, length = 10)
	private String status;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	

	@PrePersist
	public void prePersist() {
		this.createdAt = LocalDateTime.now();
	}
		
	@PreUpdate
	public void preUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
	
}