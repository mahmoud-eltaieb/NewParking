/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import DAOS.EmployeesImp;
import DAOS.GarageImp;
import DAOS.GarageSlotDoorsImp;
import DAOS.GarageSlotsStatusImp;
import DAOS.ReportsImp;
import DAOS.SlotsDetailsImp;
import DAOS.UserImp;
import daosint.ReportsInterface;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import pojo.Address;
import pojo.Employees;
import pojo.Garage;
import pojo.GarageSlotsDoors;
import pojo.Map;
import pojo.Users;
import reportsClasses.ReportHistoryRecord;

/**
 *
 * @author orcl
 */
public class Home {

    public static void main(String[] args) {


        boolean garageNameAvailable = GarageImp.getInstance().isGarageNameAvailable("243", "ss");
        System.out.println();
    }
}

  // 30.071352,31.021236,30.071354,31.021238 ==> 29 cm
// 30.071352,31.021236, 30.071354,31.021235 ==> 24 cm
// 30.071352,31.021236,30.071354,31.021236   ==> 22 cm
// int result = GarageSlotDoorsImp.getInstance().getNearestDoor(4, 30.071352, 31.021236);
  // System.out.println(result);
