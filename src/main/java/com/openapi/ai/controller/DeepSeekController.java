package com.openapi.ai.controller;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * org.springframework.ui.Model is typically used in Spring MVC applications with @Controller to pass data to view templates.
 * However, when using @RestController for RESTful APIs, the focus shifts to returning data directly in formats like JSON or XML, rather than rendering views.
 * Consequently, Model is generally not used with @RestController
 */
@Controller
@RequestMapping("/api/v1")
@CrossOrigin("*")
@Slf4j
public class DeepSeekController {

  private final OpenAiChatModel chatModel;
  private final ChatClient chatClient;

  public DeepSeekController(OpenAiChatModel chatModel, ChatClient.Builder chatClientBuilder) {
    this.chatModel = chatModel;
    this.chatClient = chatClientBuilder.build();
  }

  @GetMapping("/ask/{message}")
  public ResponseEntity<String> getAnswerFromAI(@PathVariable("message") String message) {
    String response = chatModel.call(message);
    return ResponseEntity.ok(response);
  }

  @HxRequest
  @PostMapping("/chat")
  public HtmxResponse generate(@RequestParam("message") String message, Model model) {
    log.info("User Message: {}", message);
       String response = chatClient.prompt()
                .user(message)
                .call()
                .content();

    model.addAttribute("response",response);
    model.addAttribute("message",message);

    return HtmxResponse.builder()
        .view("response :: responseFragment")
        .view("recent-message-list :: messageFragment")
        .build();
  }
}
