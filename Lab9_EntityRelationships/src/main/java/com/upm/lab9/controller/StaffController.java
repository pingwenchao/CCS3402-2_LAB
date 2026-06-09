package com.upm.lab9.controller;

import com.upm.lab9.entity.Staff;
import com.upm.lab9.repository.DepartmentRepository;
import com.upm.lab9.repository.ProjectRepository;
import com.upm.lab9.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * PING WENCHAO 226969
 * StaffController - Core routing engine managing structural operations for institutional Staff directories.
 * Coordinates data persistence bindings across bidirectional 1:N and M:N topologies.
 */
@Controller
public class StaffController {

    private final StaffRepository staffRepository;
    private final DepartmentRepository departmentRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public StaffController(StaffRepository staffRepository,
                           DepartmentRepository departmentRepository,
                           ProjectRepository projectRepository) {
        this.staffRepository = staffRepository;
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
    }

    /**
     * Renders the comprehensive staff directory including mapped departments and project portfolios.
     */
    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("staffs", staffRepository.findAll());
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("projects", projectRepository.findAll());
        return "list-staff";
    }

    /**
     * Displays the creation interface for registering new corporate personnel.
     */
    @GetMapping("signup")
    public String showSignUpForm(Staff staff, Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("projects", projectRepository.findAll());
        return "add-staff";
    }

    /**
     * Persists a newly created Staff records into the relational backing store.
     */
    @PostMapping("addstaff")
    public String addStaff(Staff staff, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("departments", departmentRepository.findAll());
            model.addAttribute("projects", projectRepository.findAll());
            return "add-staff";
        }
        staffRepository.save(staff);
        return "redirect:list";
    }

    /**
     * Pulls target staff profiles and opens the modification workspace interface.
     */
    @GetMapping("update/{id}")
    public String showUpdateMainForm(@PathVariable("id") long id, Model model) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid staff Id:" + id));
        model.addAttribute("staff", staff);
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("projects", projectRepository.findAll());
        return "update-staff";
    }

    /**
     * Synchronizes updated staff records, modifying relational foreign keys and join tables.
     */
    @PostMapping("update/{id}")
    public String updateStaff(@PathVariable("id") long id, Staff staff, BindingResult result, Model model) {
        if (result.hasErrors()) {
            staff.setId(id);
            model.addAttribute("departments", departmentRepository.findAll());
            model.addAttribute("projects", projectRepository.findAll());
            return "update-staff";
        }
        staffRepository.save(staff);
        return "redirect:/list";
    }

    /**
     * Destroys target staff profiles and cascades detachment over join tables.
     */
    @GetMapping("delete/{id}")
    public String deleteStaff(@PathVariable("id") long id, Model model) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid staff Id:" + id));
        staffRepository.delete(staff);
        return "redirect:/list";
    }
}