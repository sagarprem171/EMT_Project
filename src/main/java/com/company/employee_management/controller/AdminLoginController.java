package com.company.employee_management.controller;

import com.company.employee_management.model.Admin;
import com.company.employee_management.repository.AdminRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/login")
    public String adminLogin() {
        return "admin/login"; // Show login page
    }

    private String generateOTP() {
        // SecureRandom random = new SecureRandom();
        // int otp = 100000 + random.nextInt(900000);
        // return String.valueOf(otp);
        return "4444"; // Hardcoded OTP for testing
    }

    @PostMapping("/validate")
    public String validateAdmin(@RequestParam("adminId") String adminId,
            @RequestParam("orgCode") String orgCode,
            @RequestParam("password") String password,
            Model model,
            HttpSession session) {

        Optional<Admin> adminOpt = adminRepository.findByAdminIdAndAdminOrgCodeAndAdminPassword(adminId, orgCode,
                password);

        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            String otp = generateOTP();

            System.out.println("Generated OTP for Admin Login: " + otp); // In real world, send this via SMS

            // Store OTP and adminId in session
            session.setAttribute("expectedOtp", otp);
            session.setAttribute("adminId", admin.getAdminId());

            // Pass data to show modal
            model.addAttribute("mobileNumber", admin.getAdminMobileNumber());
            model.addAttribute("showOtpModal", true);

            return "admin/login";
        } else {
            model.addAttribute("error", "Invalid Admin ID, Organization Code, or Password.");
            return "admin/login";
        }
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam("otp") String otp,
            Model model,
            HttpSession session) {

        // String expectedOtp = (String) session.getAttribute("expectedOtp");
        // String adminId = (String) session.getAttribute("adminId");

        // if (expectedOtp != null && expectedOtp.equals(otp)) {
        // // Clear session attributes
        // session.removeAttribute("expectedOtp");
        // session.removeAttribute("adminId");

        // // Redirect to actual admin dashboard (create later)
        // model.addAttribute("adminId", adminId);
        // return "admin/panel"; // You will create admin/panel.html later
        // } else {
        // model.addAttribute("error", "OTP verification failed or expired.");
        // return "redirect:/admin/login";
        // }

        String expectedOtp = (String) session.getAttribute("expectedOtp");
        String adminId = (String) session.getAttribute("adminId");

        if ("4444".equals(otp)) { // Always accept 4444 for testing

            session.removeAttribute("expectedOtp");
            session.removeAttribute("adminId");
            model.addAttribute("adminId", adminId);
            return "admin/panel";
        } else {
            model.addAttribute("error", "OTP verification failed or expired.");
            return "redirect:/admin/login";
        }

    }
}
