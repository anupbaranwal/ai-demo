# Deepseek clone
A spring boot application using spring-ai to connect to OpenAPIs and doing simple chat operations.

I built a small demo how to simulate a Deepseek/ChatGpt like interface. I built this demo using Spring Boot + Spring AI + HTMX & Tailwind.

## Running the example

To run the example you will need to add your Open API Key. I am using deepseek free api model offered by openrouter.ai.

```properties
spring.ai.openai.api-key=${DEEPSEEK_API_KEY}
```

And then run the application from your IDE or by running the following command:

```shell
./mvnw spring-boot:run 
```
Open the UI application at ```http://localhost:8080```