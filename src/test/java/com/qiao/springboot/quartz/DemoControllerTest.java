package com.qiao.springboot.quartz;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DemoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        String json = "{\n" +
                "\n" +
                "\"scheduleType\": \"oneTime\",\n" +
                "\n" +
                "\"startTime\": 1647587385343,\n" +
                "\n" +
                "\"periodStart\": \"00:00:00\",\n" +
                "\n" +
                "\"periodEnd\": \"18:06:06\",\n" +
                "\n" +
                "\"interval\": 55,\n" +
                "\n" +
                "\"schedule\": \"4,6,1\",\n" +
                "\n" +
                "\"cron\": \"0 0 0 ? * 1,3,5\"\n" +
                "\n" +
                "}";

        Map<String, String> map = new HashMap<>();
        map.put("scheduleType", "CRON");
        map.put("cron", "0/10 * * * * ?");

        this.mockMvc.perform(post("/demo/start-job")
                .contentType("application/json")
                .content(map.toString())
                .param("name", "demo job")).andDo(print()).andExpect(status().isOk());
    }
}
