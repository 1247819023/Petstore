package com.lgj.controller;

import com.github.pagehelper.PageInfo;
import com.lgj.dao.PetMapper;
import com.lgj.entity.ApiResponse;
import com.lgj.entity.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetMapper petMapper;

    @GetMapping("/selAll")
    public String selAll(@RequestParam(defaultValue = "1") Model model) {
        List<Pet> pets = petMapper.selectAll();
        model.addAttribute("pets", pets);
        model.addAttribute("pageInfo", new PageInfo<>(pets));

        return "pet";
    }

    @PostMapping("/insert")
    public ApiResponse insert(Pet pet) {
        int c = petMapper.insert(pet);
        if (c > 0) {
            return new ApiResponse();
        } else {
            return new ApiResponse(405, "error", "Invalid input");
        }
    }

    @PutMapping("/addupdate")
    @ResponseBody
    public ApiResponse update(Pet pet) {
        if (pet.getPid() == null) {
            if (petMapper.selectByPrimaryKey(pet.getPid()) == null) {
                if (petMapper.updateByPrimaryKey(pet) != 0) {
                    return new ApiResponse();
                } else {
                    return new ApiResponse(405, "error", "Validation exception");
                }
            } else {
                return new ApiResponse(404, "error", "Pet not found");
            }
        }else {
            return  new ApiResponse(400,"error","Invalid ID supplied");
        }
    }

    @GetMapping("/findPetId")
    public ApiResponse findPetId(@PathVariable("pid") int pid, Model model) {
        if (petMapper.selectByPrimaryKey(pid) !=null ){
            return new ApiResponse(200,"successful","successful operation");
        }else {
            return new ApiResponse(400,"error","Invalid status value");
        }
    }

    @PostMapping("/update")
    public ApiResponse PetUpdate(Pet pet) {
        int c = petMapper.updateByPrimaryKey(pet);
        if (c > 0) {
            return new ApiResponse();
        }
        return new ApiResponse(405, "err", "Validation exception");
    }


    @DeleteMapping("/delete")
    public ApiResponse delete(@PathVariable("pid") int pid) {
        if (petMapper.deleteByPrimaryKey(pid)>0){
            return new ApiResponse();
        }else {
            return  new ApiResponse(400,"error","Invalid ID supplied");
        }
    }
}
