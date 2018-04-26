package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogController {
    @Autowired
    private BlogRepository blogRepository;

    @GetMapping("/blog")
    public String getBlogs(Model model) {
        Iterable<BlogModel> blogs = blogRepository.findAll();
        model.addAttribute("blogs", blogs);
        return "blog/blogs";
    }

    @GetMapping("/blog/{blogId}")
    public String getBlog(@PathVariable("blogId") Long blogId, Model model) {
        model.addAttribute("blogModel", blogRepository.findById(blogId).get());
        return "blog/blog";
    }

    @GetMapping("/blog/edit/{blogId}")
    public String startEditBlog(@PathVariable("blogId") Long blogId, Model model) {
        BlogModel blog = blogRepository.findById(blogId).get();
        model.addAttribute("blogModel", blog);
        return "blog/edit-blog";
    }

    @GetMapping("/blog/new")
    public String newBlog(Model model) {
        BlogModel blogModel = new BlogModel();
        model.addAttribute("blogModel", blogModel);
        return "blog/edit-blog";
    }

    @PostMapping("/save-blog")
    public String saveBlog(@ModelAttribute("blogModel") BlogModel blog) {
        BlogModel savedBlog = blogRepository.save(blog);
        return "redirect:/blog/" + savedBlog.getId().toString() + "";
    }
}

