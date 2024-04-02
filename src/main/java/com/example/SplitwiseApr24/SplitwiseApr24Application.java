package com.example.SplitwiseApr24;

import com.example.SplitwiseApr24.commands.Command;
import com.example.SplitwiseApr24.commands.CommandRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SplitwiseApr24Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SplitwiseApr24Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner sc = new Scanner(System.in);
		while (true){
			System.out.println("Awaiting input");
			String input = sc.nextLine();
			try {
				Command command = CommandRegistry.getInstance().getCommand(input);
				command.execute(input);
			} catch (Exception e){
				System.out.println("Something went wrong, please reenter the command. Exception: " + e.getMessage());
			}
		}
	}
}
