package album

import java.awt.Graphics2D
import java.awt.geom.AffineTransform
import java.awt.image.AffineTransformOp
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

@groovy.transform.CompileStatic
class ImageService{

	enum Operation{
		RotateClockWise90,RotateAntiClockWise90,Rotate180,Flip,Flop
	}

	def transform(byte[] data, Operation operation) {

		BufferedImage original = ImageIO.read(new ByteArrayInputStream(data))
		AffineTransform transform = new AffineTransform();
		AffineTransformOp op = null
		boolean paint = false
		Integer width = original.width
		Integer height = original.height
		switch (operation) {
			case Operation.RotateClockWise90:
				transform.rotate(Math.PI / 2.0)
				transform.translate(0, -height)
				op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
				width = original.height
				height = original.width
				paint = true
				break
			case Operation.RotateAntiClockWise90:
				transform.rotate(-Math.PI / 2.0)
				transform.translate(-width, 0)
				op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
				width = original.height
				height = original.width
				paint = true
				break
			case Operation.Rotate180:
				transform.rotate(Math.PI)
				transform.translate(-width, -height)
				op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
				break
			case Operation.Flip: // vertical
				transform.scale(-1.0d, 1.0d)
				transform.translate(-width, 0)
				op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR)
				break
			case Operation.Flop: // horizontal
				transform.scale(1.0d, -1.0d)
				transform.translate(0, -height)
				op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR)
				break
			default:
				return data
				break
		}
		BufferedImage modified = op.filter(original, null);
		if (paint) {
			BufferedImage changed = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
			Graphics2D graphics = changed.createGraphics()
			graphics.drawImage(modified, 0, 0, width, height, null)
			graphics.dispose()
			modified = changed
		}
		ByteArrayOutputStream output = new ByteArrayOutputStream(1024 * 1024)
		output.reset()
		String format = 'png'
		if (!ImageIO.write(modified, format, output)) {
			throw new IOException("Can't write the image in the given format '${format}'")
		}
		output.toByteArray()
	}
}