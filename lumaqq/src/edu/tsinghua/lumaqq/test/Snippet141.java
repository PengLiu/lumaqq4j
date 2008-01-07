/*
 * LumaQQ - Java QQ Client
 *
 * Copyright (C) 2004 luma <stubma@163.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
/*
 * Image example snippet: display an animated GIF
 *
 * For a list of all SWT example snippets see
 * http://dev.eclipse.org/viewcvs/index.cgi/%7Echeckout%7E/platform-swt-home/dev.html#snippets
 */
package edu.tsinghua.lumaqq.test;
import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class Snippet141 {
	static Display display;

	static Shell shell;

	static GC shellGC;

	static Color shellBackground;

	static ImageLoader loader;

	static ImageData[] imageDataArray;

	static Thread animateThread;

	static Image image;

	static final boolean useGIFBackground = false;

	public static void main(String[] args) {
		display = new Display();
		shell = new Shell(display);
		shell.setSize(300, 300);
		shell.open();
		shellGC = new GC(shell);
		shellBackground = shell.getBackground();

		FileDialog dialog = new FileDialog(shell);
		dialog.setFilterExtensions(new String[] {
			"*.gif"
		});
		String fileName = dialog.open();
		if(fileName != null) {
			loader = new ImageLoader();
			try {
				imageDataArray = loader.load(fileName);
				if(imageDataArray.length > 1) {
					animateThread = new Thread("Animation") {
						public void run() {
							/*
							 * Create an off-screen image to draw on, and fill
							 * it with the shell background.
							 */
							Image offScreenImage = new Image(display, loader.logicalScreenWidth, loader.logicalScreenHeight);
							GC offScreenImageGC = new GC(offScreenImage);
							offScreenImageGC.setBackground(shellBackground);
							offScreenImageGC.fillRectangle(0, 0, loader.logicalScreenWidth, loader.logicalScreenHeight);
							try {
								/*
								 * Create the first image and draw it on the
								 * off-screen image.
								 */
								int imageDataIndex = 0;
								ImageData imageData = imageDataArray[imageDataIndex];
								if(image != null && !image.isDisposed())
									image.dispose();
								image = new Image(display, imageData);
								offScreenImageGC.drawImage(image, 0, 0, imageData.width, imageData.height, imageData.x, imageData.y, imageData.width, imageData.height);

								/*
								 * Now loop through the images, creating and
								 * drawing each one on the off-screen image
								 * before drawing it on the shell.
								 */
								int repeatCount = loader.repeatCount;
								while(loader.repeatCount == 0 || repeatCount > 0) {
									switch(imageData.disposalMethod) {
										case SWT.DM_FILL_BACKGROUND:
											/*
											 * Fill with the background color
											 * before drawing.
											 */
											Color bgColor = null;
											if(useGIFBackground && loader.backgroundPixel != -1) {
												bgColor = new Color(display, imageData.palette.getRGB(loader.backgroundPixel));
											}
											offScreenImageGC.setBackground(bgColor != null ? bgColor : shellBackground);
											offScreenImageGC.fillRectangle(imageData.x, imageData.y, imageData.width, imageData.height);
											if(bgColor != null)
												bgColor.dispose();
											break;
										case SWT.DM_FILL_PREVIOUS:
											/*
											 * Restore the previous image before
											 * drawing.
											 */
											offScreenImageGC.drawImage(image, 0, 0, imageData.width, imageData.height, imageData.x, imageData.y, imageData.width, imageData.height);
											break;
									}
									imageDataIndex = (imageDataIndex + 1) % imageDataArray.length;
									imageData = imageDataArray[imageDataIndex];
									image.dispose();
									image = new Image(display, imageData);
									offScreenImageGC.drawImage(image, 0, 0, imageData.width, imageData.height, imageData.x, imageData.y, imageData.width, imageData.height);
									/* Draw the off-screen image to the shell. */
									shellGC.drawImage(offScreenImage, 0, 0);
									/*
									 * Sleep for the specified delay time
									 * (adding commonly-used slow-down fudge
									 * factors).
									 */
									try {
										int ms = imageData.delayTime * 10;
										if(ms < 20)
											ms += 30;
										if(ms < 30)
											ms += 10;
										Thread.sleep(ms);
									} catch(InterruptedException e) {
									}
									/*
									 * If we have just drawn the last image,
									 * decrement the repeat count and start
									 * again.
									 */
									if(imageDataIndex == imageDataArray.length - 1)
										repeatCount--;
								}
							} catch(SWTException ex) {
								System.out.println("There was an error animating the GIF");
							} finally {
								if(offScreenImage != null && !offScreenImage.isDisposed())
									offScreenImage.dispose();
								if(offScreenImageGC != null && !offScreenImageGC.isDisposed())
									offScreenImageGC.dispose();
								if(image != null && !image.isDisposed())
									image.dispose();
							}
						}
					};
					animateThread.setDaemon(true);
					animateThread.start();
				}
			} catch(SWTException ex) {
				System.out.println("There was an error loading the GIF");
			}
		}

		while(!shell.isDisposed()) {
			if(!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
