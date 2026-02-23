package kr.co.spring_project.join.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.spring_project.join.entity.JoinRequests;

public interface JoinRequestsRepository  extends JpaRepository<JoinRequests, Long> {

	boolean existsByMeeting_IdAndUserId_Id(Long meetingId, Long userId);

	// 내 신청 목록 조회 (최신순으로)
	List<JoinRequests> findByUserId_IdOrderByIdDesc(Long userId);
	

}
