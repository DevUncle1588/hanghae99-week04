package com.sparta.week03board.controller;

import com.sparta.week03board.dto.SignupRequestDto;
import com.sparta.week03board.model.Board;
import com.sparta.week03board.model.UserRoleEnum;
import com.sparta.week03board.security.UserDetailsImpl;
import com.sparta.week03board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }


    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "redirect:/user/login";
    }

    @GetMapping("/write")
    public String write(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails!=null){
            model.addAttribute("username", userDetails.getUsername());

            if (userDetails.getUser().getRole() == UserRoleEnum.ADMIN) {
                model.addAttribute("admin_role", true);
            }
//            Long userId = userDetails.getUser().getId();
        }
        return "write";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails!=null){
            model.addAttribute("username", userDetails.getUsername());
            model.addAttribute("id", id);

            if (userDetails.getUser().getRole() == UserRoleEnum.ADMIN) {
                model.addAttribute("admin_role", true);
                model.addAttribute("id", id);
            }
//            Long userId = userDetails.getUser().getId();
        } else {
            model.addAttribute("id", id);
        }
        return "detail";
    }

}