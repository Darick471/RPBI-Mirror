package com.expedienteclinico.expedienteclinico.controllers.almacen;

import com.expedienteclinico.expedienteclinico.models.almacen.CategoryModel;
import com.expedienteclinico.expedienteclinico.services.almacen.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/almacen/categories")
@Tag(name = "Almacén: Categorías", description = "Gestión de categorías de artículos para Farmacia, Clínica y Equipos")
@CrossOrigin(origins = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "Obtener todas las categorías", description = "Devuelve una lista de todas las categorías de almacén definidas en el sistema.")
    @GetMapping
    public List<CategoryModel> getAll() {
        return categoryService.getAllCategories();
    }

    @Operation(summary = "Crear una nueva categoría", description = "Guarda una nueva categoría como 'Farmacia' o 'Equipo Médico' en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoría creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada no válidos")
    })
    @PostMapping
    public CategoryModel create(@RequestBody CategoryModel category) {
        return categoryService.saveCategory(category);
    }

    @Operation(summary = "Obtener categoría por ID", description = "Busca una categoría específica usando su ID entero único. Devuelve 404 si no se encuentra.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría encontrada y devuelta con éxito"),
            @ApiResponse(responseCode = "404", description = "El ID de categoría proporcionado no existe en la base de datos")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoryModel> getById(
            @Parameter(description = "ID de la categoría que se va a recuperar", example = "1")
            @PathVariable Long id) {

        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar categorías por nombre", description = "Filtrar categorías por una cadena de nombre parcial (sin distinguir mayúsculas de minúsculas).")
    @GetMapping("/search")
    public List<CategoryModel> searchByName(
            @Parameter(description = "Nombre parcial de la categoría a buscar", example = "Farmacia")
            @RequestParam String name) {

        return categoryService.findByNameContaining(name);
    }
}