package com.sampler.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sampler.GdxGeneratedSample;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		// Light weight Java Game Library Application
		new LwjglApplication(new GdxGeneratedSample(), config);
	}
}
