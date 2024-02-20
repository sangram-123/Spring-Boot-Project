package com.store.controllor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import com.store.entity.BookEntity;

import com.store.service.BookService;

@Controller
public class BookControllor {
	@Autowired
	private BookService service;

	@GetMapping("/home")
	public String home() {
		return "home";
	}

	@GetMapping("/book_register")
	public String book_register() {
		return "book_register";
	}

	@GetMapping("/available_books")
	public ModelAndView getAllBooks() {
		List<BookEntity> list = service.getAllBook();
		return new ModelAndView("bookList", "book", list);
	}

	@PostMapping("/save")
	public String addBook(@ModelAttribute BookEntity b) {
		service.save(b);
		return "redirect:/available_books";
	}

	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id, Model model) {
		BookEntity b = service.getBookById(id);
		model.addAttribute("book", b);
		return "BookEdit";
	}

	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		service.deleteById(id);
		return "redirect:/available_books";
	}

}
