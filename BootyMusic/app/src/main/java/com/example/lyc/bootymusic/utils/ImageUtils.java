///**
// * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *     http://www.apache.org/licenses/LICENSE-2.0
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package com.example.lyc.bootymusic.utils;
//
//import android.graphics.Bitmap;
//
//import com.easemob.util.EMLog;
//import com.easemob.util.PathUtil;
//
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//public class ImageUtils {
////	public static String getThumbnailImagePath(String imagePath) {
////		String path = imagePath.substring(0, imagePath.lastIndexOf("/") + 1);
////		path += "th" + imagePath.substring(imagePath.lastIndexOf("/") + 1, imagePath.length());
////		EMLog.d("msg", "original image path:" + imagePath);
////		EMLog.d("msg", "thum image path:" + path);
////		return path;
////	}
//
//	public static String getImagePath(String remoteUrl)
//	{
//		String imageName= remoteUrl.substring(remoteUrl.lastIndexOf("/") + 1, remoteUrl.length());
//		String path =PathUtil.getInstance().getImagePath()+"/"+ imageName;
//        EMLog.d("msg", "image path:" + path);
//        return path;
//
//	}
//
//
//	public static String getThumbnailImagePath(String thumbRemoteUrl) {
//		String thumbImageName= thumbRemoteUrl.substring(thumbRemoteUrl.lastIndexOf("/") + 1, thumbRemoteUrl.length());
//		String path =PathUtil.getInstance().getImagePath()+"/"+ "th"+thumbImageName;
//        EMLog.d("msg", "thum image path:" + path);
//        return path;
//    }
//
//	public static void saveBitmapToFile(Bitmap bitmap, String _file){
//        BufferedOutputStream os = null;
//        try {
//            File file = new File(_file);
//            int end = _file.lastIndexOf(File.separator);
//            String _filePath = _file.substring(0, end);
//            File filePath = new File(_filePath);
//            if (!filePath.exists()) {
//                filePath.mkdirs();
//            }
//            file.createNewFile();
//            os = new BufferedOutputStream(new FileOutputStream(file));
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
//        }catch(Exception ex){
//        	ex.printStackTrace();
//        } finally {
//            if (os != null) {
//                try {
//                    os.close();
//                } catch (IOException e) {
//                }
//            }
//        }
//    }
//
//}
