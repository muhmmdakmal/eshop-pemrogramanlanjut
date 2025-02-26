package id.ac.ui.cs.advprogid.eshop.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public abstract class AbstractCrudController<T> {

    protected abstract List<T> getAll();
    protected abstract T getById(String id);
    protected abstract T createEntity(T entity);
    protected abstract T updateEntity(T entity);
    protected abstract void deleteEntity(String id);

    // Nama view spesifik untuk masing-masing operasi
    protected abstract String getListView();
    protected abstract String getCreateView();
    protected abstract String getEditView();

    // Method untuk menghasilkan instance baru dari entitas
    protected abstract T createNewInstance();

    @GetMapping("/list")
    public String list(Model model) {
        List<T> entities = getAll();
        model.addAttribute("entities", entities);
        return getListView();
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("entity", createNewInstance());
        return getCreateView();
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute("entity") T entity) {
        createEntity(entity);
        return "redirect:list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, Model model) {
        T entity = getById(id);
        model.addAttribute("entity", entity);
        return getEditView();
    }

    @PostMapping("/edit")
    public String editPost(@ModelAttribute("entity") T entity) {
        updateEntity(entity);
        return "redirect:list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        deleteEntity(id);
        return "redirect:list";
    }
}
