package com.crm.customerlead.config;

import com.crm.customerlead.entity.*;
import com.crm.customerlead.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final LeadTypeRepository leadTypeRepository;
    private final CustomerLeadRepository customerLeadRepository;
    private final FollowUpRepository followUpRepository;
    private final NotesRepository notesRepository;

    public DataLoader(UserRepository userRepository, LeadTypeRepository leadTypeRepository,
                      CustomerLeadRepository customerLeadRepository, FollowUpRepository followUpRepository,
                      NotesRepository notesRepository) {
        this.userRepository = userRepository;
        this.leadTypeRepository = leadTypeRepository;
        this.customerLeadRepository = customerLeadRepository;
        this.followUpRepository = followUpRepository;
        this.notesRepository = notesRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        System.out.println("Loading sample data...");
        
        // Check if data already exists
        if (userRepository.count() > 0) {
            System.out.println("Data already loaded, skipping...");
            return;
        }

        // Create Users
        User admin = new User(null, "admin", "admin123", "ADMIN");
        userRepository.save(admin);

        User executive = new User(null, "executive", "executive123", "EXECUTIVE");
        userRepository.save(executive);

        // Create Lead Types
        LeadType website = new LeadType(null, "Website", "Leads from website inquiries", null);
        leadTypeRepository.save(website);

        LeadType referral = new LeadType(null, "Referral", "Leads from customer referrals", null);
        leadTypeRepository.save(referral);

        LeadType coldCall = new LeadType(null, "Cold Call", "Leads from cold calling", null);
        leadTypeRepository.save(coldCall);

        LeadType socialMedia = new LeadType(null, "Social Media", "Leads from social media platforms", null);
        leadTypeRepository.save(socialMedia);

        // Create Customer Leads
        CustomerLead lead1 = new CustomerLead(
            null, "John Smith", "9876543210", "9876543211", "john.smith@example.com",
            website, "New York", "123 Main St, New York, NY 10001", "Interested in premium CRM solution",
            "Website Contact Form", "executive", "Initial call completed, showed interest in product",
            LocalDate.now().plusDays(2), LocalDate.now().plusDays(7), Status.INTERESTED, Priority.HOT, null
        );
        customerLeadRepository.save(lead1);

        CustomerLead lead2 = new CustomerLead(
            null, "Sarah Johnson", "9876543212", null, "sarah.johnson@example.com",
            referral, "Los Angeles", "456 Oak Ave, Los Angeles, CA 90001", "Small business CRM needs",
            "Customer Referral", "executive", "Referred by existing customer, needs demo",
            null, LocalDate.now().plusDays(3), Status.CONTACTED, Priority.WARM, null
        );
        customerLeadRepository.save(lead2);

        CustomerLead lead3 = new CustomerLead(
            null, "Michael Brown", "9876543213", null, "michael.brown@example.com",
            coldCall, "Chicago", "789 Pine Rd, Chicago, IL 60601", "Enterprise CRM solution",
            "Cold Call", "executive", "Cold call made, requested information",
            null, LocalDate.now().plusDays(5), Status.NEW, Priority.COLD, null
        );
        customerLeadRepository.save(lead3);

        CustomerLead lead4 = new CustomerLead(
            null, "Emily Davis", "9876543214", null, "emily.davis@example.com",
            socialMedia, "Houston", "321 Elm St, Houston, TX 77001", "CRM for startup",
            "LinkedIn", "executive", "LinkedIn inquiry, sent pricing",
            LocalDate.now().plusDays(1), LocalDate.now(), Status.VISIT_SCHEDULED, Priority.HOT, null
        );
        customerLeadRepository.save(lead4);

        // Create Follow Ups
        FollowUp followUp1 = new FollowUp(
            null, lead1, "Discussed pricing and features, customer interested",
            LocalDate.now().plusDays(7), Status.FOLLOW_UP, null
        );
        followUpRepository.save(followUp1);

        FollowUp followUp2 = new FollowUp(
            null, lead2, "Scheduled demo for next week",
            LocalDate.now().plusDays(3), Status.FOLLOW_UP, null
        );
        followUpRepository.save(followUp2);

        // Create Notes
        Notes note1 = new Notes(null, lead1, "Customer has budget approval, ready to proceed", null);
        notesRepository.save(note1);

        Notes note2 = new Notes(null, lead4, "Customer requested technical documentation", null);
        notesRepository.save(note2);

        System.out.println("Sample data loaded successfully!");
    }
}
