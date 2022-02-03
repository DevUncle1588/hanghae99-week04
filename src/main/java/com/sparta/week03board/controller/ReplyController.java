package com.sparta.week03board.controller;

import com.sparta.week03board.dto.ReplyRequestDto;
import com.sparta.week03board.model.Board;
import com.sparta.week03board.model.Reply;
import com.sparta.week03board.model.UserRoleEnum;
import com.sparta.week03board.security.UserDetailsImpl;
import com.sparta.week03board.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ReplyController {
    private final ReplyService replyService;

    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @GetMapping("/reply/{id}")
    public List<Reply> readPosts(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println(userDetails==null);
//      Long userId = userDetails.getUser().getId();

        return replyService.getReplysByboardId(id);
//        return replyService.etBoard(userId);
    }


    @PostMapping("/reply")
    public Reply createReply(@RequestBody ReplyRequestDto requestDto,
                             @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = null;
        if (userDetails!=null){

//            if (userDetails.getUser().getRole() == UserRoleEnum.ADMIN) {
//                model.addAttribute("admin_role", true);
//            }
            userId = userDetails.getUser().getId();
        } else {
//            return "redirect:/user/login";
        }
        Reply reply = replyService.createReply(requestDto, userId);
        return reply;
    }


//    @GetMapping("/api/posts/{targetId}")
//    public Reply readOne(@PathVariable Long targetId) {
//        Optional<Reply> reply = replyService.getOnePost(targetId);
//        return reply.get();
//    }


    @PutMapping("/reply/{targetId}")
    public Long updateReply(@PathVariable Long targetId, @RequestBody ReplyRequestDto requestDto) {
        Reply reply = replyService.updateReply(targetId, requestDto);

        // 응답 보내기 (업데이트된 상품 id)
        return reply.getId();

    }


    @DeleteMapping("/reply/delete/{id}")
    public Long deleteReply(@PathVariable Long id) {
        replyService.deletePost(id);
        return id;
    }




}
