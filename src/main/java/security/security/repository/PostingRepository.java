package security.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import security.security.domain.MemberEntity;
import security.security.domain.Posting;

import java.util.List;

@Repository
public interface PostingRepository extends JpaRepository<Posting,Long> {
    List<Posting> findAllByMember(MemberEntity member);

}
