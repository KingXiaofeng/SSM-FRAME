package com.ssm.frame.web;

import com.ssm.frame.entity.Book;
import com.ssm.frame.service.interfaces.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by wangxf on 2017/5/24.
 */
@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    IBookService bookService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model){

        List<Book> list = bookService.getList();
        model.addAttribute("list",list);
        // list.jsp + model = ModelAndView
        return "list";
    }
}
