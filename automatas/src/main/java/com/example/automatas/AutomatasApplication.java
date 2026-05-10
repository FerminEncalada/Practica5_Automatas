package com.example.automatas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class AutomatasApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx =
				SpringApplication.run(AutomatasApplication.class, args);

		Environment env = ctx.getEnvironment();

		String port    = env.getProperty("server.port", "8080");
		String context = env.getProperty("server.servlet.context-path", "");

		String separator = "=".repeat(60);

		System.out.println("\n" + separator);
		System.out.println("  ✔  APLICACIÓN INICIADA CORRECTAMENTE");
		System.out.println(separator);
		System.out.println("  🌐  URL local:    http://localhost:" + port + context + "/");
		System.out.println("  🌐  URL red:      http://0.0.0.0:"  + port + context + "/");
		System.out.println("  📌  Autómatas disponibles:");
		System.out.println("       - Telemetría IoT       → ?automata=iot");
		System.out.println("       - Secuencias Genéticas → ?automata=genetico");
		System.out.println("       - E-commerce           → ?automata=ecommerce");
		System.out.println(separator + "\n");
	}
}