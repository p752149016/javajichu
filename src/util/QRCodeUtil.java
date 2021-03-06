package util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
 
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
 
/**
 * 二维码生成工具类
 * @author PLF
 * @date   2020-1-21
 * QRCode
 */
 
 
public class QRCodeUtil{
 
      //二维码颜色
      private static final int BLACK = 0xFF000000;
      //二维码颜色
      private static final int WHITE = 0xFFFFFFFF;
 
      /**
       * ZXing 方式生成二维码
       * @param text    		二维码内容
       * @param width    		二维码宽
       * @param height    		二维码高
       * @param outPutPath      二维码生成保存路径
       * @param imageType       二维码生成格式
       */
      public static void zxingCodeCreate(String text, int width, int height, String outPutPath, String imageType){
            Map<EncodeHintType, String> his = new HashMap<EncodeHintType, String>();
            //设置编码字符集
            his.put(EncodeHintType.CHARACTER_SET, "utf-8");
            try {
                  //1、生成二维码
                  BitMatrix encode = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, his);
 
                  //2、获取二维码宽高
                  int codeWidth = encode.getWidth();
                  int codeHeight = encode.getHeight();
 
                  //3、将二维码放入缓冲流
                  BufferedImage image = new BufferedImage(codeWidth, codeHeight, BufferedImage.TYPE_INT_RGB);
                  for (int i = 0; i < codeWidth; i++) {
                        for (int j = 0; j < codeHeight; j++) {
                              //4、循环将二维码内容定入图片
                              image.setRGB(i, j, encode.get(i, j) ? BLACK : WHITE);
                        }
                  }
                  File outPutImage = new File(outPutPath);
                  //如果图片不存在创建图片
                  if(!outPutImage.exists())
                        outPutImage.createNewFile();
                  //5、将二维码写入图片
                  ImageIO.write(image, imageType, outPutImage);
            } catch (WriterException e) {
                  e.printStackTrace();
                  System.out.println("二维码生成失败");
            } catch (IOException e) {
                  e.printStackTrace();
                  System.out.println("生成二维码图片失败");
            }
      }
 
 
       
      public static void main(String[] args) throws Exception {
    	  
		// QRcode 二维码生成测试
		QRCodeUtil.zxingCodeCreate("https://fanyi.baidu.com/?aldtype=16047#zh/en/%E4%BA%8C%E7%BB%B4%E7%A0%81",300, 300, "E:/home/borths.jpg", "jpg");
		System.out.println("success");
      }
}
