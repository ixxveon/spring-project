package kr.co.spring_project.users.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Users {
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long id;
	   
	   @Column(nullable = false, length = 50)
	   private String name;
	   
	   @Column(nullable = false, length = 80, unique = true)
	   private String email;
	   
	   @Column(nullable = false, length = 30, unique = true)
	   private String nickname;
	   
	   @Column(nullable = false, length = 255)
	   private String password;
	   
	   @Column(nullable = false, length = 30)
	   private String region;
	   
	   @Column(nullable = false)
	   private LocalDateTime createdAt;
	   private LocalDateTime updatedAt;
	   
	   @PrePersist
	   public void prePersist() {
	      this.createdAt = LocalDateTime.now();
	      this.updatedAt = LocalDateTime.now();
	   }
}
