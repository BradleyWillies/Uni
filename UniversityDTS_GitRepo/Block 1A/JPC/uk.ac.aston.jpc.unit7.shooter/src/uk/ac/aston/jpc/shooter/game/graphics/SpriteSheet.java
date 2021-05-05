package uk.ac.aston.jpc.shooter.game.graphics;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SpriteSheet {

	private final Image image;
	private final Map<String, Region> regions = new HashMap<>();

	// TODO add Region non-static member class
	
	// TODO add Builder static member class

	private SpriteSheet(Image img) {
		this.image = img;
	}

	public Region getRegion(String name) {
		return regions.get(name);
	}

	public Region[] getRegions(String[] sRegions) {
		final Region[] regions = new Region[sRegions.length];
		for (int i = 0; i < sRegions.length; i++) {
			regions[i] = getRegion(sRegions[i]);
		}
		return regions;
	}

}
