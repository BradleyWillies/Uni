package uk.ac.aston.jpc.shooter.game.graphics;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SpriteSheet {

	private final Image image;
	private final Map<String, Region> regions = new HashMap<>();

	public class Region {
		private final String name;
		private final int x, y, width, height;
		
		private Region(String name, int x, int y, int w, int h) {
			this.name = name;
			this.x = x;
			this.y = y;
			this.width = w;
			this.height = h;
		}
		
		public void draw(GraphicsContext ctx, double dx, double dy) {
			ctx.drawImage(image, x, y, width, height, dx, dy, width, height);
		}
		
		public String getName() {
			return name;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public int getWidth() {
			return width;
		}
		
		public int getHeight() {
			return height;
		}
	}
	
	public static class Builder {
		private final SpriteSheet sheet;
		private int regionX, regionY, regionW, regionH;
		
		public Builder(Image img) {
			this.sheet = new SpriteSheet(img);
		}
		
		public Builder at(int x, int y) {
			this.regionX = x;
			this.regionY = y;
			return this;
		}
		
		public Builder ofSize(int w, int h) {
			this.regionW = w;
			this.regionH = h;
			return this;
		}
		
		public SpriteSheet build() {
			return sheet;
		}
		
		public Builder region(String name) {
			sheet.regions.put(name, sheet.new Region(name, regionX, regionY, regionW, regionH));
			return this;
		}
	}

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
