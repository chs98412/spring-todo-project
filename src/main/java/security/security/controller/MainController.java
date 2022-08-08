package security.security.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import security.security.MemberService;
import security.security.domain.MemberEntity;
@Controller
@AllArgsConstructor
public class MainController {
    @Autowired
    private MemberService memberService;
    // 메인 페이지
    @GetMapping("/")
    public String index(@AuthenticationPrincipal UserDetails memberDetail, Model model  ) {

        try{
            System.out.println(memberDetail.getUsername());
            MemberEntity memberEntity = memberService.find(memberDetail.getUsername());
            model.addAttribute("friendscnt", memberEntity.getFriends().size());
            model.addAttribute("friends", memberEntity.getFriends());

            model.addAttribute("username", memberEntity.getName());



            return "index";
        }catch (Exception e){
            System.out.println("can't find!");
            return "redirect:user/login";
        }

    }
}
