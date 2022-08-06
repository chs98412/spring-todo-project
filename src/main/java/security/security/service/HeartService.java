package security.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import security.security.domain.Heart;
import security.security.domain.MemberEntity;
import security.security.domain.Posting;
import security.security.repository.HeartRepository;

@Service
public class HeartService {

    @Autowired
    private HeartRepository heartRepository;

    public Heart isHeart(Posting posting, MemberEntity member) {
        return  heartRepository.findByPostingAndMember(posting, member);

    }


    public Long countHeart(Posting posting) {
        return heartRepository.countByPosting(posting);
    }

    public Heart create(Heart heart) {
        return heartRepository.save(heart);
    }

    public void delete(Heart heart) {
        heartRepository.delete(heart);

        return;
    }
}
