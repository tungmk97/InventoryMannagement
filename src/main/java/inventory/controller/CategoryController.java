package inventory.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import inventory.model.Paging;
import inventory.ultil.Constant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import inventory.model.Category;
import inventory.service.ProductService;
import inventory.validate.CategoryValidator;

import javax.servlet.http.HttpSession;

@Controller
public class CategoryController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryValidator categoryValidator;
    static final Logger log = Logger.getLogger(CategoryController.class);
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        if(binder.getTarget()==null) {
            return;
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
        if(binder.getTarget().getClass()== Category.class) {
            binder.setValidator(categoryValidator);
        }
    }

    @RequestMapping(value = {"/category/list", "/category/list/"})
    public String redirect() {
        return "redirect:/category/list/1";
    }

    @RequestMapping("/category/list/{page}")
    public String showCategoryList(Model model, HttpSession httpSession, @ModelAttribute("searchForm") Category category,
                                   @PathVariable("page") int page) {
        Paging paging = new Paging(3);
        paging.setIndexPage(page);
        List<Category> categories = productService.getAllCategory(category, paging);
        if(httpSession.getAttribute(Constant.MSG_SUCCESS) != null) {
            model.addAttribute(Constant.MSG_SUCCESS, httpSession.getAttribute(Constant.MSG_SUCCESS));
            httpSession.removeAttribute(Constant.MSG_SUCCESS);
        }
        if(httpSession.getAttribute(Constant.MSG_ERROR) != null) {
            model.addAttribute(Constant.MSG_ERROR, httpSession.getAttribute(Constant.MSG_ERROR));
            httpSession.removeAttribute(Constant.MSG_ERROR);
        }
        model.addAttribute("pageInfo", paging);
        model.addAttribute("categories", categories);

        return "category-list";

    }

    @GetMapping("/category/add")
    public String add(Model model) {
        model.addAttribute("titlePage", "Add Category");
        model.addAttribute("modelForm", new Category());
        model.addAttribute("viewOnly", false);
        return "category-action";
    }

    @GetMapping("/category/edit/{id}")
    public String edit(Model model , @PathVariable("id") int id) {
        log.info("Edit category with id="+id);
        Category category = productService.findByIdCategory(id);
        if(category!=null) {
            model.addAttribute("titlePage", "Edit Category");
            model.addAttribute("modelForm", category);
            model.addAttribute("viewOnly", false);
            return "category-action";
        }
        return "redirect:/category/list";
    }

    @GetMapping("/category/view/{id}")
    public String view(Model model , @PathVariable("id") int id) {
        log.info("View category with id="+id);
        Category category = productService.findByIdCategory(id);
        if(category!=null) {
            model.addAttribute("titlePage", "View Category");
            model.addAttribute("modelForm", category);
            model.addAttribute("viewOnly", true);
            return "category-action";
        }
        return "redirect:/category/list";
    }
    @PostMapping("/category/save")
    public String save(Model model, @ModelAttribute("modelForm") @Validated Category category, BindingResult result,
                       HttpSession httpSession) {
        if(result.hasErrors()) {
            if(category.getId()!=null) {
                model.addAttribute("titlePage", "Edit Category");
            }else {
                model.addAttribute("titlePage", "Add Category");
            }

            model.addAttribute("modelForm", category);
            model.addAttribute("viewOnly", false);
            return "category-action";

        }
        if(category.getId()!=null && category.getId()!=0) {
            try {
                productService.updateCategory(category);
                httpSession.setAttribute(Constant.MSG_SUCCESS, "Save Successfully!!!");
            } catch (Exception e) {
                e.printStackTrace();
                httpSession.setAttribute(Constant.MSG_ERROR, "Save Error!!!");
            }
        }else {
            try {
                productService.saveCategory(category);
                httpSession.setAttribute(Constant.MSG_SUCCESS, "Insert Successfully!!!");
            } catch (Exception e) {
                e.printStackTrace();
                httpSession.setAttribute(Constant.MSG_ERROR, "Insert Error!!!");
            }
        }
        return "redirect:/category/list";

    }

    @GetMapping("/category/delete/{id}")
    public String delete(Model model , @PathVariable("id") int id, HttpSession httpSession) {
        log.info("Delete category with id="+id);
        Category category = productService.findByIdCategory(id);
        if(category!=null) {
            try {
                productService.deleteCategory(category);
                httpSession.setAttribute(Constant.MSG_SUCCESS, "Delete Successfully!!!");
            }catch (Exception e) {
                e.printStackTrace();
                httpSession.setAttribute(Constant.MSG_ERROR, "Delete error!!!");
            }
        }
        return "redirect:/category/list";
    }

}
