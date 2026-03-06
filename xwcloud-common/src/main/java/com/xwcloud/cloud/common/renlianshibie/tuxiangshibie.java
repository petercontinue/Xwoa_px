package com.xwcloud.cloud.common.renlianshibie;


//import org.opencv.core.*;
//import org.opencv.imgcodecs.Imgcodecs;
//import org.opencv.imgproc.Imgproc;
//import org.opencv.objdetect.CascadeClassifier;

public class tuxiangshibie {
    // 初始化人脸探测器
    //static CascadeClassifier faceDetector;
    static {
        //路径不能包含中文  linux使用libopencv_java410.so
        String opencvDllName = "G:\\opencv-4.5.2-vc14_vc15\\opencv\\build\\java\\x64\\opencv_java452.dll";
        System.load(opencvDllName);
        // xml路径不能包含中文
        //G:\opencv-4.5.2-vc14_vc15\opencv\build\etc\haarcascades
        //faceDetector = new CascadeClassifier("G:\\opencv-4.5.2-vc14_vc15\\opencv\\build\\etc\\haarcascades\\haarcascade_frontalface_alt.xml");
    }
    // 灰度化人脸
 //   public static Mat conv_Mat(String img) {
//        Mat image0 = Imgcodecs.imread(img);
//        Mat image1 = new Mat();
//        //Mat image1 = new Mat(240,320,CvType.CV_8UC3);
//        // 灰度化
//        Imgproc.cvtColor(image0, image1, Imgproc.COLOR_BGR2GRAY);
//        // 探测人脸
//        MatOfRect faceDetections = new MatOfRect();
//        faceDetector.detectMultiScale(image1, faceDetections);
//        // rect中人脸图片的范围
//        for (Rect rect : faceDetections.toArray()) {
//            Mat face = new Mat(image1, rect);
//            return face;
//        }
//        return null;
//    }

//    public static double compare_image(String img_1, String img_2) {
//        Mat mat_1 = conv_Mat(img_1);
//        Mat mat_2 = conv_Mat(img_2);
//        Mat hist_1 = new Mat();
//        Mat hist_2 = new Mat();
//
//        //颜色范围
//        MatOfFloat ranges = new MatOfFloat(0f, 256f);
//        //直方图大小， 越大匹配越精确 (越慢)
//        MatOfInt histSize = new MatOfInt(10000000);
//
//        //计算图像直方图
//        Imgproc.calcHist(Arrays.asList(mat_1), new MatOfInt(0), new Mat(), hist_1, histSize, ranges);
//        Imgproc.calcHist(Arrays.asList(mat_2), new MatOfInt(0), new Mat(), hist_2, histSize, ranges);
//
//
//        // CORREL 相关系数
//        double res = Imgproc.compareHist(hist_1, hist_2, Imgproc.CV_COMP_CORREL);
//        return res;
//    }

    public static void detect(String inImg, String outImg) {
//        File file = new File(inImg);
//        if (!file.exists()) {
//            System.out.println("Image File Not Found");
//            return;
//        }
//        // 读取图像
//        Mat image = Imgcodecs.imread(inImg);
//        // 用于保存监测到的人脸
//        MatOfRect faceDetections = new MatOfRect();
//        // 开始监测
//        faceDetector.detectMultiScale(image, faceDetections);
//        int size = faceDetections.toArray().length;
//        System.out.println("人像：" + size);
//        int i = 0;
//        for (Rect rect : faceDetections.toArray()) {
//            // 循环所有监测到的人脸
//            Point x = new Point(rect.x, rect.y);
//            Point y = new Point(rect.x + rect.width, rect.y + rect.height);
//            // 在image图片上画框，x，y可确定框的位置和大小，new Scalar(0, 255, 0)是框的颜色，自行调整
//            Imgproc.rectangle(image, x, y, new Scalar(0, 255, 0));
//            // 保存监测的人脸小图片
//            Rect r = new Rect(x, y);
//            Mat areaM = new Mat(image, r);
//            // 保存监测的人脸小图片到tmp+序号的jpg文件
//            String tmpFilePath = "H:\\javaFace_about\\images\\" + "tmp" + (i++) + ".png";
//            Imgcodecs.imwrite(tmpFilePath, areaM);
//        }
//        // 保存画了方框的图片
//        Imgcodecs.imwrite(outImg, image);
//        // 销毁
//        image.release();
    }

}
