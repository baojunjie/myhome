package com.myhome.web.controller;

import com.myhome.entity.GameWorksItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;



@Controller
@RequestMapping("/game/works/item")
public class GameWorksItemController extends AbstractController {

    

    



    @RequestMapping("/get")
    public GameWorksItem get(@RequestParam("id") Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    
    @RequestMapping("/add")
    public void add(@ModelAttribute("gameWorksItem") GameWorksItem gameWorksItem) {
        // TODO 待完成
    }
    
    
    @RequestMapping("/update")
    public int update(@ModelAttribute("gameWorksItem") GameWorksItem gameWorksItem) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    
    @RequestMapping("/remove")
    public int remove(@RequestParam("id") Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }
    
    
    @RequestMapping("/delete")
    public int delete(@RequestParam("id") Long id) {
        // TODO 待完成
        throw new RuntimeException();
    }
    

}
