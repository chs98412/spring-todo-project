package security.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import security.security.domain.MemberEntity;
import security.security.domain.Posting;
import security.security.repository.PostingRepository;

import java.util.List;

@Service
public class PostingService {

    @Autowired
    private PostingRepository postingRepository;

    public Posting create(Posting posting) {
        return postingRepository.save(posting);
    }

    public List<Posting> findpostings(MemberEntity member) {
        return postingRepository.findAllByMember(member);
    }

    public Posting find(Long Id){
        return postingRepository.findById(Id).get();
    }
}
