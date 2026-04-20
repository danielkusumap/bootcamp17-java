package com.bootcamp.bootcamp17.controller;

import org.springframework.web.bind.annotation.*;

@RestController // Menandakan bahwa class adalah Controller untuk REST API
@RequestMapping("/api") // base url dari controller
public class FirstController {
    @GetMapping("/halo")
    public String sayHallo(){
        return "Haloooo";
    }

    @GetMapping("/salam")
    public String salam() {
        return "endpoint salam";
    }

    @GetMapping("/nama")
    public String sebutNama(
            @RequestParam(required = true) String nama
    ){
        return "Nama kamu: " + nama;
    }

    @GetMapping("/namapath/{nama}")
    public String sebutNamaPath(
            @PathVariable String nama
    ){
        return "Nama kamu: " + nama;
    }
}
