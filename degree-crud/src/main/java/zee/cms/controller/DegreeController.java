package zee.cms.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import zee.cms.entity.Degree;
import zee.cms.exceptions.DegreeNotFoundException;
import zee.cms.service.DegreeService;

/*
 * Degree Controller
 * 
 */
@RestController
@RequestMapping("/degree")
public class DegreeController {

	@Autowired
	private DegreeService degreeService;

	@GetMapping("/degrees")
	public List<Degree> findAllDegrees(){ return degreeService.findAll(); }

	@GetMapping("/degrees/{degreeId}")
	public Degree getDegree(@PathVariable long degreeId){
		Degree theDegree = degreeService.findById(degreeId);
		if( theDegree == null) { throw new DegreeNotFoundException("Degree ID not found - " + degreeId); }
		return theDegree; 
	}

	@PostMapping("/degrees")
	public Degree addDegree(@RequestBody Degree theDegree){
		theDegree.setId(0);
		degreeService.save(theDegree);
		return theDegree;		
	}

	@PutMapping("/degrees/{degreeId}")
	public Degree updateDegree(@PathVariable long degreeId, @RequestBody Degree theNewDegree){
		Degree theDegree = degreeService.findById(degreeId);
		if( theDegree == null) { throw new DegreeNotFoundException("Degree ID not found - " + degreeId); }
		theDegree.setName(theNewDegree.getName());
		degreeService.save(theDegree);
		return theDegree;
	}

	@DeleteMapping("/degrees/{degreeId}")
	public String deleteDegree(@PathVariable long degreeId){
		Degree theDegree = degreeService.findById(degreeId);
		if( theDegree == null) { throw new DegreeNotFoundException("Degree ID not found - " + degreeId); }
		degreeService.deleteById(degreeId);
		return "Deleted Degree Id: " + degreeId;
	}

}
