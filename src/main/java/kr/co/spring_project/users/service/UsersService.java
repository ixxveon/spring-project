package kr.co.spring_project.users.service;

import kr.co.spring_project.users.dto.ReqLoginDTO;
import kr.co.spring_project.users.dto.ReqSignupDTO;
import kr.co.spring_project.users.dto.ResLoginDTO;

public interface UsersService {
	void signup(ReqSignupDTO request);
	ResLoginDTO login(ReqLoginDTO request);
}
