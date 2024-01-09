package cz.cvut.ear.clubevidence.rest;

import cz.cvut.ear.clubevidence.service.MembershipRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Member;

@RestController
@RequestMapping("/rest/membership_records")
public class MembershipRecordController {
    MembershipRecordService membershipRecordService;
    @Autowired
    public MembershipRecordController(MembershipRecordService membershipRecordService){
        this.membershipRecordService = membershipRecordService;
    }
}
