package ru.mephi.vikingdemo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.mephi.vikingdemo.model.Viking;
import ru.mephi.vikingdemo.service.VikingService;

import java.util.List;


@RestController
@RequestMapping("/api/vikings")
@Tag(name = "Vikings", description = "Операции с викингами")
public class VikingController {

    private final VikingService vikingService;
    private final VikingListener vikingListener;

    public VikingController(VikingService vikingService, VikingListener vikingListener) {
        this.vikingService = vikingService;
        this.vikingListener = vikingListener;
    }

    @GetMapping
    public List<Viking> getAllVikings() {
        return vikingService.findAll();
    }

    @PostMapping
    public Viking create(@RequestBody Viking viking) {
        return vikingService.addViking(viking);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        vikingService.deleteViking(id);
    }

    @PutMapping("/{id}")
    public Viking update(@PathVariable int id,
                         @RequestBody Viking viking) {
        return vikingService.updateViking(id, viking);
    }
}