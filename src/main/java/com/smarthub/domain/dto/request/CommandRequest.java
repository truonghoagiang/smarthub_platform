package com.smarthub.domain.dto.request;

import com.smarthub.integration.core.Command;

import java.util.List;

public class CommandRequest {
    private List<Command> commands;

    public CommandRequest() {
    }

    public CommandRequest(List<Command> commands) {
        this.commands = commands;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    public static class Builder{
        private List<Command> commands;

        public Builder commands(List<Command> commands) {
            this.commands = commands;
            return this;
        }

        public CommandRequest build(){
            return new CommandRequest(commands);
        }
    }

    public static CommandRequest builder(){
        return new CommandRequest();
    }
}
