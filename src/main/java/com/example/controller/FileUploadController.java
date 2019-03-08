package com.example.controller;

 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.testcamanda;

@Controller
public class FileUploadController {
  public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";
  @RequestMapping("/")
  public String UploadPage(Model model) {
	  return "uploadview";
  }
  
  
  
}
