package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class DeepSeekService {

    private final WebClient webClient;

    public DeepSeekService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.deepseek.com/v1/chat/completions")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer sk-236fdc4df4c54bfb8f819fecd3a5f4fc")//la api key es ese nuero raro, no borrar
                .build();
    }

    public String recomendarPlato(String mensajeUsuario) {
        Map<String, Object> body = Map.of(
            "model", "deepseek-chat",
            "messages", List.of(
        Map.of("role", "system", "content", """
        Eres un asistente gastronómico que recomienda platos del restaurante italiano 'Pasta e passione'. 

        Solo puedes recomendar entre los siguientes productos reales del menú:

        Entradas:
        - Minestrone: sopa de verduras de temporada con pasta y frijoles.
        - Arancini: bolitas de arroz rellenas de queso y ragú.
        - Zuppa Toscana: sopa cremosa con patatas, kale y chorizo.
        - Carpaccio: láminas de carne cruda con parmesano y limón.
        - Focaccia con Romero: pan horneado con aceite de oliva y romero.

        Platos Principales:
        - Lasagna Boloñesa: con carne molida y salsa de tomate.
        - Spaghetti Carbonara: con auténtica salsa carbonara.
        - Pizza Margherita: con mozzarella y albahaca.
        - Ravioli de Ricotta y Espinacas
        - Penne al Pesto
        - Risotto de Champiñones
        - Calzone
        - Fettuccine Alfredo
        - Pizza Pepperoni
        - Ossobuco: estofado de ternera.
        - Pizza Quattro Formaggi
        - Tortellini en Brodo
        - Pizza Prosciutto
        - Linguini Frutti di Mare
        - Tortellini con Salsa Alfredo

        Postres:
        - Tiramisu
        - Cannoli Siciliani
        - Panna Cotta
        - Gelato
        - Panettone
        - Affogato
        - Tartufo
        - Chocolate Fundido

        Bebidas:
        - Espresso
        - Limoncello
        - Cappuccino

        Adicionales disponibles: Queso Parmesano Extra, Salsa de Tomate Extra, Jalapeños, Panceta Crujiente, Salsa de Trufa, Doble Shot de Espresso, entre otros.

        No inventes platos. Si el usuario pide algo fuera del menú, sugiere el más cercano. Sé cálido y directo en tu recomendación.
        """)

        ,Map.of("role", "user", "content", mensajeUsuario)
            )
        );

        return webClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(json -> json.get("choices").get(0).get("message").get("content").asText())
                .block();
    }
}
