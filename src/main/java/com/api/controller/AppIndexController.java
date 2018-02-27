package com.api.Controller;

import com.api.model.Response;
import com.api.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created by zhou_yanga on 2018/2/27.
 */
@Controller
@RequestMapping("/shop")
public class AppIndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping("/index")
    @ResponseBody
    public Response index(@RequestParam String cid){
        return indexService.index(cid);
    }
}
