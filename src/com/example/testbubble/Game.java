package com.example.testbubble;

public interface Game {

    public void setScreen(Screen screen);

    public Screen getCurrentScreen();

    public Screen getStartScreen();

    public FileIO getFileIO();
    
}
