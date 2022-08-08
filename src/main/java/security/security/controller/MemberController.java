package security.security.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import security.security.MemberDto;
import security.security.MemberService;
import security.security.config.exception.BadRequestException;
import security.security.domain.*;
import security.security.dto.*;
import security.security.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("user")

public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private TodoService todoService;

    @Autowired
    private FriendService friendService;

    @Autowired
    private PostingService postingService;

    @Autowired
    private HeartService heartService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CocommentService cocommentService;


    // 회원가입 페이지
    @GetMapping("/signup")
    public String Signup() {
        return "signup";
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String Signup(MemberDto memberDto,Model model) {

        try {
            memberService.joinUser(memberDto);
        } catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return "signup";
        }

        return "redirect:user/login";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String Login() {
        return "login";
    }


    // 로그아웃 결과 페이지
    @GetMapping("/logout/result")
    public String dispLogout() {
        return "login";
    }

    // 접근 거부 페이지
    @GetMapping("/denied")
    public String dispDenied() {
        return "denied";
    }

    // 내 정보 페이지
    @GetMapping("/info")
    public String dispMyInfo() {
        return "myinfo";
    }

    // 어드민 페이지
    @GetMapping("/admin")
    public String dispAdmin() {
        return "admin";
    }


//    여기부터 새로 추가한 거
//    여기부터 새로 추가한 거
//    여기부터 새로 추가한 거
//    여기부터 새로 추가한 거
//    여기부터 새로 추가한 거
//    여기부터 새로 추가한 거


//    @GetMapping("/calender")
//    public String calender(String id, Model model) {
//        model.addAttribute("dto", new ScheduleDTO());
//
//        model.addAttribute("id", id);
//        return "/calender";
//    }

    //날짜 선택 후 일정 조회1?
    @PostMapping("/schedule")
    public String schedule(@AuthenticationPrincipal UserDetails memberDetail, ScheduleDTO scheduleDTO, Model model) {
        Schedule schedule = scheduleService.find(memberDetail.getUsername(), scheduleDTO.getDate());
        if (scheduleDTO.getDate() == null) {
            throw new BadRequestException("잘못된 접근입니다.");
        }
        if (schedule == null) {
            model.addAttribute("date", scheduleDTO.getDate());
            MemberEntity member = memberService.find(memberDetail.getUsername());
            scheduleDTO.setDate(scheduleDTO.getDate());
            scheduleDTO.setMember(member);
            scheduleDTO.setUserName(member.getName());
            Schedule entity = ScheduleDTO.toEntity(scheduleDTO);
            entity.setScheduleId(null);
            schedule = scheduleService.createSchedule(entity);
        }


        model.addAttribute("dto", new TodoDTO());
        model.addAttribute("schedule", schedule);
        model.addAttribute("todo", schedule.getSchedules());
        return "todolist";
    }

    //친구 날짜 선택 후 일정 조회
    @GetMapping("/frschedule")
    public String frschedule(HttpServletRequest request, @RequestParam String date, Model model, RedirectAttributes redirectAttributes) {

        HttpSession session = (HttpSession) request.getSession();
        Schedule schedule = scheduleService.find((String) session.getAttribute("fr"), date);
        if (schedule == null) {
            redirectAttributes.addAttribute("name", (String) session.getAttribute("fr"));
            redirectAttributes.addAttribute("errormessage", "일정이 존재하지 않습니다.");
            return "redirect:/user/frview";
        }

        model.addAttribute("todo", schedule.getSchedules());
        return "frtodolist";
    }


    //날짜 선택 후 일정 조회2?
    @GetMapping("/schedule")
    public String viewschedule(@AuthenticationPrincipal UserDetails memberDetail, @RequestParam String date, Model model) {
        Schedule schedule = scheduleService.find(memberDetail.getUsername(), date);
        if (schedule == null) {
            model.addAttribute("date", date);
            MemberEntity member = memberService.find(memberDetail.getUsername());
            ScheduleDTO scheduleDTO = new ScheduleDTO();
            scheduleDTO.setDate(date);
            scheduleDTO.setMember(member);
            scheduleDTO.setUserName(member.getName());
            Schedule entity = ScheduleDTO.toEntity(scheduleDTO);
            entity.setScheduleId(null);
            schedule = scheduleService.createSchedule(entity);
            System.out.println(schedule.getMember().getName());
            System.out.println(schedule.getDate());
        }


        model.addAttribute("dto", new TodoDTO());
        model.addAttribute("schedule", schedule);
        model.addAttribute("todo", schedule.getSchedules());
        return "todolist";
    }

    //일정 추가
    @PostMapping("/todo")
    public String todo(String date, @AuthenticationPrincipal UserDetails memberDetail, TodoDTO todoDTO, Model model) {


        Schedule schedule = scheduleService.find(memberDetail.getUsername(), date);

        todoDTO.setSchedule(schedule);

        Todo entity = TodoDTO.toEntity(todoDTO);
        entity.setTodoId(null);
        Todo result = todoService.createTodo(entity);


        model.addAttribute("dto", new TodoDTO());
        model.addAttribute("schedule", schedule);
        model.addAttribute("todo", schedule.getSchedules());
        return "todolist";
    }

    //친구 목록 조회
    @GetMapping("/showfriends")
    public String showFriends(@AuthenticationPrincipal UserDetails memberDetail, Model model) {
        MemberEntity member = memberService.find(memberDetail.getUsername());


        model.addAttribute("friends", member.getFriends());
        return "friend";
    }

    //친구 찾기
    @GetMapping("/findfriend")
    public String findFriend(HttpServletRequest request, FriendDTO friendDTO, Model model) {
        HttpSession session = (HttpSession) request.getSession();
        if (friendDTO.getFriendName() == null) {
            model.addAttribute("request", "null");
        } else {
            try{
                MemberEntity fr = memberService.find(friendDTO.getFriendName());
                model.addAttribute("request", fr.getUsername());
            }
            catch (Exception e) {
                model.addAttribute("request", e.getMessage());
            }
        }
        model.addAttribute("dto", new FriendDTO());

        return "findfriend";
    }

    //친구 추가
    @PostMapping("/addfriend")
    public String addFriend(@AuthenticationPrincipal UserDetails memberDetail, @RequestParam String name, Model model) {
        MemberEntity member = memberService.find(memberDetail.getUsername());
        FriendDTO friendDTO = new FriendDTO();
        friendDTO.setFriendName(name);
        friendDTO.setMember(member);
        Friend fr = FriendDTO.toEntity(friendDTO);
        friendService.createFriend(fr);

        return "redirect:/user/showfriends";
    }

    //친구 메인 뷰
    @GetMapping("/frview")
    public String frView(HttpServletRequest request, @RequestParam String name, Model model ,@RequestParam(required = false) String errormessage) {
        HttpSession session = (HttpSession) request.getSession();
        MemberEntity member = memberService.find(name);
        if (member != null) {
            System.out.println("fr:"+member.getUsername());
            session.setAttribute("fr", member.getUsername());
        }

        if (errormessage != null) {
            model.addAttribute("errorMessage", errormessage);
        }
        return "calendar_fr";
    }

    //포스팅 목록 뷰
    @GetMapping("/postingview")
    public String postingview(@AuthenticationPrincipal UserDetails memberDetail, Model model) {
        MemberEntity member = memberService.find(memberDetail.getUsername());
        List<Posting> postings = postingService.findpostings(member);   //DTO로 넘겨줘야하는거 아닐까?
        for (Posting posting : postings) {
            System.out.println(posting.getTitle());
            System.out.println(posting.getPostingId());
        }
        model.addAttribute("postings", postings);

        return "postingview";
    }

    //포스팅 작성
    @GetMapping("/postingwrite")
    public String postingwrite(HttpServletRequest request, Model model) {
        model.addAttribute("posting", new PostingDTO());

        return "postingwrite";
    }


    //포스팅 작성 포스트
    @PostMapping("/postingpost")
    public String posingpost(@AuthenticationPrincipal UserDetails memberDetail, PostingDTO postingDTO, Model model) {
        MemberEntity member = memberService.find(memberDetail.getUsername());
        postingDTO.setMember(member);
        Posting posting = PostingDTO.toEntity(postingDTO);
        postingService.create(posting);

        return "redirect:/user/postingview";
    }
    //포스팅 상세보기
    @GetMapping("/postingdetail")
    public String postingdetail(@AuthenticationPrincipal UserDetails memberDetail, @RequestParam Long id, Model model) {
        Posting posting = postingService.find(id);
        MemberEntity member = memberService.find(memberDetail.getUsername());
        Heart isHeart = heartService.isHeart(posting, member);
        List<Comment> comments = commentService.findComments(posting);
        List<CommentViewDTO> commentViewDTOS = new ArrayList<>();
        for (Comment comment : comments) {
            CommentViewDTO commentViewDTO = new CommentViewDTO(comment);
            commentViewDTO.setCocommentList(cocommentService.findCocomments(comment));
            commentViewDTOS.add(commentViewDTO);
        }
        Boolean bool = true;
        if (isHeart == null) {
            bool = false;
        } else {
            bool = true;
        }
        Long cnt = heartService.countHeart(posting);

        model.addAttribute("bool", bool);
        model.addAttribute("cnt", cnt.toString());
        model.addAttribute("posting", posting);
        model.addAttribute("comments", commentViewDTOS);
        model.addAttribute("dto", new CommentDTO());
        return "postingdetail";
    }

    //친구 포스팅 뷰
    @GetMapping("/frpostingview")
    public String frpostingview(HttpServletRequest request, Model model) {
        HttpSession session = (HttpSession) request.getSession();
        MemberEntity member = memberService.find((String) session.getAttribute("fr"));
        System.out.println((String) session.getAttribute("fr"));
        List<Posting> postings = postingService.findpostings(member);   //DTO로 넘겨줘야하는거 아닐까?
        model.addAttribute("postings", postings);

        return "frpostingview";
    }


    //친구 포스팅 상세조회
    @GetMapping("/frpostingdetail")
    public String frpostingdetail(@AuthenticationPrincipal UserDetails memberDetail, @RequestParam Long id, Model model) {
        Posting posting = postingService.find(id);
        MemberEntity member = memberService.find(memberDetail.getUsername());
        Heart isHeart = heartService.isHeart(posting, member);

        List<Comment> comments = commentService.findComments(posting);
        List<CommentViewDTO> commentViewDTOS = new ArrayList<>();
        for (Comment comment : comments) {
            CommentViewDTO commentViewDTO = new CommentViewDTO(comment);
            commentViewDTO.setCocommentList(cocommentService.findCocomments(comment));
            commentViewDTOS.add(commentViewDTO);
        }
        Boolean bool = true;
        if (isHeart == null) {
            bool = false;
        } else {
            bool = true;
        }
        Long cnt = heartService.countHeart(posting);

        model.addAttribute("bool", bool);
        model.addAttribute("cnt", cnt.toString());
        model.addAttribute("posting", posting);
        model.addAttribute("comments", commentViewDTOS);
        model.addAttribute("dto", new CommentDTO());


        return "frpostingdetail";
    }


    @GetMapping("/setlike")
    public String isHeart(@AuthenticationPrincipal UserDetails memberDetail, @RequestParam Long id, Model model) {

        Posting posting = postingService.find(id);
        MemberEntity member = memberService.find(memberDetail.getUsername());

        Heart isHeart = heartService.isHeart(posting, member);
        if (isHeart == null) {
            Heart heart = new Heart();
            heart.setMember(member);
            heart.setPosting(posting);
            heartService.create(heart);
        } else {
            heartService.delete(isHeart);
        }

        if (posting.getMember() == member) {
            return "redirect:/user/postingdetail?id=" + id.toString();
        } else {
            return "redirect:/user/frpostingdetail?id=" + id.toString();

        }
    }

    @PostMapping("/createcomment")
    public String createcomment(@AuthenticationPrincipal UserDetails memberDetail, CommentDTO commentDTO, @RequestParam Long id, Model model) {
        Posting posting = postingService.find(id);
        MemberEntity member = memberService.find(memberDetail.getUsername());
        commentDTO.setMember(member);
        commentDTO.setPosting(posting);
        Comment comment = CommentDTO.toEntity(commentDTO);
        commentService.create(comment);


        if (posting.getMember() == member) {
            return "redirect:/user/postingdetail?id=" + id.toString();
        } else {
            return "redirect:/user/frpostingdetail?id=" + id.toString();

        }
    }
    @PostMapping("/createcocomment")
    public String createcocoment(@AuthenticationPrincipal UserDetails memberDetail, CocommentDTO cocommentDTO,@RequestParam Long id, @RequestParam Long postingid, Model model) {
        MemberEntity member = memberService.find(memberDetail.getUsername());
        Comment comment = commentService.find(id);
        cocommentDTO.setMember(member);
        cocommentDTO.setComment(comment);
        Cocomment cocomment = CocommentDTO.toEntity(cocommentDTO);
        cocommentService.create(cocomment);
        Posting posting = postingService.find(postingid);
        if (posting.getMember() == member) {
            return "redirect:/user/postingdetail?id=" + postingid.toString();
        } else {
            return "redirect:/user/frpostingdetail?id=" + postingid.toString();

        }
    }

}