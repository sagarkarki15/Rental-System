package com.sagarthyme.brs.controller.category;

import com.sagarthyme.brs.dto.AuthorDto;
import com.sagarthyme.brs.dto.CategoryDto;
import com.sagarthyme.brs.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    public CategoryService categoryService;

    @GetMapping
    public String openCategoryPage(Model model){
        model.addAttribute("listCategory", categoryService.findAll());
        return "category/categorypage";
    }

    @GetMapping("/addcategory")
    public String openAddCategoryPage(Model model){
        model.addAttribute("categoryDto",new CategoryDto());
        return "category/addcategory/addcategorypage";
    }

    @PostMapping("savecategory")
    public String saveCategory(@Valid @ModelAttribute CategoryDto categoryDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ("category/addcategory/addcategorypage");
        }
        categoryService.save(categoryDto);
        return "redirect:/category";
    }

    @GetMapping("updatecategory/{id}")
    public String updateCategory(@PathVariable("id") Integer id, Model model){
        model.addAttribute("categoryDto", categoryService.findById(id));
        return "category/addcategory/addcategorypage";
    }

    @GetMapping("deletecategory/{id}")
    public String deleteCategory(@PathVariable("id") Integer id){
        categoryService.deleteById(id);
        return "redirect:/category";
    }
}
