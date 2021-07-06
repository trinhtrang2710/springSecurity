package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.NewDTO;
import org.springframework.web.bind.annotation.*;

@RestController(value = "newAPIOfWeb")
public class NewAPI {

    @PostMapping("/api/new")
    public NewDTO createNew(@RequestBody NewDTO newDTO){
        return  newDTO;
    }

    @PutMapping("/api/new")
    public NewDTO updateNew(@RequestBody NewDTO newDTO){
        return  newDTO;
    }

    //we can delete more than one new so input should be array
    @DeleteMapping("/api/new")
    public void deleteNew(@RequestBody long[] ids){
        System.out.println("ok");
    }
}

