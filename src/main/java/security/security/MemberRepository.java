package security.security;

import org.springframework.data.jpa.repository.JpaRepository;
import security.security.domain.MemberEntity;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByUsername(String username);

    Long countByUsername(String username);
}