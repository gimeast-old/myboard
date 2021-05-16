package com.gimeast.myboard.controller;

import com.gimeast.myboard.model.Board;
import com.gimeast.myboard.repository.BoardRepository;
import com.gimeast.myboard.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 2) Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);

        model.addAttribute("maxPage", 10);
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false)Long id) {
        if (id == null) {
            model.addAttribute("board", new Board());
        }else {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "board/form";
    }

    @PostMapping("/form")
    public String formSubmit(@Valid Board board, BindingResult bindingResult){
        boardValidator.validate(board, bindingResult);
        if (bindingResult.hasErrors()) {
            return "board/form";
        }
        boardRepository.save(board);
        return "redirect:/board/list";
    }

}
