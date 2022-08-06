package security.security;


import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import security.security.config.exception.AlreadyExsistException;
import security.security.config.exception.NotFoundException;
import security.security.domain.MemberEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {
    private MemberRepository memberRepository;

    @Transactional
    public Long joinUser(MemberDto memberDto) throws RuntimeException {
        // 비밀번호 암호화

        if (memberRepository.countByUsername(memberDto.getUsername()) > 0) {
            throw new AlreadyExsistException("이미 존재하는 사용자 입니다.");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        return memberRepository.save(memberDto.toEntity()).getId();
    }

    public MemberEntity find(String username) throws RuntimeException{
        Optional<MemberEntity> userEntityWrapper = memberRepository.findByUsername(username);
        MemberEntity userEntity;
        if (userEntityWrapper.isPresent()) {
            userEntity = userEntityWrapper.get();
            return userEntity;
        }
        else if(username==null){
            return null;
        }
        else{
            throw new NotFoundException("존재하지 않는 유저입니다.");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MemberEntity> userEntityWrapper = memberRepository.findByUsername(username);
        MemberEntity userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin@example.com").equals(username)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }
}