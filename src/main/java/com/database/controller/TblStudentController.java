package com.database.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.database.entity.TblStudent;
import com.database.service.ITblStudentService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cxw
 * @since 2019-09-25
 */
@RestController
@NoArgsConstructor
@CrossOrigin
@Slf4j
@RequestMapping("/tblstudent")
public class TblStudentController {
    private ITblStudentService iTblStudentService;

    @Autowired
    public TblStudentController(ITblStudentService iTblStudentService){
        this.iTblStudentService = iTblStudentService;
    }
    
    @RequestMapping("/gettblstudentbyid")
    public TblStudent getTblStudentById(){
        TblStudent student = iTblStudentService.getById("1");
        return student;
    }

}