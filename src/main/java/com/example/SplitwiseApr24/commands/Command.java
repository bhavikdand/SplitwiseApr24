package com.example.SplitwiseApr24.commands;

import com.example.SplitwiseApr24.exceptions.InvalidCommandException;

public interface Command {
    void execute(String input) throws InvalidCommandException;
}
