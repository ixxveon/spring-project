package kr.co.spring_project.join.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.spring_project.join.entity.JoinRequests;

public interface JoinRequestsRepository  extends JpaRepository<JoinRequests, Long> {

	boolean existsByMeeting_IdAndUserId(Long meetingId, Long UserId);

	List<JoinRequests> findAllByOrderByIdDesc();
	

}
