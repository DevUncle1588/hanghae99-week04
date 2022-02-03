package com.sparta.week03board.service;

import com.sparta.week03board.dto.ReplyRequestDto;
import com.sparta.week03board.model.Reply;
import com.sparta.week03board.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    @Autowired
    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    @Transactional
    public Reply createReply(ReplyRequestDto requestDto, Long userId ) {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Reply reply = new Reply(requestDto, userId);
        System.out.println("============================");
        System.out.println(requestDto.getContents());
        System.out.println(requestDto.getUsername());
        System.out.println(userId);
        System.out.println("============================");
        replyRepository.save(reply);

        return reply;
    }

//    @Transactional
//    public Long update(Long id, ReplyRequestDto requestDto) {
//        Reply reply = replyRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );
//        reply.update(requestDto);
//        return reply.getId();
//    }
    @Transactional
    public Reply updateReply(Long id, ReplyRequestDto requestDto) {
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));

        reply.update(requestDto);
        return reply;
    }

    @Transactional
    public void deletePost(Long id) {
        replyRepository.deleteById(id);
    }

    @Transactional
    public Optional getOnePost(Long id) {
        return replyRepository.findById(id);
    }

    public List<Reply> getReply(Long userId) {
        return replyRepository.findAllByUserId(userId);
    }


    @Transactional
    public List<Reply> getReplysByboardId(Long boardId) {
        System.out.println("findAllByboardId");
        return replyRepository.findAllByBoardIdOrderByCreatedAtDesc(boardId);
//        return replyRepository.findAll(Sort.by(Sort.Direction.DESC, "boardId"));
    }


//    @Transactional
//    public Long readOnePost(Long id, ReplyRequestDto requestDto) {
//        Reply reply = replyRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
//        );
//        reply.update(requestDto);
//        return reply.getId();
//    }
}