package security.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import security.security.domain.Friend;
import security.security.repository.FriendRepository;

import java.util.List;

@Service
public class FriendService {
    @Autowired
    private FriendRepository repository;

    public List<Friend> create(final Friend friend) {
        repository.save(friend);

        return repository.findByFriendId(friend.getFriendId());
    }

    public List<Friend> findAll(){
        return repository.findAll();
    }

    public Friend createFriend(final Friend friend) {
        return repository.save(friend);
    }
}
