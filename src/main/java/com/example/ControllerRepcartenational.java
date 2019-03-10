package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.camunda.bpm.model.bpmn.instance.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Employe;
import com.example.model.Service;
import com.example.repository.cartenational.RepoEmployecartenational;
import com.example.repository.cartenational.RepoServicecartenational;
import com.exomple.configfile.Config;
@Controller 
@RequestMapping(value="/model_cartenational")
public class ControllerRepcartenational {

	@Autowired(required=true)
	private   RepoEmployecartenational red ;
	@Autowired 
	private   RepoServicecartenational rsd ;
	private static String namedb; 
	public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";

	// Modele selectioner
	@RequestMapping("/model" )
	public String model(Model model,@RequestParam(name = "m") String namemodel) throws IOException, SQLException {
		//	ArrayList <String> list=TestserverApplication.showDatabase() ;

		namemodel =namemodel.replaceAll("\\s","");
		namedb=namemodel;
		model.addAttribute("modelName",namedb);

		return "uploadview";

	}	//Employe
	@RequestMapping(value="/Employe" ,method=RequestMethod.GET)
	public String formEmploye(Model model) {
		List<Employe> list = red.findAll();
		model.addAttribute("services", rsd.findAll());
		model.addAttribute("enployee",new Employe());
		model.addAttribute("enployees",list);
		return "addenployee";
	}
	//Service
	@RequestMapping(value="/Service" ,method=RequestMethod.GET)
	public String formService(Model model) {
		List<Service> list = rsd.findAll();
		model.addAttribute("service",new Employe());
		model.addAttribute("services",list);
		return "addservice";
	}

	@RequestMapping(value="/saveEnployee" ,method=RequestMethod.POST)
	public String saveEnployee(Model model, @Valid @ModelAttribute("enployee")Employe ep, BindingResult result) {
		System.out.println("### :->"+ep.getService());
		//Service service = new Service();
		//service.setId(5);
		//	ep.setService(service);
		red.save(ep);
		return "redirect:Employe";
	}	
	//saveService
	@RequestMapping(value="/saveService" ,method=RequestMethod.POST)
	public String saveService(Service srv) {
		rsd.save(srv);
		return "redirect:Service";
	}

	@RequestMapping(value="/bpmn")
	public String bpmnModele()  {
		return "bpmn";
	}


	@RequestMapping("/upload")
	public String upload(Model model,@RequestParam("files") MultipartFile[] files) {
		StringBuilder fileNames = new StringBuilder();
		for (MultipartFile file : files) {
			Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
			fileNames.append(file.getOriginalFilename()+" ");
			System.out.println("name file:"+fileNames);

			try {
				Files.write(fileNameAndPath, file.getBytes());
				//Config.configAll(namemodel,index);
				red.deleteAll();
				rsd.deleteAll();

				Collection<Task> tasks =testcamanda.mymethod(fileNames.toString());

				tasks.forEach(t->{

					// read attributes bvars.entrySet()y helper methods
					String id = t.getId();
					String name = t.getName();
					String type=t.getElementType().getTypeName();
					Service srv =new Service();
					srv.setIdtask(id);
					srv.setName(name);
					srv.setType(type);


					List<Task> possibleTasks=	new testcamanda().getNextTasks(t.getId() ,fileNames.toString());

					System .out.println("ID: "+ id +" Name : "+name +" Type : "+type);
					possibleTasks.forEach(tsk->{
						srv.setNextidtask(tsk.getId());

						System .out.println("NEXT TASK -> ID: "+ tsk.getId() +" Name : "+tsk.getName() +" Type : "+type);
					});

					rsd.save(srv);
				});




			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("msg", "Successfully uploaded files "+fileNames.toString());
		return "redirect:Service";
	}

 

	@PostMapping("/updateEmploye")
	public String update(@RequestParam(name ="id") Long id, @Valid Employe employe, BindingResult result, Model model) {
		if (result.hasErrors()) {
			employe.setId(id);
			return "redirect:updateEmploye";
			
		}
		red.save(employe);
		model.addAttribute("services", rsd.findAll());
		model.addAttribute("enployee", employe);
		model.addAttribute("enployees",red.findAll());
		return "redirect:Employe";
		
	}
	//Edit 
	@GetMapping("/editEmploye")
	public String showUpdateForm(@RequestParam(name ="id") Long id, Model model) {
		Employe employe = red.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Employe Id:" + id));


		model.addAttribute("services", rsd.findAll());
		model.addAttribute("enployee", employe);
		model.addAttribute("enployees",red.findAll());

		return "updatenployee";
	}


	//	     
	//	@GetMapping("/delete/{id}")
	//	public String deleteUser(@PathVariable("id") long id, Model model) {
	//	    User user = userRepository.findById(id)
	//	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	//	    userRepository.delete(user);
	//	    model.addAttribute("users", userRepository.findAll());
	//	    return "index";
	//	}
	//
	//
	@GetMapping("/findemploye")
	@ResponseBody
	public Employe findOne(@RequestParam(name ="id") Long id) {
		return red.getOne(id);
	}

}
