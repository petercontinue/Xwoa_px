package com.xwcloud.cloud.common;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageUtil {
    /**
     * 图片转换，将读取图片放大或缩小
     *
     * */
    public static  byte[] ChangeImgSize(byte[] data, int nw, int nh){
        byte[] newdata = null;
        try{
            BufferedImage bis = ImageIO.read(new ByteArrayInputStream(data));
            int w = bis.getWidth();
            int h = bis.getHeight();
            double sx = (double) nw / w;
            double sy = (double) nh / h;
            AffineTransform transform = new AffineTransform();
            transform.setToScale(sx, sy);
            AffineTransformOp ato = new AffineTransformOp(transform, null);
            //原始颜色
            BufferedImage bid = new BufferedImage(nw, nh, BufferedImage.TYPE_3BYTE_BGR);
            ato.filter(bis, bid);
            //转换成byte字节
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bid, "jpeg", baos);
            newdata = baos.toByteArray();
        }catch(IOException e){
            e.printStackTrace();
        }
        return newdata;
    }
}
