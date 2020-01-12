package com.nikola.backend1;

import com.nikola.backend1.domain.Student;
import com.nikola.backend1.domain.UniversityGroup;
import com.nikola.backend1.repos.StudentRepo;
import com.nikola.backend1.repos.UniversityGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Map;


@Controller
public class GreetingController {

    @Autowired
    private UniversityGroupRepo universityGroupRepo;
    @Autowired StudentRepo studentRepo;

    @GetMapping("main")
    public String main(Map<String, Object> model) {
        Iterable<UniversityGroup> universityGroups = universityGroupRepo.findAll();
        model.put("UniversityGroups", universityGroups);
        Iterable<Student> students = studentRepo.findAll();
        model.put("Students", students);
        return "main";
    }

    @PostMapping("addGroup")
    public String addGroup(@RequestParam String groupName, Map<String, Object> model) {
        if(universityGroupRepo.findByGroupName(groupName) == null) {
            UniversityGroup universityGroup = new UniversityGroup(groupName);
            universityGroupRepo.save(universityGroup);
        }
        return main(model);
    }

    @PostMapping("deleteGroup")
    public String deleteGroup(@RequestParam Integer group_id, Map<String, Object> model) {
        boolean is = true;
        Iterable<Student> students = studentRepo.findAll();
        for (Student student : students) {
            if (student.getGroup_id().getId().equals(group_id))
                is = false;
        }

        if(universityGroupRepo.existsById(group_id) && is)
        universityGroupRepo.deleteById(group_id);
        return main(model);
    }

    @PostMapping("renameGroup")
    public String renameGroup(@RequestParam String groupNameOld, @RequestParam String groupNameNew, Map<String, Object> model) {
        UniversityGroup universityGroup = universityGroupRepo.findByGroupName(groupNameOld);

        if(universityGroup != null){
            universityGroup.setGroupName(groupNameNew);
            universityGroupRepo.save(universityGroup);
        }

        return main(model);
    }

    @PostMapping("addStudent")
    public String addStudent(@RequestParam String name, @RequestParam String groupName, Map<String, Object> model) {
        UniversityGroup universityGroup = universityGroupRepo.findByGroupName(groupName);
        if(universityGroup == null){
            UniversityGroup universityGroupNew =new UniversityGroup(groupName);
            universityGroupRepo.save(universityGroupNew);
            Student student = new Student(name, universityGroupNew);
            studentRepo.save(student);
        }
        else {
            Student student = new Student(name, universityGroup);
            studentRepo.save(student);
        }
        return main(model);
    }

    @PostMapping("deleteStudent")
    public String deleteStudent(@RequestParam Integer id, Map<String, Object> model) {
        studentRepo.deleteById(id);
        return main(model);
    }

    @PostMapping("renameStudent")
    public String renameStudent(@RequestParam Integer id, @RequestParam String name, Map<String, Object> model) {
        if(studentRepo.existsById(id)) {
          Student student = studentRepo.findById(id).orElse(new Student());
          student.setName(name);
          studentRepo.save(student);
        }

        return main(model);
    }

}

