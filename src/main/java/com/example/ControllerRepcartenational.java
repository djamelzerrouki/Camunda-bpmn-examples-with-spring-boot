package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Employe;
import com.example.model.MyTask;
import com.example.model.NextTask;
import com.example.model.Service;
import com.example.repository.cartenational.Dossier;
import com.example.repository.cartenational.RepoDossiercartenational;
import com.example.repository.cartenational.RepoEmployecartenational;
import com.example.repository.cartenational.RepoMyTaskcartenational;
import com.example.repository.cartenational.RepoNextTaskcartenational;
import com.example.repository.cartenational.RepoServicecartenational;
@Controller 
@RequestMapping(value="/model_cartenational")
public class ControllerRepcartenational {

	@Autowired(required=true)
	private   RepoEmployecartenational red ;
	@Autowired 
	private   RepoServicecartenational rsd ;
	@Autowired 
	private   RepoDossiercartenational rdd;
	@Autowired 
	private   RepoMyTaskcartenational rmtd;	
	@Autowired 
	private   RepoNextTaskcartenational rntd;	
	
//	
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
		model.addAttribute("mytasks", rmtd.findAll());
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
	//addmytask
	//Service
	@RequestMapping(value="/Task" ,method=RequestMethod.GET)
	public String formMyTask(Model model) {
		List<MyTask> list = rmtd.findAll();
		model.addAttribute("mytasks",list);
		return "addmytask";
	}
	 
	//Service
	@RequestMapping(value="/Dossier" ,method=RequestMethod.GET)
	public String formDossier(Model model) {
		List<Dossier> list = rdd.findAll();
 		model.addAttribute("dossiers",list);
		return "adddossier";
	}

	@RequestMapping(value="/saveEnployee" ,method=RequestMethod.POST)
	public String saveEnployee(Model model, @Valid @ModelAttribute("enployee")Employe ep, BindingResult result) {
		//System.out.println("### :->"+ep.getService());
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
//				red.deleteAll();
//				rsd.deleteAll();
				rmtd.deleteAll();
				Collection<Task> tasks =testcamanda.mymethod(fileNames.toString());

				tasks.forEach(t->{

					// read attributes bvars.entrySet()y helper methods
					String id = t.getId();
					String name = t.getName();
					String type=t.getElementType().getTypeName();
					
 					MyTask myTask=new MyTask();
 					myTask.setIdtask(id);
 					myTask.setName(name);
 					myTask.setType(type);
 	/*
					Service srv =new Service();
					srv.setIdtask(id);
					srv.setName(name);
					srv.setType(type);

*/
//					List<Task> possibleTasks=	new testcamanda().getNextTasks(t.getId() ,fileNames.toString());
//
//					System .out.println("ID: "+ id +" Name : "+name +" Type : "+type);
//					possibleTasks.forEach(tsk->{
//						srv.setNextidtask(tsk.getId());
//
//						System .out.println("NEXT TASK -> ID: "+ tsk.getId() +" Name : "+tsk.getName() +" Type : "+type);
//					});

 					List<Task> possibleTasks= testcamanda.getNextTasks(t.getId() , testcamanda.pathUploads+fileNames.toString());
                     Collection<NextTask> collection= new ArrayList<NextTask>();
					System .out.println("ID: "+ id +" Name : "+name +" Type : "+type);
					possibleTasks.forEach(tsk->{
						NextTask nextTask= new NextTask();
						nextTask.setMytask(myTask);
						nextTask.setNextidtask(tsk.getId());
						collection.add(nextTask);
						
						System .out.println("NEXT TASK -> ID: "+ tsk.getId() +" Name : "+tsk.getName() +" Type : "+type);
					});
					
					myTask.setNexttasks(collection);
//					
//					myTask
//					rsd.save(srv);
					rmtd.save(myTask);
					possibleTasks.forEach(tsk->{
						NextTask nextTask= new NextTask();
						nextTask.setMytask(myTask);
						nextTask.setNextidtask(tsk.getId());
						collection.add(nextTask);
						rntd.save(nextTask);

						System .out.println("NEXT TASK -> ID: "+ tsk.getId() +" Name : "+tsk.getName() +" Type : "+type);
					});
				});




			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("msg", "Successfully uploaded files "+fileNames.toString());
		return "redirect:Task";
	}

 

	@PostMapping("/updateEmploye")
	public String update(@RequestParam(name ="id") Long id, @Valid Employe employe, BindingResult result, Model model) {
		if (result.hasErrors()) {
			employe.setId(id);
			return "redirect:updateEmploye";
			
		}
		red.save(employe);
		model.addAttribute("mytasks", rmtd.findAll());
		model.addAttribute("enployee", employe);
		model.addAttribute("enployees",red.findAll());
		return "redirect:Employe";
		
	}
	//Edit 
	@GetMapping("/editEmploye")
	public String showUpdateForm(@RequestParam(name ="id") Long id, Model model) {
		Employe employe = red.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Employe Id:" + id));


		model.addAttribute("mytasks", rmtd.findAll());
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
 		/*
 		 
 		  return red.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Employe Id:" + id));

 		 * */
	}

}
