package api.drunkhouse.repository;

import api.drunkhouse.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUserNameAndPassword(String userName, String password);
    boolean existsByUserName(String userName);
}
