package BookStore.application.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import BookStore.application.domain.Book;
import BookStore.application.domain.BookRepository;
import BookStore.application.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository brepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@ResponseBody
	public String returnBook() {
		return "This is the bookstore";
	}
	
    @RequestMapping(value="/booklist")
    public String bookList(Model model) {	
        model.addAttribute("books", brepository.findAll());
        return "booklist";
    }
    
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", crepository.findAll());
        return "addbook";
    }     
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        brepository.save(book);
        return "redirect:booklist";
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	brepository.deleteById(bookId);
        return "redirect:../booklist";
    } 
    
    @RequestMapping(value = "/edit/{id}")
    public String editBook(@PathVariable("id") Long bookId, Model model){
    model.addAttribute("book", brepository.findById(bookId));
    model.addAttribute("categories", crepository.findAll());
    return "editbook";
    }
	
	

}
