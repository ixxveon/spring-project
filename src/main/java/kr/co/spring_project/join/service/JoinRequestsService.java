package kr.co.spring_project.join.service;

import java.util.List;

import kr.co.spring_project.join.dto.ReqJoinDTO;
import kr.co.spring_project.join.dto.ResJoinDTO;
import kr.co.spring_project.meetings.entity.Meetings;

public interface JoinRequestsService {
	Meetings getStatusCheck(Long meetingId);
	
	/**
	 * 참여 신청 로직
	 * - 로그인한 사용자만 신청 가능
	 * - 모집 상태가 OPEN이어야 함
	 * - 정원이 남아 있어야 함
	 * - 중복 신청 방지 (이미 신청했으면 막기)
	 * @param request
	 */
	void apply(ReqJoinDTO request, Long userId);

	List<ResJoinDTO> getMyList(Long userId);
}
