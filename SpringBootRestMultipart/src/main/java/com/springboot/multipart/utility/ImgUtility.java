package com.springboot.multipart.utility;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImgUtility {
	
	public final String img_dir = "E:\\MyWorkSpace\\SpringBoot\\SpringBootLearning\\SpringBootRestMultipart\\src\\main\\resources\\static\\images";
	
	public Boolean postImg(MultipartFile file) {
		Boolean check = false;
		
		try{
//			InputStream ip = file.getInputStream();
//			byte data[] = new byte[ip.available()];
//			
//			ip.read(data);
//			
//			try (FileOutputStream fos = new FileOutputStream(img_dir + File.separator + file.getName()+file.getOriginalFilename())) {
//				fos.write(data);
//			}
//			return true;
//		
			Files.copy(file.getInputStream(), Path.of(img_dir+ File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			return true;
			
		} catch (Exception e) {
			System.out.println(e);
			return check;
		}
	}
}
