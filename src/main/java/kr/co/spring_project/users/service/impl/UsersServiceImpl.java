package kr.co.spring_project.users.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.spring_project.users.dto.ReqLoginDTO;
import kr.co.spring_project.users.dto.ReqSignupDTO;
import kr.co.spring_project.users.dto.ResLoginDTO;
import kr.co.spring_project.users.entity.Users;
import kr.co.spring_project.users.repository.UsersRepository;
import kr.co.spring_project.users.service.UsersService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
	private final UsersRepository usersRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override 
	public void signup(ReqSignupDTO request) {
	      
	      // 1. 비밀번호 & 비밀번호 확인 검증
	      if(request.getPassword() != null && !request.getPassword().equals(request.getPasswordCheck())) {
	         System.out.println("비밀번호가 일치하지 않습니다.");
	         return;
	      }
	         
	      // 2. 이메일 중복 체크
	      if(usersRepository.existsByEmail(request.getEmail())) {
	         System.out.println("이미 사용 중인 이메일입니다.");
	         return;
	      }
	         
	      // 3. 닉네임 중복 체크
	      if (usersRepository.existsByNickname(request.getNickname())) {
	    	  System.out.println("이미 사용 중인 닉네임입니다.");
	    	  return;
	      }
	      
	      // 4. 비밀번호 암호화 (Spring Security의 BCrypt 사용)
	      String encodedPassword = passwordEncoder.encode(request.getPassword());
	      
	      // 5. Entity로 변경
	      Users users = Users.builder()
	                    .name(request.getName())
	                    .email(request.getEmail())
	                    .nickname(request.getNickname())
	                    .password(encodedPassword)
	                    .region("미설정")
	                    .build();
	      
	      // 6. DB에 저장
	      usersRepository.save(users);
	   }
	
	@Override // 엔티티에서 데이터 꺼내와서 반응값으로 돌려줘야지
	public ResLoginDTO login(ReqLoginDTO request) {
		// 1. 해당 데이터를 가진 유저가 있는지 조회
		Users users = usersRepository.findByEmail(request.getEmail());
		if (users == null) {
			System.out.println("?");
			return null;
		}
		
		// 2. 사용자가 입력한 비밀정보가 암호화된 비밀번호와 일치하는지 검증
		if(!passwordEncoder.matches(request.getPassword(), users.getPassword())) {
			System.out.println("??");
			return null;
		}
		
		// dto로 변환해서 컨트롤러한테 돌려줘야지
		ResLoginDTO response = ResLoginDTO.builder()
							   .Id(users.getId())
							   .email(users.getEmail())
							   .nickname(users.getNickname())
							   .password(users.getPassword())
							   .createdAt(users.getCreatedAt())
							   .updatedAt(users.getUpdatedAt())
							   .build();
		
		return response;
	}
}
	   
		
		
