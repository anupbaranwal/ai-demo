package com.openapi.ai.controller;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class OpenAIController {
  private final OpenAiChatModel chatModel;

  public OpenAIController(OpenAiChatModel chatModel) {
    this.chatModel = chatModel;
  }
  @GetMapping("/ask/{model}/{message}")
  public ResponseEntity<String> getAnswerFromAI(@PathVariable("model") String model, @PathVariable("message") String message) {
    String response = chatModel.call(message);
    return ResponseEntity.ok(response);
  }
}
