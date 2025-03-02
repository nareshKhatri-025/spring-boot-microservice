package com.nkcreation.jobapp.job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
public class JobController {

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    JobService jobService;


    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }


    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> findById(@PathVariable("id") Long id){
        var job=jobService.findById(id);
if(job!=null){
  return  new  ResponseEntity<>(jobService.findById(id),HttpStatus.OK);
}
        return new  ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/jobs")
    public ResponseEntity<String> saveJob(@RequestBody Job job){

       jobService.createJob(job);
        return  new  ResponseEntity<>( "Job Added Succesfully",HttpStatus.OK);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        var status= jobService.deleteJob(id);
        if(status){
            return new  ResponseEntity<>("Job Deleted Successfully",HttpStatus.OK);
        }else{
            return  new ResponseEntity<>("Job with id "+id+" not found",HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value="/jobs/{id}",method = RequestMethod.PUT)
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job job){

      boolean isUpdated=  jobService.updateJob(id,job);
      if(isUpdated){
          return new ResponseEntity<>("Job Updated Successfully", HttpStatus.OK);
      }else{
          return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

    }
}


    /*

    GET /jobs : Get All Jobs
    GET /jobs/{id} : Get specific job by id
    POST /jobs: Create a new job (request body should contain the job details)
    DELETE /jobs/{id} : delete specific job by id
    PUT /jobs/{id}: Update job by specific id
     */