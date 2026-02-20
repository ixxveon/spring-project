package kr.co.spring_project.join.service;

import kr.co.spring_project.join.dto.ReqJoinDTO;

public interface JoinRequestsService {
	public void getStatusCheck(Long meetingId);
	
	public void getMemberCount(ReqJoinDTO request);
}
