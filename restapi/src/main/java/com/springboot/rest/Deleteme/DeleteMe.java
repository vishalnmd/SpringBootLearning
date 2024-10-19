package com.springboot.rest.Deleteme;

import java.io.File;


public class DeleteMe {
	public static void main(String args[]) throws Exception {
		
		DeleteMe obj = new DeleteMe();
		
		System.out.println(obj.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
		
		
		
//		PDDocument document = PDDocument.load(new File("E:\\MyWorkSpace\\SpringBoot\\SpringBootLearning\\restapi\\PolicyTemplate.pdf"));
//		if (!document.isEncrypted()) {
//		    PDFTextStripper stripper = new PDFTextStripper();
//		    String text = stripper.getText(document);
//		    System.out.println("Text from pdf:" + text);
//		} else{
////		    log.info("File is encrypted!");
//			
//		}
//		document.close();
//	}
		
//		PdfReader reader = new PdfReader("E:\\\\MyWorkSpace\\\\SpringBoot\\\\SpringBootLearning\\\\restapi\\\\form.pdf");
//		 int numPages = reader.getNumberOfPages();
//         StringBuilder extractedText = new StringBuilder();
//
//         for (int page = 1; page <= numPages; page++) {
//             extractedText.append(PdfTextExtractor.getTextFromPage(reader, page));
//         }
//         
//         reader.close();
//         System.out.println(extractedText.toString());
		
	}
}
