package com.oocl.easymovie.controller;

import com.oocl.easymovie.advice.WebSocketServer;
import com.oocl.easymovie.dto.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author edward
 */
@RestController
public class HelloController {

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping("/dev")
    public String devBranch() {
        return "devBranch";
    }

    @GetMapping("/socket/push/{cid}")
    public ResultData<Object> testWebSocket(@PathVariable(value = "cid") String cid) {
        String message = "ticketUsed";
        try {
            WebSocketServer.sendInfo(message, cid);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultData.success();
    }

}
