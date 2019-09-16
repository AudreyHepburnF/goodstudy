package com.personalstudy.goodstudy.designpatterns.Command;

/**
 * 遥控器
 */
public class Invoker {

    private Command[] onCommands;
    private Command[] offCommands;


    public Invoker() {
        int slotNum = 7;
        this.onCommands = new Command[slotNum];
        this.offCommands = new Command[slotNum];
    }


    public void setOnCommand(Command command, int slot) {
        onCommands[slot] = command;
    }


    public void setOffCommand(Command command, int slot) {
        offCommands[slot] = command;
    }


    public void onButtonWasPushed(int slot) {
        onCommands[slot].execute();
    }


    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
    }
}