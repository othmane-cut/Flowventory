package com.flowventorygrp.flowventory.pos.controller;



import com.flowventorygrp.flowventory.pos.model.Product;
import com.flowventorygrp.flowventory.pos.model.ProductType;
import com.flowventorygrp.flowventory.pos.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "0") int page, Model model) {
        // Crée un objet Pageable pour définir la page, la taille et l’ordre d’affichage
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id").descending());

        // Récupère une page de produits depuis la base de données
        Page<Product> productPage = productRepository.findAll(pageable);

        // Envoie la liste des produits à la vue (seulement les 10 de la page actuelle)
        model.addAttribute("products", productPage.getContent());

        // Envoie les infos nécessaires pour la pagination
        model.addAttribute("currentPage", page); // numéro de la page actuelle
        model.addAttribute("totalPages", productPage.getTotalPages()); // nombre total de pages

        // Retourne la vue index.html
        return "index";
    }

    @GetMapping("/product/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("types", ProductType.values());
        return "newProduct";
    }

    @PostMapping("/product")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", ProductType.values());
            return "newProduct";
        }
        productRepository.save(product);
        return "redirect:/";
    }

    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produit invalide : " + id));
        model.addAttribute("product", product);
        model.addAttribute("types", ProductType.values());
        return "editProduction";
    }

    @PostMapping("/product/{id}")
    public String updateProduct(@PathVariable Long id,
                                @Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", ProductType.values());
            return "editProduction";
        }
        productRepository.save(product);
        return "redirect:/";
    }
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Model model) {
        productRepository.deleteById(id);
        return "redirect:/";}

}
