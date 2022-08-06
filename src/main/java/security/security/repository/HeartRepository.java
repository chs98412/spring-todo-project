package security.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import security.security.domain.Heart;
import security.security.domain.MemberEntity;
import security.security.domain.Posting;

@Repository
public interface HeartRepository extends JpaRepository<Heart,Long> {
    Heart findByPostingAndMember(Posting posting, MemberEntity member);

    Long countByPosting(Posting posting);
}
