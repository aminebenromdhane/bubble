package com.example.testbubble;

public class MainActivity extends GLGame {

	@Override
	public Screen getStartScreen() {
		return new DemoScreen(this);
	}
}
