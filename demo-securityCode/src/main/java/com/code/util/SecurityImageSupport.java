package com.code.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.CubicCurve2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class SecurityImageSupport {

	/**
	 * 返回验证码图片的流格式
	 * 
	 * @param securityCode
	 *            验证码
	 * @return ByteArrayInputStream 图片流
	 */
	public static ByteArrayInputStream getImageAsInputStream(String securityCode) {
		BufferedImage image = createImage(securityCode);
		return convertImageToStream(image);
	}
	
	public static byte[] getImageAsByte(String securityCode) {
		BufferedImage image = createImage(securityCode);
		return convertImageToByte(image);
	}

	/**
	 * 生成验证码图片
	 * 
	 * @param securityCode
	 *            验证码字符
	 * @return BufferedImage 图片
	 */
	private static BufferedImage createImage(String securityCode) {
		// 验证码长度
		int codeLength = securityCode.length();
		// 字体大小
		int fSize = 13;
		int fWidth = fSize + 1;
		// 图片宽度
		int width = codeLength * fWidth + 15;
		// 图片高度
		int height = (int) (fSize * 1.5) + 1;
		// 图片
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		Color bgColor = new Color(239, 241, 249);
		// 设置背景色
		g.setColor(bgColor);
		// 填充背景
		g.fillRect(0, 0, width, height);
		// 设置边框颜色
		g.setColor(bgColor);
		// 边框字体样式
		g.setFont(new Font("Arial", Font.BOLD, height - 2));
		// 绘制边框
		g.drawRect(10, 10, width - 1, height - 1);
		// 绘制噪点
		Random rand = new Random();
		// 设置噪点颜色
		g.setColor(Color.LIGHT_GRAY);
		for (int i = 0; i < codeLength * 6; i++) {
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			// 绘制1*1大小的矩形
			g.drawRect(x, y, 1, 1);
		}
		// 绘制验证码
		int codeY = height - 5;
		// 设置字体颜色和样式
		g.setColor(new Color(80, 25, 28));
		g.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, fSize));
		for (int i = 0; i < codeLength; i++) {
			g.drawString(String.valueOf(securityCode.charAt(i)), i * 16 + 5, codeY);
		}

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Random r = new Random();
		CubicCurve2D cubic = new CubicCurve2D.Float(2, height / 2 + r.nextInt(8) - 4, //
				2 + width * 1 / 3, height / 2 + r.nextInt(8) - 4, //
				2 + width * 2 / 3, height / 2 + r.nextInt(8) - 4, //
				width - 2, height / 2 + r.nextInt(8) - 4);
		g.draw(cubic);

		// 关闭资源
		g.dispose();
		return image;
	}

	/**
	 * 将BufferedImage转换成ByteArrayInputStream
	 * 
	 * @param image
	 *            图片
	 * @return ByteArrayInputStream 流
	 */
	private static ByteArrayInputStream convertImageToStream(BufferedImage image) {
		byte[] bts = convertImageToByte(image);
		if(bts!=null) {
			return new ByteArrayInputStream(bts);
		}else {
			return null;
		}
	}
	
	private static byte[] convertImageToByte(BufferedImage image) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "jpeg", bos);
			image.flush();
			byte[] bts = bos.toByteArray();
			return bts;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
