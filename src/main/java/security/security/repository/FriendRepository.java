package security.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import security.security.domain.Friend;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend,Long> {
    List<Friend> findByFriendId(Long id);

}
