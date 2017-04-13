package com.ych.openCVUtils;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;

public class DetectFaceUtil {

	public static boolean isExistFace(String path){
	//	System.loadLibrary("opencv_java310");
		LoadOpenCV.getInstance();  //加载opencv lib
		System.out.println("----"+DetectFaceUtil.class.getResource("lbpcascade_frontalface.xml").getPath().substring(1));
		 String xmlfilePath=DetectFaceUtil.class.getResource("lbpcascade_frontalface.xml").getPath().substring(1);
		   CascadeClassifier faceDetector = new CascadeClassifier(xmlfilePath);
		    Mat image = Imgcodecs.imread(path);

		 // Detect faces in the image.
		    // MatOfRect is a special container class for Rect.
		    MatOfRect faceDetections = new MatOfRect();
		    faceDetector.detectMultiScale(image, faceDetections);
		    if(faceDetections.toArray().length==1)
		    	return true;
		   return false;
		
	}
	
}
