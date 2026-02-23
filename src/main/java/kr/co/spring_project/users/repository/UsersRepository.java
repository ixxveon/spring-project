package kr.co.spring_project.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.spring_project.users.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{
	   boolean existsByEmail(String email);
	   boolean existsByNickname(String nickname);
	   Users findByEmail(String email);
}
