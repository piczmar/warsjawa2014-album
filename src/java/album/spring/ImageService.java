package album.spring;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;

class ImageService {
	private static Logger LOGGER = Logger.getLogger(ImageService.class);

	public enum Operation {
		RotateClockWise90, RotateAntiClockWise90, Rotate180, Flip, Flop
	}

	public byte[] transform(byte[] data, Operation operation)
			throws IOException {
		long start = System.currentTimeMillis();

		BufferedImage original = ImageIO.read(new ByteArrayInputStream(data));
		AffineTransform transform = new AffineTransform();
		AffineTransformOp op = null;
		boolean paint = false;
		Integer width = original.getWidth();
		Integer height = original.getHeight();
		switch (operation) {
		case RotateClockWise90:
			transform.rotate(Math.PI / 2.0);
			transform.translate(0, -height);
			op = new AffineTransformOp(transform,
					AffineTransformOp.TYPE_BILINEAR);
			width = original.getHeight();
			height = original.getWidth();
			paint = true;
			break;
		case RotateAntiClockWise90:
			transform.rotate(-Math.PI / 2.0);
			transform.translate(-width, 0);
			op = new AffineTransformOp(transform,
					AffineTransformOp.TYPE_BILINEAR);
			width = original.getHeight();
			height = original.getWidth();
			paint = true;
			break;
		case Rotate180:
			transform.rotate(Math.PI);
			transform.translate(-width, -height);
			op = new AffineTransformOp(transform,
					AffineTransformOp.TYPE_BILINEAR);
			break;
		case Flip: // vertical
			transform.scale(-1.0d, 1.0d);
			transform.translate(-width, 0);
			op = new AffineTransformOp(transform,
					AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			break;
		case Flop: // horizontal
			transform.scale(1.0d, -1.0d);
			transform.translate(0, -height);
			op = new AffineTransformOp(transform,
					AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			break;
		default:
			return data;
		}
		BufferedImage modified = op.filter(original, null);
		if (paint) {
			BufferedImage changed = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = changed.createGraphics();
			graphics.drawImage(modified, 0, 0, width, height, null);
			graphics.dispose();
			modified = changed;
		}
		ByteArrayOutputStream output = new ByteArrayOutputStream(1024 * 1024);
		output.reset();
		String format = "png";
		if (!ImageIO.write(modified, format, output)) {
			throw new IOException("Can't write the image in the given format "
					+ format);
		}
		LOGGER.debug("transform done in "
				+ (System.currentTimeMillis() - start));
		return output.toByteArray();
	}
}
